package dev.scx.http.routing;

import dev.scx.function.Function1Void;
import dev.scx.http.ScxHttpServerRequest;

import java.util.List;

/// Router
///
/// @author scx567888
/// @version 0.0.1
public interface Router extends Function1Void<ScxHttpServerRequest, Throwable> {

    static Router of() {
        return new RouterImpl();
    }

    /// 添加一个路由
    /// 路由按 [Route#order()] 从小到大匹配:
    ///
    /// - order 越小, 匹配优先级越高.
    /// - order 相同的路由, 按注册顺序匹配.
    Router add(Route route);

    /// 移除一个路由
    Router remove(Route route);

    /// 只读快照
    List<Route> routes();

}
