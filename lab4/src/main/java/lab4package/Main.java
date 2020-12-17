package lab4package;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

public class Main {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("lab4");
        Http http = Http.get(actorSystem);
        ActorMaterializer actorMaterializer = ActorMaterializer.create(actorSystem);
        Server server = new Server(actorSystem);
        Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = server.getRoute().flow(actorSystem, actorMaterializer);
        

    }
}
