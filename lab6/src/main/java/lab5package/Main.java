package lab5package;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;

public class Main {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("routes");
        Http http = Http.get(actorSystem);
        ActorRef actorRef = actorSystem.actorOf(Props.create(...));
    } 
}
