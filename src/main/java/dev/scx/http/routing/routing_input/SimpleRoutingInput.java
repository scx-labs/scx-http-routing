package dev.scx.http.routing.routing_input;

import dev.scx.http.uri.ScxURI;

/// SimpleRoutingInput
///
/// @author scx567888
/// @version 0.0.1
record SimpleRoutingInput(ScxURI uri) implements RoutingInput {

    SimpleRoutingInput {
        if (uri == null) {
            throw new NullPointerException("uri must not be null");
        }
    }

}
