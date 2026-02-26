package dev.scx.http.routing.path_matcher;

import static dev.scx.http.routing.path_matcher.AnyPathMatch.ANY_PATH_MATCH;

/// AnyPathMatcher
///
/// @author scx567888
/// @version 0.0.1
final class AnyPathMatcher implements PathMatcher {

    public static final AnyPathMatcher ANY_PATH_MATCHER = new AnyPathMatcher();

    @Override
    public PathMatch match(String path) {
        return ANY_PATH_MATCH;
    }

    @Override
    public String toString() {
        return "ANY";
    }

}
