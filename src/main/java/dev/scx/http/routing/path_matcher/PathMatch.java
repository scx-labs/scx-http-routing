package dev.scx.http.routing.path_matcher;


public interface PathMatch {

    /// 按捕获顺序查询捕获值 (从 0 开始编号).
    /// 若不存在该索引对应的捕获, 返回 null.
    String capture(int index);

    /// 按名称查询命名捕获值。
    /// 若不存在该名称对应的捕获, 返回 null.
    String capture(String name);

}
