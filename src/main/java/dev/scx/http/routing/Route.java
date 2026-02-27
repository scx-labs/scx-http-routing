package dev.scx.http.routing;

import dev.scx.function.Function1Void;
import dev.scx.http.routing.method_matcher.MethodMatcher;
import dev.scx.http.routing.path_matcher.PathMatcher;
import dev.scx.http.routing.request_matcher.RequestMatcher;

/// Route 路由只保存状态不做行为处理
///
/// @author scx567888
/// @version 0.0.1
public interface Route {

    /// 路由优先级: 数值越小越先匹配, 相同 order 按注册顺序匹配.
    int order();

    RequestMatcher requestMatcher();

    PathMatcher pathMatcher();

    MethodMatcher methodMatcher();

    Function1Void<RoutingContext, ?> handler();

}
