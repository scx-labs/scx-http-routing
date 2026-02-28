package dev.scx.http.routing.routing_context;

import dev.scx.http.ScxHttpServerRequest;
import dev.scx.http.routing.path_matcher.PathMatch;
import dev.scx.http.routing.route_list.RouteList;
import dev.scx.http.routing.routing_input.RoutingInput;

import java.util.HashMap;
import java.util.Map;

/// RoutingContext
///
/// @author scx567888
/// @version 0.0.1
public interface RoutingContext {

    static RoutingContext of(RouteList routeList, ScxHttpServerRequest request, RoutingInput routingRequest, Map<String, Object> data) {
        return new RoutingContextImpl(routeList, request, routingRequest, data);
    }

    ScxHttpServerRequest request();

    RoutingInput routingInput();

    PathMatch pathMatch();

    Map<String, Object> data();

    void next() throws Throwable;

    default <T extends ScxHttpServerRequest> T request(Class<T> requestType) {
        return requestType.cast(request());
    }

}
