package dev.scx.http.routing.route_table;

import dev.scx.http.routing.Route;
import dev.scx.http.routing.routing_input.RoutingInput;

import java.util.Collection;
import java.util.Iterator;

/// BasicRouteTable
///
/// @author scx567888
/// @version 0.0.1
record BasicRouteTable(Collection<Route> routes) implements RouteTable {

    public BasicRouteTable add(Route route){
        routes.add(route);
        return this;
    }

    public BasicRouteTable remove(Route route){
        routes.remove(route);
        return this;
    }

    public int size(){
        return routes.size();
    }

    @Override
    public Iterator<Route> candidates(RoutingInput routingInput) {
        // 返回全量
        return routes.iterator();
    }

}
