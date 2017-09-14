package actor.msg;

import pojo.Document;

/**
 * Created by fabienbk on 15/09/17.
 */
public class CallSmartEyeResult extends CallWorkerResult {
    private final Document document;

    public CallSmartEyeResult(Document document) {
        this.document = document;
    }

}
