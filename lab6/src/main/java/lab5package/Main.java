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
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import org.apache.zookeeper.*;
import akka.http.javadsl.server.Directives;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.CompletionStage;

public class Main {
    public static ZooKeeper zooKeeper;
    public static final Duration timeout = Duration.ofSeconds(3);
    public static ActorRef actorRef;
    public static final String URL = "url";
    public static final String COUNT = "count";
    public static Http http;

    public static Watcher watcher = watchedEvent -> {
        if (watchedEvent.getType() == Watcher.Event.EventType.NodeCreated) {
            ArrayList<String> servers = new ArrayList<>();
            try {
                for (String s: zooKeeper.getChildren("/servers", null)) {
                    String port = new String(zooKeeper.getData("/servers/" + s, false, null));
                    servers.add(port);
                }
                actorRef.tell(new ServersList(servers), ActorRef.noSender());
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) throws IOException {
        ActorSystem actorSystem = ActorSystem.create("routes");
        http = Http.get(actorSystem);
        actorRef = actorSystem.actorOf(Props.create(ConfigurationStoreActor.class));
        ActorMaterializer actorMaterializer = ActorMaterializer.create(actorSystem);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = createFlow(...);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8080),
                actorMaterializer
        );
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> actorSystem.terminate());
        initZooKeeper();
    }



    public static void initZooKeeper() throws IOException, KeeperException, InterruptedException {
        zooKeeper = new ZooKeeper("localhost:8080", (int) (1000 * timeout.getSeconds()), watcher);
        zooKeeper.create("/servers8080", "8080".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        watcher.process(new WatchedEvent(Watcher.Event.EventType.NodeCreated, Watcher.Event.KeeperState.SyncConnected, ""));
    }

    public static Route createFlow() {
        return Directives.route(Directives.get(() ->
                Directives.parameter(URL, url ->
                        Directives.parameter(COUNT, count -> {
                            if (Integer.parseInt(count) <= 0) {
                                return Directives.completeWithFuture(http.singleRequest(HttpRequest.create(url)));
                            }
                            else {
                                return Directives.completeWithFuture(Patterns.ask(actorRef, new ServerQuery(), timeout)
                                .thenApply(next -> next).thenCompose(next -> http.singleRequest(HttpRequest.create(
                                        String.format("http://%s:%s?%s=%s&%s=%d", "localhost", next, URL, url, COUNT, Integer.parseInt(count) - 1)
                                        ))));
                            }
                        }))));
    }
}

