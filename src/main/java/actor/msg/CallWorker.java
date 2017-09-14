package actor.msg;

import pojo.Document;

/**
 * Created by Fabien Benoit-Koch on 13/09/2017.
 */
public class CallWorker {
    protected final Document document;

    public CallWorker(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }
}