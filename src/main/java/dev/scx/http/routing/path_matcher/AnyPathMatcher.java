package dev.scx.http.routing.path_matcher;

final class AnyPathMatcher implements PathMatcher {

    public static final AnyPathMatcher ANY_PATH_MATCHER = new AnyPathMatcher();

    public static final PathMatch EMPTY_PATH_MATCH = new AnyPathMatch();

    @Override
    public PathMatch match(String path) {
        return EMPTY_PATH_MATCH;
    }

}
