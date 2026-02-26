package dev.scx.http.routing.path_matcher;

/// AnyPathMatch
///
/// @author scx567888
/// @version 0.0.1
final class AnyPathMatch implements PathMatch {

    public static final AnyPathMatch ANY_PATH_MATCH = new AnyPathMatch();

    @Override
    public String capture(int index) {
        // 什么都不匹配 返回 null
        return null;
    }

    @Override
    public String capture(String name) {
        // 什么都不匹配 返回 null
        return null;
    }

}
