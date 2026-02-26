package dev.scx.http.routing.path_matcher;

import java.util.Map;

final class RegexPathMatch implements PathMatch {

    private final String[] groups;
    private final Map<String, Integer> namedGroups;

    public RegexPathMatch(String[] groups, Map<String, Integer> namedGroups) {
        this.groups = groups;
        this.namedGroups = namedGroups;
    }

    @Override
    public String get(String name) {
        return "";
    }

    @Override
    public String get(int index) {
        return "";
    }
}
