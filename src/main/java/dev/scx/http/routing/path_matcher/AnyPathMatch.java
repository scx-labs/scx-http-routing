package dev.scx.http.routing.path_matcher;

final class AnyPathMatch implements PathMatch {

    @Override
    public String get(String name) {
        return "";
    }

    @Override
    public String get(int index) {
        return "";
    }
}
