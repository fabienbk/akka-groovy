package actor.msg;

import akka.actor.ActorRef;
import pojo.Document;
import pojo.Identity;

/**
 * Created by Fabien Benoit-Koch on 13/09/2017.
 */
public class RunScript {

    private String file;
    private ActorRef targetActor;
    private Identity identity;
    private Document document;

    public RunScript(String file, ActorRef targetActor, Identity identity, Document document) {
        this.file = file;
        this.targetActor = targetActor;
        this.identity = identity;
        this.document = document;
    }

    public ActorRef getTargetActor() {
        return targetActor;
    }

    public String getFile() {
        return file;
    }

    public Identity getIdentity() {
        return identity;
    }

    public Document getSubmittedDocument() {
        return document;
    }
}
