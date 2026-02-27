package dev.scx.http.routing.request_matcher;

import dev.scx.http.ScxHttpServerRequest;

/// TypeNotRequestMatcher
///
/// @author scx567888
/// @version 0.0.1
final class TypeNotRequestMatcher implements RequestMatcher {

    private final Class<? extends ScxHttpServerRequest> requestType;

    public TypeNotRequestMatcher(Class<? extends ScxHttpServerRequest> requestType) {
        if (requestType == null) {
            throw new NullPointerException("requestType must not be null");
        }
        this.requestType = requestType;
    }

    @Override
    public boolean matches(ScxHttpServerRequest request) {
        return !requestType.isInstance(request);
    }

    @Override
    public String toString() {
        return "typeNot(" + requestType.getSimpleName() + ")";
    }

}
