package dev.scx.http.routing.routing_input;

import dev.scx.http.ScxHttpServerRequest;
import dev.scx.http.headers.ScxHttpHeaders;
import dev.scx.http.method.ScxHttpMethod;
import dev.scx.http.uri.ScxURI;

final class RequestRoutingInput implements RoutingInput {

    private final ScxHttpServerRequest request;

    public RequestRoutingInput(ScxHttpServerRequest request) {
        this.request = request;
    }

    @Override
    public ScxHttpMethod method() {
        return request.method();
    }

    @Override
    public ScxURI uri() {
        return request.uri();
    }

    @Override
    public ScxHttpHeaders headers() {
        return request.headers();
    }

}
