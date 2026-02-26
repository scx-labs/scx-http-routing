package dev.scx.http.routing.type_matcher;

import dev.scx.http.ScxHttpServerRequest;

/// NotTypeMatcher
///
/// @author scx567888
/// @version 0.0.1
final class NotTypeMatcher implements TypeMatcher {

    private final Class<? extends ScxHttpServerRequest> requestType;

    public NotTypeMatcher(Class<? extends ScxHttpServerRequest> requestType) {
        if (requestType == null) {
            throw new NullPointerException("requestType must not be null");
        }
        this.requestType = requestType;
    }

    @Override
    public boolean matches(ScxHttpServerRequest request) {
        return !requestType.isInstance(request);
    }

}
