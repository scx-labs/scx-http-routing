package dev.scx.http.routing.path_matcher;

import static dev.scx.http.routing.path_matcher.EmptyPathMatch.EMPTY_PATH_MATCH;

/// AnyPathMatcher
///
/// @author scx567888
/// @version 0.0.1
final class AnyPathMatcher implements PathMatcher {

    public static final AnyPathMatcher ANY_PATH_MATCHER = new AnyPathMatcher();

    @Override
    public PathMatch match(String path) {
        return EMPTY_PATH_MATCH;
    }

    @Override
    public String toString() {
        return "ANY";
    }

}
