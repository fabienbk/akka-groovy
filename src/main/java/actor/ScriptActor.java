package actor;

import actor.msg.RunScript;
import akka.actor.AbstractActor;
import akka.actor.Props;
import api.AkkaServiceAPI;
import api.ServiceAPI;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Fabien Benoit-Koch on 13/09/2017.
 */
public class ScriptActor extends AbstractActor {

    private static ScriptEngineManager factory = new ScriptEngineManager();

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(RunScript.class, this::runScript).build();
    }

    private void runScript(RunScript runScriptMessage) throws Exception {
        Invocable invocableEngine = getEngine(runScriptMessage.getFile());
        ServiceAPI serviceAPI = new AkkaServiceAPI(runScriptMessage.getTargetActor());
        invocableEngine.invokeFunction("main", serviceAPI, runScriptMessage.getIdentity(), runScriptMessage.getSubmittedDocument());
    }

    private Invocable getEngine(String file) throws ScriptException, FileNotFoundException {
        ScriptEngine engine = factory.getEngineByName("groovy");
        engine.eval(new FileReader(file));
        return (Invocable) engine;
    }

    public static Props props() {
        return Props.create(ScriptActor.class);
    }

}
