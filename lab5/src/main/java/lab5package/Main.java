package lab5package;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import java.time.Duration;

import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import javafx.util.Pair;
import org.asynchttpclient.*;

public class Main {
    final static AsyncHttpClient asyncHttpClient = Dsl.asyncHttpClient();
    final static String URL = "testUrl";
    final static String COUNT = "count";
    static ActorRef cache;
    static final Duration timeout = Duration.ofSeconds(5);

    public static void main(String[] args) throws IOException {
        System.out.println("start!");
        ActorSystem system = ActorSystem.create("routes");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = createFlow(materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8080),
                materializer
        );
        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
        asyncHttpClient.close();
        cache = system.actorOf(Props.create(CacheActor.class));
    }

    public static Flow<HttpRequest, HttpResponse, NotUsed> createFlow(ActorMaterializer materializer) {
        return Flow.of(HttpRequest.class).map(x -> new Pair<>(x.getUri().query().get(URL).get(), Integer.parseInt(x.getUri().query().get(COUNT).get()))).mapAsync(1, (Pair<String, Integer> pair) -> {
            CompletionStage<Object> cs = Patterns.ask(cache, new ReceiveMessage(pair.getKey()), timeout);
            return cs.thenCompose(res -> {
                if ((Integer)res >= 0) {
                    return CompletableFuture.completedFuture(new Pair<>(pair.getKey(), (long) res));
                }
                Flow<Pair<String,Integer>,Long,NotUsed> flow = Flow.<Pair<String,Integer>>create()
                        .mapConcat(pair2 -> new ArrayList<>(Collections.nCopies(pair2.getValue(), pair2.getKey())))
                        .mapAsync(pair.getValue(), url -> {
                            long startTime = System.currentTimeMillis();
                            Dsl.asyncHttpClient().prepareGet(url).execute();
                            long endTime = System.currentTimeMillis();
                            return CompletableFuture.completedFuture(endTime - startTime);
                        });
                return Source.single(pair).via(flow).toMat(Sink.fold((long)0.0, Long::sum), Keep.right()).run(materializer)
                        .thenApply(sum -> new Pair<>(pair.getKey(), sum / pair.getValue()));
            });
        }).map((Pair<String,Long> pair) -> {
            cache.tell(new StoredMessage(pair.getKey(), pair.getValue()), ActorRef.noSender());
            return HttpResponse.create().withEntity(pair.getValue().toString());
        });
    }
}
