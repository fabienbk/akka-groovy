package actor.msg;

import pojo.Document;

/**
 * Created by fabienbk on 15/09/17.
 */
public class CallVelocityCheckResult extends CallWorkerResult {
    private Document document;

    public CallVelocityCheckResult(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public Status getVelocityStatus() {
        return Status.OK;
    }

    public enum Status {
        OK, KO, INVALID;
    }
}
