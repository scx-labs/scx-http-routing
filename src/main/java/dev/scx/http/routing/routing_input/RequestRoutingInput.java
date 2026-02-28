package dev.scx.http.routing.routing_input;

import dev.scx.http.ScxHttpServerRequest;
import dev.scx.http.uri.ScxURI;

/// RequestRoutingInput
///
/// @author scx567888
/// @version 0.0.1
final class RequestRoutingInput implements RoutingInput {

    private final ScxHttpServerRequest request;

    public RequestRoutingInput(ScxHttpServerRequest request) {
        if (request == null) {
            throw new NullPointerException("request must not be null");
        }
        this.request = request;
    }

    @Override
    public ScxURI uri() {
        return request.uri();
    }

}
