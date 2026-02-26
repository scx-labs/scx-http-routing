package dev.scx.http.routing.method_matcher;

import dev.scx.http.method.ScxHttpMethod;

import java.util.Set;
import java.util.stream.Collectors;

/// MultiMethodMatcher
///
/// @author scx567888
/// @version 0.0.1
final class MultiMethodMatcher implements MethodMatcher {

    private final Set<ScxHttpMethod> methods;

    public MultiMethodMatcher(ScxHttpMethod... methods) {
        this.methods = Set.of(methods);
    }

    @Override
    public boolean matches(ScxHttpMethod method) {
        return methods.contains(method);
    }

    @Override
    public String toString() {
        return this.methods.stream()
            .map(ScxHttpMethod::value)
            .sorted()
            .collect(Collectors.joining("|"));
    }

}
