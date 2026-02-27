package dev.scx.http.routing.test;

import dev.scx.http.routing.path_matcher.PathMatcher;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TemplatePathMatcherTest {

    public static void main(String[] args) {
        test1();
    }

    /// 测试模板
    @Test
    public static void test1() {
        var matcher = PathMatcher.ofTemplate("/a/b/:id");
        var result = matcher.match("/a/b/苹果");
        Assert.assertEquals(result.capture("id"), "苹果");

        var result1 = matcher.match("/a/b/ 空格 ");
        Assert.assertEquals(result1.capture("id"), " 空格 ");

        var result2 = matcher.match("/a/b/c/ ");
        Assert.assertNull(result2);

        var matcher2 = PathMatcher.ofTemplate("/a/b/*");

        var result5 = matcher2.match("/a/b/");
        Assert.assertEquals(result5.capture("*"), "");

        var result6 = matcher2.match("/a/b/c/d/f/e/f");
        Assert.assertEquals(result6.capture("*"), "c/d/f/e/f");

        var matcher3 = PathMatcher.ofTemplate("/a/b/:name/*");

        var result7 = matcher3.match("/a/b/小明/");
        Assert.assertEquals(result7.capture("name"), "小明");
        Assert.assertEquals(result7.capture("*"), "");

        var result8 = matcher3.match("/a/b/小明/9");
        Assert.assertEquals(result8.capture("name"), "小明");
        Assert.assertEquals(result8.capture("*"), "9");
    }

}
