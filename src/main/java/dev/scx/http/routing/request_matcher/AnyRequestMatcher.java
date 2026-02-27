package dev.scx.http.routing.request_matcher;

import dev.scx.http.ScxHttpServerRequest;

/// AnyRequestMatcher
///
/// @author scx567888
/// @version 0.0.1
final class AnyRequestMatcher implements RequestMatcher {

    public static final AnyRequestMatcher ANY_REQUEST_MATCHER = new AnyRequestMatcher();

    @Override
    public boolean matches(ScxHttpServerRequest request) {
        return true;
    }

    @Override
    public String toString() {
        return "ANY";
    }

}
