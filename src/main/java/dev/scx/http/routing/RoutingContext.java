package dev.scx.http.routing;

import dev.scx.http.ScxHttpServerRequest;
import dev.scx.http.routing.path_matcher.PathMatch;

import java.util.Map;

/// RoutingContext
///
/// @author scx567888
/// @version 0.0.1
public interface RoutingContext {

    ScxHttpServerRequest request();

    RoutingRequest routingRequest();

    PathMatch pathMatch();

    Map<String, Object> data();

    void next() throws Throwable;

    default <T extends ScxHttpServerRequest> T request(Class<T> requestType) {
        return requestType.cast(request());
    }

}
