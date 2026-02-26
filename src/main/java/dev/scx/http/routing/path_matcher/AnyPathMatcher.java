package dev.scx.http.routing.path_matcher;

/// AnyPathMatcher
///
/// @author scx567888
/// @version 0.0.1
final class AnyPathMatcher implements PathMatcher {

    public static final AnyPathMatcher ANY_PATH_MATCHER = new AnyPathMatcher();

    public static final AnyPathMatch ANY_PATH_MATCH = new AnyPathMatch();

    @Override
    public PathMatch match(String path) {
        return ANY_PATH_MATCH;
    }

    @Override
    public String toString() {
        return "ANY";
    }

}
