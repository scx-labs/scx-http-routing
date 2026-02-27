package dev.scx.http.routing;

import dev.scx.function.Function1Void;
import dev.scx.http.ScxHttpServerRequest;
import dev.scx.http.routing.route.Route;
import dev.scx.http.routing.route_list.OrderedRouteList;
import dev.scx.http.routing.route_list.RouteList;
import dev.scx.http.routing.routing_context.RoutingContext;
import dev.scx.http.routing.routing_input.RoutingInput;

import java.util.HashMap;

import static dev.scx.http.method.HttpMethod.GET;
import static dev.scx.http.method.HttpMethod.POST;

// todo 还有存在的必要吗? 还是留着做一个门面类?

/// Router
///
/// @author scx567888
/// @version 0.0.1
public class Router implements Function1Void<ScxHttpServerRequest, Throwable> {

    private final OrderedRouteList routes = new OrderedRouteList();

    public static Router of() {
        return new Router();
    }

    public Router add(RouteBuilder builder) {
        Route route = builder.build();
        routes.add(route, builder.order());
        return this;
    }

    public Router remove(Route route) {
        routes.remove(route);
        return this;
    }

    public RouteList routeList() {
        return routes;
    }

    @Override
    public void apply(ScxHttpServerRequest request) throws Throwable {
        var input = RoutingInput.of(request);
        var data = new HashMap<String, Object>();
        RoutingContext.of(routes, request, input, data).next();
    }

    public Router get(String path, Function1Void<RoutingContext, ?> handler) {
        return add(RouteBuilder.route().method(GET).path(path).handler(handler));
    }

    public Router post(String path, Function1Void<RoutingContext, ?> handler) {
        return add(RouteBuilder.route().method(POST).path(path).handler(handler));
    }

    public Router any(String path, Function1Void<RoutingContext, ?> handler) {
        return add(RouteBuilder.route().path(path).handler(handler));
    }

}
