package dev.scx.http.routing.path_matcher;

//todo 需要重构
public interface PathMatch {

    //todo 重命名?
    String get(String name);

    // todo 需要重命名?
    String get(int index);

}
