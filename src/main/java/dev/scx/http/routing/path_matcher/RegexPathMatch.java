package dev.scx.http.routing.path_matcher;

import java.util.Map;

/// RegexPathMatcher
///
/// @author scx567888
/// @version 0.0.1
final class RegexPathMatch implements PathMatch {

    private final String[] groups;
    private final Map<String, Integer> namedGroups;

    public RegexPathMatch(String[] groups, Map<String, Integer> namedGroups) {
        this.groups = groups;
        this.namedGroups = namedGroups;
    }

    @Override
    public String capture(int index) {
        if (index > groups.length - 1) {
            return null;
        }
        return groups[index];
    }

    @Override
    public String capture(String name) {
        var i = namedGroups.get(name);
        if (i == null) {
            return null;
        }
        // 这里注意 namedGroups 是按照 1 起始, 所以这里要 - 1.
        return capture(i - 1);
    }

}
