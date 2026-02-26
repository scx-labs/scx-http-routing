package dev.scx.http.routing.path_matcher;

import java.util.Map;
import java.util.regex.Pattern;

final class RegexPathMatcher implements PathMatcher {

    private final Pattern pattern;
    // 这里应该是一个不可变 map.
    private final Map<String, Integer> namedGroups;

    public RegexPathMatcher(String regex) {
        this(Pattern.compile(regex));
    }

    public RegexPathMatcher(Pattern pattern) {
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

        return new RegexPathMatch(groups, namedGroups);
    }

}
