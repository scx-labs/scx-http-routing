package dev.scx.http.routing;

import dev.scx.function.Function1Void;
import dev.scx.http.ScxHttpServerRequest;
import dev.scx.http.method.ScxHttpMethod;
import dev.scx.http.routing.method_matcher.MethodMatcher;
import dev.scx.http.routing.path_matcher.PathMatcher;
import dev.scx.http.routing.request_matcher.RequestMatcher;
import dev.scx.http.routing.route.Route;
import dev.scx.http.routing.route.RouteImpl;
import dev.scx.http.routing.routing_context.RoutingContext;

// todo 需要重构.

/// RouteBuilder
///
/// @author scx567888
/// @version 0.0.1
public final class RouteBuilder {

    private int order;
    private RequestMatcher typeMatcher;
    private PathMatcher pathMatcher;
    private MethodMatcher methodMatcher;
    private Function1Void<RoutingContext, ?> handler;

    private RouteBuilder() {
        this.order = 0;
        this.typeMatcher = RequestMatcher.any();
        this.pathMatcher = PathMatcher.any();
        this.methodMatcher = MethodMatcher.any();
        this.handler = null;
    }

    public static RouteBuilder route() {
        return new RouteBuilder();
    }

    int order() {
        return this.order;
    }

    // ***************** order 相关 *********************
    public RouteBuilder order(int order) {
        this.order = order;
        return this;
    }

    // ***************** typeMatcher 相关 *********************
    public RouteBuilder typeMatcher(RequestMatcher typeMatcher) {
        this.typeMatcher = typeMatcher;
        return this;
    }

    public RouteBuilder typeIs(Class<? extends ScxHttpServerRequest> requestType) {
        return typeMatcher(RequestMatcher.typeIs(requestType));
    }

    public RouteBuilder typeNot(Class<? extends ScxHttpServerRequest> requestType) {
        return typeMatcher(RequestMatcher.typeNot(requestType));
    }

    // ***************** pathMatcher 相关 *********************
    public RouteBuilder pathMatcher(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
        return this;
    }

    public RouteBuilder path(String path) {
        return pathMatcher(PathMatcher.ofTemplate(path));
    }

    public RouteBuilder pathRegex(String regex) {
        return pathMatcher(PathMatcher.ofRegex(regex));
    }

    // ***************** methodMatcher 相关 *********************
    public RouteBuilder methodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
        return this;
    }

    public RouteBuilder method(ScxHttpMethod... httpMethods) {
        return methodMatcher(MethodMatcher.of(httpMethods));
    }

    // ***************** handler 相关 *********************
    public RouteBuilder handler(Function1Void<RoutingContext, ?> handler) {
        this.handler = handler;
        return this;
    }

    // ***************** build *********************
    public Route build() {
        return new RouteImpl(
            typeMatcher,
            pathMatcher,
            methodMatcher,
            handler
        );
    }

}
