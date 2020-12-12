package lab4package;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.pattern.Patterns;
import akka.routing.BalancingPool;
import akka.util.Timeout;
import scala.concurrent.Await;

import java.time.Duration;
import scala.concurrent.Future;

public class RouteActor extends AbstractActor {
    public static final int POOL_SIZE = 3;
    public static final Timeout FUTURE_TIMEOUT = Timeout.create(Duration.ofSeconds(5));

    public ActorRef storageActor;
    public ActorRef executeBalancingActor;

    @Override
    public void preStart() {
        storageActor = getContext().actorOf(Props.create(ResultsStoreActor.class));
        executeBalancingActor = getContext().actorOf(new BalancingPool(POOL_SIZE)
                .props(Props.create(TestExecuteActor.class)));

    }

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoredMessage.class, message -> storageActor.tell(message, self()))
                .match(ExecuteMessage.class, message -> executeBalancingActor.tell(message, self()))
                .match(RetrievedMessage.class, message -> {
                    Future<Object> future = Patterns.ask(storageActor, message, FUTURE_TIMEOUT);
                    sender().tell(Await.result(future, FUTURE_TIMEOUT.duration()), self());
                }).build();
    }
}
