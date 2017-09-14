package actor;

import actor.msg.*;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import pojo.Identity;

import java.util.UUID;

/**
 * Created by Fabien Benoit-Koch on 13/09/2017.
 */
public class IdentityActor extends AbstractActor {

    private Identity identity = new Identity(UUID.randomUUID());

    private ActorRef scriptManager;

    public IdentityActor() {
        this.scriptManager = context().actorOf(ScriptActor.props());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CallWorker.class, this::callWorker)
                .match(SubmitPassport.class, this::handlePassport)
                .build();
    }

    private void handlePassport(SubmitPassport msg) {
        scriptManager.tell(new RunScript("src/main/groovy/ValidatePassport.groovy", self(), identity, msg.getPassport()), self());
    }

    private void callWorker(CallWorker callWorker) throws InterruptedException {
        if (callWorker instanceof CallSmartEye) {
            Thread.sleep(1000L);
            sender().tell(new CallSmartEyeResult(callWorker.getDocument()), self());
        }
        else if (callWorker instanceof CallVelocityCheck) {
            Thread.sleep(500L);
            sender().tell(new CallVelocityCheckResult(callWorker.getDocument()), self());
        }
    }

    public static Props props() {
        return Props.create(IdentityActor.class);
    }
}
