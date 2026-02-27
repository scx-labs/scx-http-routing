package dev.scx.http.routing.route_list;

import dev.scx.http.routing.route.Route;
import dev.scx.http.routing.routing_input.RoutingInput;

import java.util.Iterator;

public interface RouteList {

    /// 返回针对该 routingInput 的候选路由序列 (按尝试顺序).
    /// 实现可以进行安全的预筛选/索引优化, 但不得遗漏任何可能匹配成功的路由.
    Iterator<Route> iterator(RoutingInput routingInput);

}
