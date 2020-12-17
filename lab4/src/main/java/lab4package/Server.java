package lab4package;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.Directives;
import akka.http.javadsl.server.Route;

public class Server {
    ActorSystem actorSystem;
    ActorRef actorRef;

    public Server(ActorSystem actorSystem, ActorRef actorRef) {
        this.actorSystem = actorSystem;
        this.actorRef = actorRef;
    }

    public Route getRoute() {
        Directives.route(Directives.path("execute", () ->
                Directives.route(Directives.post(() ->
                        Directives.entity(Jackson.unmarshaller(ExecuteMessage))))))
    }
}
