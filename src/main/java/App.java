import actor.IdentityActor;
import actor.ScriptActor;
import actor.msg.SubmitPassport;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import pojo.Passport;

import static akka.actor.ActorRef.noSender;

/**
 * Created by Fabien Benoit-Koch on 13/09/2017.
 */
public class App {

    public static void main(String[] args) {

        ActorSystem system = ActorSystem.create();
        ActorRef identityActor = system.actorOf(IdentityActor.props());

        Passport passport = new Passport("Fabien Benoit-Koch", "1234567890", new byte[10]);

        identityActor.tell(new SubmitPassport(passport), noSender());

    }
}
