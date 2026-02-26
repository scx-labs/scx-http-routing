package dev.scx.http.routing;

import dev.scx.function.Function1Void;
import dev.scx.http.ScxHttpServerRequest;
import dev.scx.http.method.HttpMethod;
import dev.scx.http.routing.type_matcher.TypeMatcher;

import static dev.scx.http.routing.type_matcher.TypeMatcher.is;
import static dev.scx.http.routing.type_matcher.TypeMatcher.not;

/// RouteWritable
///
/// @author scx567888
/// @version 0.0.1
public interface RouteWritable extends Route {

    RouteWritable type(TypeMatcher typeMatcher);

    RouteWritable path(String path);

    RouteWritable pathRegex(String path);

    RouteWritable method(HttpMethod... httpMethods);

    RouteWritable order(int order);

    RouteWritable handler(Function1Void<RoutingContext, ?> handler);

    default RouteWritable typeIs(Class<? extends ScxHttpServerRequest> requestType) {
        return type(is(requestType));
    }

    default RouteWritable typeNot(Class<? extends ScxHttpServerRequest> requestType) {
        return type(not(requestType));
    }

}
