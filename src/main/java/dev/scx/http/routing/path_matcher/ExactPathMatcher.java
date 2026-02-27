package dev.scx.http.routing.path_matcher;

import static dev.scx.http.routing.path_matcher.EmptyPathMatch.EMPTY_PATH_MATCH;

/// ExactPathMatcher
///
/// @author scx567888
/// @version 0.0.1
final class ExactPathMatcher implements PathMatcher {

    private final String exactPath;

    public ExactPathMatcher(String exactPath) {
        this.exactPath = exactPath;
    }

    @Override
    public PathMatch match(String path) {
        if (!exactPath.equals(path)) {
            return null;
        }
        return EMPTY_PATH_MATCH;
    }

    @Override
    public String toString() {
        return "exact(" + exactPath + ")";
    }

}
