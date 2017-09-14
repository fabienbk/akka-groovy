package api;

import java.util.function.Consumer;

/**
 * Created by Fabien Benoit-Koch on 13/09/2017.
 */
public interface ServiceAPI {

    void callAsync(Service service, Consumer<Object> callback);

    Object call(Service service);

    void waitForCompletion();
}
