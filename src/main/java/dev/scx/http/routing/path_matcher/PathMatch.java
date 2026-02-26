package dev.scx.http.routing.path_matcher;


public interface PathMatch {

    /// - index 从 0 开始
    /// - 若 index >= 捕获数量, 返回 null
    String capture(int index);

    /// - 若不存在该命名捕获, 返回 null
    String capture(String name);

}
