package lab4package;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.util.concurrent.CompletionStage;

public class Main {
    static final String IP = "localhost";
    static final int PORT = 6111;

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("lab4");
        Http http = Http.get(actorSystem);
        ActorMaterializer actorMaterializer = ActorMaterializer.create(actorSystem);
        Server server = new Server(actorSystem);
        Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = server.getRoute().flow(actorSystem, actorMaterializer);
        CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow, ConnectHttp.toHost())

    }
}
