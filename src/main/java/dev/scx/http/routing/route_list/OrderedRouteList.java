package dev.scx.http.routing.route_list;

import dev.scx.http.routing.route.Route;
import dev.scx.http.routing.routing_input.RoutingInput;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/// OrderedRouteList
public final class OrderedRouteList implements RouteList {

    private final ArrayList<RouteEntry> routeEntries;

    public OrderedRouteList() {
        this.routeEntries = new ArrayList<>();
    }

    /// 添加一个路由
    ///
    /// @param order 路由优先级: 数值越小越先匹配, 相同 order 按注册顺序匹配.
    public RouteList add(Route route, int order) {
        var routeEntry = new RouteEntry(route, order);
        int idx = upperBound(routeEntry); // 插到相同 order 段的末尾
        routeEntries.add(idx, routeEntry);
        return this;
    }

    public RouteList add(Route route) {
        return add(route, 0);
    }

    /// 移除一个路由
    public RouteList remove(Route route) {
        routeEntries.removeIf(c -> c.route.equals(route));
        return this;
    }

    /// 只读快照
    public List<RouteEntry> routeEntries() {
        return List.copyOf(routeEntries);
    }

    @Override
    public Iterator<Route> iterator(RoutingInput routingInput) {
        // 这里我们忽略 routingInput, 直接返回全量.
        return new OrderedRouteListIterator(routeEntries.iterator());
    }

    /// 二分法查找, 返回第一个 route.order() > order 的位置 (upper bound).
    /// 这样相同 order 的新 route 会插到已有相同 order 的后面, 保持注册顺序.
    private int upperBound(RouteEntry order) {
        int lo = 0;
        int hi = routeEntries.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            int midOrder = routeEntries.get(mid).order;
            if (midOrder <= order.order) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static final class OrderedRouteListIterator implements Iterator<Route> {

        private final Iterator<RouteEntry> iterator;

        public OrderedRouteListIterator(Iterator<RouteEntry> iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Route next() {
            return iterator.next().route;
        }

    }

    public record RouteEntry(Route route, int order) {

    }

}
