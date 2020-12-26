package lab5package;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import org.apache.zookeeper.*;
import akka.http.javadsl.server.Directives;

public class Main {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("routes");
        Http http = Http.get(actorSystem);
        ActorRef actorRef = actorSystem.actorOf(Props.create(ConfigurationStoreActor.class));
        ActorMaterializer actorMaterializer = ActorMaterializer.create(actorSystem);
        Flow<HttpRequest, HttpResponse, NotUsed> route = 
    }
}
