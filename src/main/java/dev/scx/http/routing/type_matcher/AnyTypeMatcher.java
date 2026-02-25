package dev.scx.http.routing.type_matcher;

import dev.scx.http.ScxHttpServerRequest;

/// AnyTypeMatcher
///
/// @author scx567888
/// @version 0.0.1
final class AnyTypeMatcher implements TypeMatcher {

    public static final AnyTypeMatcher ANY_TYPE_MATCHER = new AnyTypeMatcher();

    @Override
    public boolean matches(ScxHttpServerRequest request) {
        return true;
    }

}
