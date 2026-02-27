package dev.scx.http.routing.path_matcher;

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
        return EmptyPathMatch.EMPTY_PATH_MATCH;
    }

    @Override
    public String toString() {
        return "exact(" + exactPath + ")";
    }

}
