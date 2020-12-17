package lab4package;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
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

    public Server(ActorSystem actorSystem, ActorRef actorRef) {
        this.actorSystem = actorSystem;
        this.actorRef = actorRef;
    }

    public Route getRoute() {
        Directives.route(Directives.path("execute", () ->
                Directives.route(Directives.post(() ->
                        Directives.entity(Jackson.unmarshaller(RetrieveResults.class), body -> {
                            actorRef.tell(new ExecuteMessage(body.id, body.functionName, body.jsScript, body.tests), ActorRef.noSender());
                            return Directives.complete(StatusCodes.OK, String.format("Package %s started\n", body.id));
                        })))),
                Directives.path("retrieve", () -> Directives.route(Directives.get(() ->
                        Directives.parameter("packageID", id -> {
                            Future<Object> future =
                                    Patterns.ask(actorRef, new RetrievedMessage(id), FUTURE_TIMEOUT);
                            StoredMessage res = Await.result(future, FUTURE_TIMEOUT, )
                        })))))
    }
}
