package dev.scx.http.routing;

import dev.scx.function.Function1Void;
import dev.scx.http.ScxHttpServerRequest;
import dev.scx.http.method.HttpMethod;
import dev.scx.http.routing.method_matcher.MethodMatcher;
import dev.scx.http.routing.path_matcher.PathMatcher;
import dev.scx.http.routing.type_matcher.TypeMatcher;

/// RouteWritable
///
/// @author scx567888
/// @version 0.0.1
public final class RouteBuilder {

    private int order;
    private TypeMatcher typeMatcher;
    private PathMatcher pathMatcher;
    private MethodMatcher methodMatcher;
    private Function1Void<RoutingContext, ?> handler;

    public RouteBuilder() {
        this.order = 0;
        this.typeMatcher = TypeMatcher.any();
        this.pathMatcher = PathMatcher.any();
        this.methodMatcher = MethodMatcher.any();
        this.handler = null;
    }

    public RouteBuilder order(int order) {
        this.order = order;
        return this;
    }

    public RouteBuilder typeMatcher(TypeMatcher typeMatcher) {
        this.typeMatcher = typeMatcher;
        return this;
    }

    public RouteBuilder typeIs(Class<? extends ScxHttpServerRequest> requestType) {
        return typeMatcher(TypeMatcher.is(requestType));
    }

    public RouteBuilder typeNot(Class<? extends ScxHttpServerRequest> requestType) {
        return typeMatcher(TypeMatcher.not(requestType));
    }

    public RouteBuilder pathMatcher(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
        return this;
    }

    public RouteBuilder path(String path) {
        return pathMatcher(PathMatcher.of(path));
    }

    public RouteBuilder pathRegex(String regex) {
        return pathMatcher(PathMatcher.ofRegex(regex));
    }

    public RouteBuilder methodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
        return this;
    }

    public RouteBuilder method(HttpMethod... httpMethods) {
        return methodMatcher(MethodMatcher.of(httpMethods));
    }

    public RouteBuilder handler(Function1Void<RoutingContext, ?> handler) {
        this.handler = handler;
        return this;
    }

    public Route build() {
        return new RouteImpl(
            order,
            typeMatcher,
            pathMatcher,
            methodMatcher,
            handler
        );
    }

}
