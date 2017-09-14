package api;

import actor.msg.CallSmartEye;
import actor.msg.CallVelocityCheck;
import actor.msg.CallWorker;
import akka.actor.ActorRef;
import akka.pattern.PatternsCS;
import pojo.Document;
import pojo.Identity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Created by fabienbk on 14/09/17.
 */
public class AkkaServiceAPI implements ServiceAPI {

    private ActorRef targetActor;
    private final Identity identity;
    private final Document document;

    public List<CompletableFuture<Void>> futures = new ArrayList<>();

    public AkkaServiceAPI(ActorRef targetActor, Identity identity, Document document) {
        this.targetActor = targetActor;
        this.identity = identity;
        this.document = document;
    }

    @Override
    public void callAsync(Service service, Consumer<Object> callback) {
        CompletableFuture<Void> cf = PatternsCS.ask(targetActor, createMessage(service), 1000L).toCompletableFuture().thenAccept(callback);
        futures.add(cf);
    }

    private Object createMessage(Service service) {
        switch (service) {
            case SMARTEYE: return new CallSmartEye(document);
            case VELOCITY_CHECK: return new CallVelocityCheck(document);
        }
    }

    @Override
    public Object call(Service service) {
        return PatternsCS.ask(targetActor, new CallWorker(), 1000L).toCompletableFuture().join();
    }

    /**
     * Wait for pending operations
     */
    @Override
    public void waitForCompletion() {
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
        futures.clear();
    }
}
