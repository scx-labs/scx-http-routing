package dev.scx.http.routing;

import dev.scx.http.ScxHttpServerRequest;

public interface TypeMatcher {

    TypeMatcher ANY = _ -> true;

    static TypeMatcher any() {
        return ANY;
    }

    static TypeMatcher is(Class<? extends ScxHttpServerRequest> requestType) {
        return requestType::isInstance;
    }

    static TypeMatcher not(Class<? extends ScxHttpServerRequest> requestType) {
        return request -> !requestType.isInstance(request);
    }

    boolean matches(ScxHttpServerRequest request);

}
