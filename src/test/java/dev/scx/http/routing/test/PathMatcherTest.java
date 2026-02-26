package dev.scx.http.routing.test;

import dev.scx.http.routing.path_matcher.PathMatcher;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PathMatcherTest {

    public static void main(String[] args) {
        test1();
    }

    @Test
    public static void test1() {
        var matcher = PathMatcher.of("/a/b/:id");
        var result = matcher.match("/a/b/苹果");
        Assert.assertEquals(result.get("id"), "苹果");

        var result1 = matcher.match("/a/b/ 空格 ");
        Assert.assertEquals(result1.get("id"), " 空格 ");

        var result2 = matcher.match("/a/b/c/ ");
        Assert.assertNull(result2);

        var matcher2 = PathMatcher.of("/a/b/*");

        var result5 = matcher2.match("/a/b/");
        Assert.assertEquals(result5.get("*"), "");

        var result6 = matcher2.match("/a/b/c/d/f/e/f");
        Assert.assertEquals(result6.get("*"), "c/d/f/e/f");

        var matcher3 = PathMatcher.of("/a/b/:name/*");

        var result7 = matcher3.match("/a/b/小明/");
        Assert.assertEquals(result7.get("name"), "小明");
        Assert.assertEquals(result7.get("*"), "");

        var result8 = matcher3.match("/a/b/小明/9");
        Assert.assertEquals(result8.get("name"), "小明");
        Assert.assertEquals(result8.get("*"), "9");
    }

}
