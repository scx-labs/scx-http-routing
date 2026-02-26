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
    Router add(Route route);

    /// 移除一个路由
    Router remove(Route route);

    /// 只读快照
    List<Route> routes();

}
