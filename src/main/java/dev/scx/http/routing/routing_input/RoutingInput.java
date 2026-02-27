package dev.scx.http.routing.routing_input;

import dev.scx.http.ScxHttpServerRequest;
import dev.scx.http.headers.ScxHttpHeaders;
import dev.scx.http.method.ScxHttpMethod;
import dev.scx.http.parameters.Parameters;
import dev.scx.http.uri.ScxURI;

public interface RoutingInput {

    static RoutingInput of(ScxHttpServerRequest request) {
        return new RequestRoutingInput(request);
    }

    ScxHttpMethod method();

    ScxURI uri();

    ScxHttpHeaders headers();

    //******************** 简化 URI 操作 *******************

    default String path() {
        return uri().path();
    }

    default Parameters<String, String> query() {
        return uri().query();
    }

    default String getQuery(String name) {
        return uri().getQuery(name);
    }

}
