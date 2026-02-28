package dev.scx.http.routing.path_matcher;

import dev.scx.http.routing.path_matcher.template.TemplatePathMatcher;

import java.util.regex.Pattern;

import static dev.scx.http.routing.path_matcher.AnyPathMatcher.ANY_PATH_MATCHER;

/// PathMatcher
///
/// @author scx567888
/// @version 0.0.1
public interface PathMatcher {

    static PathMatcher any() {
        return ANY_PATH_MATCHER;
    }

    static PathMatcher ofExact(String exactPath) {
        return new ExactPathMatcher(exactPath);
    }

    static PathMatcher ofTemplate(String template) {
        return new TemplatePathMatcher(template);
    }

    static PathMatcher ofRegex(String regex) {
        if (regex == null) {
            throw new NullPointerException("regex must not be null");
        }
        return new RegexPathMatcher(Pattern.compile(regex));
    }

    static PathMatcher ofRegex(Pattern pattern) {
        return new RegexPathMatcher(pattern);
    }

    /// 匹配失败返回 null
    /// 参数永远 是 "/" 起始
    PathMatch match(String path);

}
