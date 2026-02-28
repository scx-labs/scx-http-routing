package dev.scx.http.routing.route_table;

import dev.scx.http.routing.Route;
import dev.scx.http.routing.routing_input.RoutingInput;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/// OrderedRouteTable
///
/// @author scx567888
/// @version 0.0.1
public final class OrderedRouteTable implements RouteTable {

    private final ArrayList<RouteEntry> routeEntries;

    public OrderedRouteTable() {
        this.routeEntries = new ArrayList<>();
    }

    /// 添加一个路由
    ///
    /// @param order 路由优先级: 数值越小越先匹配, 相同 order 按注册顺序匹配.
    public OrderedRouteTable add(int order, Route route) {
        var routeEntry = new RouteEntry(order, route);
        int idx = upperBound(routeEntry.order); // 计算索引.
        routeEntries.add(idx, routeEntry); // 插到相同 order 段的末尾
        return this;
    }

    /// 添加一个路由, 默认 order = 0.
    public OrderedRouteTable add(Route route) {
        return add(0, route);
    }

    /// 移除所有匹配的路由
    public OrderedRouteTable remove(Route route) {
        routeEntries.removeIf(c -> c.route.equals(route));
        return this;
    }

    /// 只读快照
    public List<RouteEntry> routeEntries() {
        return List.copyOf(routeEntries);
    }

    @Override
    public Iterator<Route> candidates(RoutingInput routingInput) {
        // 这里我们忽略 routingInput, 直接返回全量.
        return new OrderedRouteTableIterator(routeEntries.iterator());
    }

    /// 二分法查找, 返回第一个 entry.order() > order 的位置 (upper bound).
    /// 这样相同 order 的新 route 会插到已有相同 order 的后面, 保持注册顺序.
    private int upperBound(int order) {
        int lo = 0;
        int hi = routeEntries.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            int midOrder = routeEntries.get(mid).order;
            if (midOrder <= order) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    /// 迭代器
    private record OrderedRouteTableIterator(Iterator<RouteEntry> iterator) implements Iterator<Route> {

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Route next() {
            return iterator.next().route;
        }

    }

    /// 内部存储单元
    public record RouteEntry(int order, Route route) {

    }

}
