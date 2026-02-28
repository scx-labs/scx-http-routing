package dev.scx.http.routing.path_matcher.template;

import dev.scx.http.routing.path_matcher.IndexedPathMatch;
import dev.scx.http.routing.path_matcher.PathMatch;
import dev.scx.http.routing.path_matcher.PathMatcher;

import java.util.ArrayList;
import java.util.Map;

import static dev.scx.http.routing.path_matcher.template.TemplatePathMatcherHelper.createNameToIndex;
import static dev.scx.http.routing.path_matcher.template.TemplatePathMatcherHelper.templateToToken;
import static dev.scx.http.routing.path_matcher.template.WildcardToken.WILDCARD_TOKEN;

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
