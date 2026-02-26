package dev.scx.http.routing;

import dev.scx.http.ScxHttpServerRequest;

import java.util.ArrayList;
import java.util.List;

/// RouterImpl
///
/// @author scx567888
/// @version 0.0.1
public final class RouterImpl implements Router {

    private final ArrayList<Route> routes;

    public RouterImpl() {
        this.routes = new ArrayList<>();
    }

    @Override
    public Router add(Route route) {
        int idx = upperBound(route.order()); // 插到相同 order 段的末尾
        routes.add(idx, route);
        return this;
    }

    @Override
    public Router remove(Route route) {
        routes.remove(route);
        return this;
    }

    @Override
    public List<Route> routes() {
        return List.copyOf(routes);
    }

    @Override
    public void apply(ScxHttpServerRequest serverRequest) throws Throwable {
        new RoutingContextImpl(routes, serverRequest).next();
    }

    /// 二分法查找, 返回第一个 route.order() > order 的位置 (upper bound).
    /// 这样相同 order 的新 route 会插到已有相同 order 的后面, 保持注册顺序.
    private int upperBound(int order) {
        int lo = 0;
        int hi = routes.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            int midOrder = routes.get(mid).order();
            if (midOrder <= order) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

}
