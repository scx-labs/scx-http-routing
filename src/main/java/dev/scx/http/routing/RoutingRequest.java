package dev.scx.http.routing;

import dev.scx.http.method.ScxHttpMethod;
import dev.scx.http.parameters.Parameters;
import dev.scx.http.uri.ScxURI;

public interface RoutingRequest {

    ScxHttpMethod method();

    ScxURI uri();

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
