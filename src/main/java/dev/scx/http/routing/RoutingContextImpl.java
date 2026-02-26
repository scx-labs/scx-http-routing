package dev.scx.http.routing;

import dev.scx.http.ScxHttpServerRequest;
import dev.scx.http.exception.HttpException;
import dev.scx.http.exception.MethodNotAllowedException;
import dev.scx.http.exception.NotFoundException;
import dev.scx.http.routing.path_matcher.PathMatch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/// RoutingContextImpl
///
/// @author scx567888
/// @version 0.0.1
public final class RoutingContextImpl implements RoutingContext {

    private final Iterator<Route> iter;
    private final ScxHttpServerRequest request;
    private final Map<String, Object> data;
    private PathMatch nowPathMatch;

    RoutingContextImpl(Iterable<Route> routes, ScxHttpServerRequest request) {
        this.iter = routes.iterator();
        this.request = request;
        this.data = new HashMap<>();
    }

    @Override
    public ScxHttpServerRequest request() {
        return request;
    }

    @Override
    public PathMatch pathMatch() {
        return nowPathMatch;
    }

    @Override
    public Map<String, Object> data() {
        return data;
    }

    /// 任何路径都不匹配 抛出 404.
    /// 存在路径匹配, 但是任何方法都不匹配 抛出 405.
    @Override
    public void next() throws Throwable {
        HttpException e = new NotFoundException();

        while (iter.hasNext()) {
            var route = iter.next();

            // 1, 优先匹配类型
            var typeMatcherResult = route.typeMatcher().matches(request);

            if (!typeMatcherResult) {
                continue;
            }

            // 2, 然后匹配路径
            var pathMatch = route.pathMatcher().match(request.path());

            // 匹配不到就下一次
            if (pathMatch == null) {
                continue;
            }

            this.nowPathMatch = pathMatch;

            // 3, 最后匹配方法
            var methodMatchResult = route.methodMatcher().matches(request.method());

            // 匹配方法失败.
            // 这里不直接抛出异常, 因为后续可能其他路由会匹配成功,
            if (!methodMatchResult) {
                e = new MethodNotAllowedException();
                continue;
            }

            route.handler().apply(this);

            return;

        }

        throw e;
    }

}
