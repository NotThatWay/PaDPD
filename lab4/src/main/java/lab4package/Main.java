package lab4package;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;

public class Main {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("lab4");
        Http http = Http.get(actorSystem);
        ActorMaterializer actorMaterializer = ActorMaterializer.create(actorSystem);
        

    }
}
