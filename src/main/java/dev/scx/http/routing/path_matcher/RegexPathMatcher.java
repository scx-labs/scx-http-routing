package dev.scx.http.routing.path_matcher;

import java.util.Map;
import java.util.regex.Pattern;

/// RegexPathMatcher
///
/// @author scx567888
/// @version 0.0.1
final class RegexPathMatcher implements PathMatcher {

    private final Pattern pattern;
    private final Map<String, Integer> namedGroups;

    public RegexPathMatcher(Pattern pattern) {
        if (pattern == null) {
            throw new NullPointerException("pattern must not be null");
        }
        this.pattern = pattern;
        this.namedGroups = pattern.namedGroups();
    }

    @Override
    public PathMatch match(String path) {
        var matcher = pattern.matcher(path);
        if (!matcher.matches()) {
            return null;
        }

        var groups = new String[matcher.groupCount()];

        for (int i = 0; i < groups.length; i++) {
            groups[i] = matcher.group(i + 1); // 注意 group 索引是 1 起始.
        }

        // pattern.namedGroups() 返回的是不可变的 Map 这里可以安全传递.
        return new RegexPathMatch(groups, namedGroups);
    }

    @Override
    public String toString() {
        return "regex(" + pattern.pattern() + ")";
    }

}
