package dev.scx.http.routing.type_matcher;

import dev.scx.http.ScxHttpServerRequest;

import static dev.scx.http.routing.type_matcher.AnyTypeMatcher.ANY_TYPE_MATCHER;

/// TypeMatcher
///
/// @author scx567888
/// @version 0.0.1
public interface TypeMatcher {

    static TypeMatcher any() {
        return ANY_TYPE_MATCHER;
    }

    static TypeMatcher is(Class<? extends ScxHttpServerRequest> requestType) {
        return new IsTypeMatcher(requestType);
    }

    static TypeMatcher not(Class<? extends ScxHttpServerRequest> requestType) {
        return new NotTypeMatcher(requestType);
    }

    boolean matches(ScxHttpServerRequest request);

}
