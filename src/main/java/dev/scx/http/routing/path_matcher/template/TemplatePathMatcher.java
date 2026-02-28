package dev.scx.http.routing.path_matcher.template;

import dev.scx.http.routing.path_matcher.IndexedPathMatch;
import dev.scx.http.routing.path_matcher.PathMatch;
import dev.scx.http.routing.path_matcher.PathMatcher;

import java.util.ArrayList;
import java.util.Map;

import static dev.scx.http.routing.path_matcher.template.TemplatePathMatcherHelper.createNameToIndex;
import static dev.scx.http.routing.path_matcher.template.TemplatePathMatcherHelper.templateToToken;
import static dev.scx.http.routing.path_matcher.template.WildcardToken.WILDCARD_TOKEN;

/// `TemplatePathMatcher` 基于简洁, 显式的路径模板语法实现路径匹配.
///
/// ## 模板语法
///
/// 模板必须以 `/` 开头, 由 `/` 分隔的若干段 (segment) 组成.
///
/// 支持以下三种段类型:
///
/// - **静态段**: 普通字符串, 要求与请求路径中的对应段完全相等.
/// - **参数段**: `:name`, 匹配恰好一个路径段, 并以 `name` 为键捕获其值.
/// - **通配段**: `*`, 匹配剩余的零个或多个路径段. 若存在通配段, 则**必须是模板的最后一段**.
///
/// ## 匹配规则
///
///  - 请求路径必须以 `/` 开头.
///  - 静态段必须严格相等.
///  - 参数段匹配且仅匹配一个路径段.
///  - 通配段 `*` 可匹配零个或多个后续路径段.
///  - 若模板中不包含 `*`, 则请求路径段数量必须与模板段数量完全一致.
///
/// ## 捕获语义
/// 匹配成功后, 可通过 [PathMatch#capture(String)] 或 [PathMatch#capture(int)] 获取捕获值.
///
/// - 每个 `:name` 参数捕获对应的路径段字符串.
/// - `*` 捕获请求路径中尚未匹配的剩余部分, 捕获值是**原始请求路径的子串**, 不会做任何归一化处理.
///
/// ### 通配段捕获示例
///
/// 对于模板 `/api/*`:
///
/// ```
/// /api        -> "*" = ""
/// /api/       -> "*" = "/"
/// /api/user   -> "*" = "/user"
/// /api/a/b    -> "*" = "/a/b"
/// ```
///
/// 该设计会刻意保留尾部斜杠与空段信息,
/// 使调用方能够区分 `""`, `"/"` 与 `"/rest"`.
/// 如需宽松或归一化行为, 应由更高层路由逻辑自行处理.
///
/// ## 使用场景
///
/// `TemplatePathMatcher` 常用于路由与子路由匹配.
/// 典型用法是使用 `/base/*` 挂载子路由, 并通过
/// `capture("*")` 获取剩余路径, 交由子路由继续处理.
///
/// 本类刻意采用严格, 可预测的匹配语义, 不包含隐式规则或自动修正行为.
///
/// @author scx567888
/// @version 0.0.1
public final class TemplatePathMatcher implements PathMatcher {

    private final String template;
    private final Token[] tokens;
    private final Map<String, Integer> nameToIndex;
    private final boolean hasWildcard;

    public TemplatePathMatcher(String template) {
        if (template == null) {
            throw new NullPointerException("template must not be null");
        }
        if (!template.startsWith("/")) {
            throw new IllegalArgumentException("exactPath must start with /");
        }
        this.template = template;
        this.tokens = templateToToken(this.template);
        this.nameToIndex = createNameToIndex(this.tokens);
        this.hasWildcard = tokens[tokens.length - 1] == WILDCARD_TOKEN;
    }

    @Override
    public PathMatch match(String path) {
        // 0, 切分 path
        var segments = path.split("/", -1);

        // 这里 segments 需要忽略第一个段. 后续注意 长度和索引计算.

        // 1, 长度校验. 如果包含尾部通配符. 长度必须大于等于 token 长度.
        int tokensCount = tokens.length;
        int segmentsCount = segments.length - 1;
        int fixedLen = hasWildcard ? tokensCount - 1 : tokensCount;

        if (hasWildcard) {
            if (segmentsCount < fixedLen) {
                return null;
            }
        } else { // 没有通配符 必须相等
            if (segmentsCount != fixedLen) {
                return null;
            }
        }

        // 2, 逐段匹配.

        // 参数
        var values = new ArrayList<String>();
        var pos = 0;

        for (int i = 0; i < fixedLen; i++) {
            var token = tokens[i];
            var segment = segments[i + 1];
            switch (token) {
                // 必须严格相等
                case StaticToken s -> {
                    if (!s.value().equals(segment)) {
                        // 终止匹配
                        return null;
                    }
                }
                case ParamToken p -> values.add(segment);
                // 这里理论上不可能发生
                case WildcardToken w -> throw new IllegalStateException("WildcardToken must be the last token");
            }
            pos += 1 + segment.length();
        }

        if (hasWildcard) {
            values.add(path.substring(pos));
        }

        return new IndexedPathMatch(values.toArray(String[]::new), nameToIndex);
    }

    @Override
    public String toString() {
        return "template(" + template + ")";
    }

}
