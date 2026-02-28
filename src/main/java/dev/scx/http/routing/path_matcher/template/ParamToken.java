package dev.scx.http.routing.path_matcher.template;

/// ParamToken
///
/// @author scx567888
/// @version 0.0.1
record ParamToken(String name) implements Token {

    ParamToken {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("param name must not be empty");
        }
        if ("*".equals(name)) {
            throw new IllegalArgumentException("param name can not be '*'");
        }
    }

}
