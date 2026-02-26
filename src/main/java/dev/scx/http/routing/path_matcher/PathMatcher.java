package dev.scx.http.routing.path_matcher;

import static dev.scx.http.routing.path_matcher.AnyPathMatcher.ANY_PATH_MATCHER;

/// PathMatcher
///
/// @author scx567888
/// @version 0.0.1
public interface PathMatcher {

    static PathMatcher any() {
        return ANY_PATH_MATCHER;
    }

    static PathMatcher of(String path) {
        return new TemplatePathMatcher(path);
    }

    static PathMatcher ofRegex(String regex) {
        return new RegexPathMatcher(regex);
    }

    /// 匹配失败返回 null
    PathMatch match(String path);

}
