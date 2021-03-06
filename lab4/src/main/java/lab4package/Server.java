package lab4package;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.Directives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.util.Timeout;

import java.time.Duration;

import scala.concurrent.Await;
import scala.concurrent.Future;

public class Server {
    static final Timeout FUTURE_TIMEOUT = Timeout.create(Duration.ofSeconds(5));

    ActorSystem actorSystem;
    ActorRef actorRef;

    public Server(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
        this.actorRef = actorSystem.actorOf(Props.create(RouteActor.class));
    }

    public Route getRoute() {
        return Directives.route(Directives.path("execute", () ->
                Directives.route(Directives.post(() ->
                        Directives.entity(Jackson.unmarshaller(RetrieveResults.class), body -> {
                            actorRef.tell(new ExecuteMessage(body.getPackageID(), body.getJsScript(), body.getFunctionName(), body.getTests()), ActorRef.noSender());
                            return Directives.complete(StatusCodes.OK, String.format("Package %s started\n", body.getPackageID()));
                        })))),
                Directives.path("retrieve", () -> Directives.route(Directives.get(() ->
                        Directives.parameter("packageID", id -> {
                            Future<Object> future = Patterns.ask(actorRef, new RetrievedMessage(id), FUTURE_TIMEOUT);
                            StoredMessage res = null;
                            try {
                                res = (StoredMessage) Await.result(future, FUTURE_TIMEOUT.duration());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (res != null && res.result != null) {
                                return Directives.complete(StatusCodes.OK, res.result.toJSON() + "\n");
                            }
                            return null;
                        })))),
                Directives.complete(StatusCodes.NOT_FOUND, "Wrong request\n")
        );
    }
}
