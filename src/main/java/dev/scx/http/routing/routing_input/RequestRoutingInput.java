package dev.scx.http.routing.routing_input;

import dev.scx.http.ScxHttpServerRequest;
import dev.scx.http.uri.ScxURI;

final class RequestRoutingInput implements RoutingInput {

    private final ScxHttpServerRequest request;

    public RequestRoutingInput(ScxHttpServerRequest request) {
        this.request = request;
    }

    @Override
    public ScxURI uri() {
        return request.uri();
    }

}
