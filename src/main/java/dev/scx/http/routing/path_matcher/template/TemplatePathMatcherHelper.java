package dev.scx.http.routing.path_matcher.template;

import java.util.HashMap;
import java.util.Map;

import static dev.scx.http.routing.path_matcher.template.WildcardToken.WILDCARD_TOKEN;

/// TemplatePathMatcherHelper
///
/// @author scx567888
/// @version 0.0.1
final class TemplatePathMatcherHelper {

    /// 将 tokens 转换成 nameToIndex, 同时进行 ParamToken 的命名冲突校验.
    public static Map<String, Integer> createNameToIndex(Token[] tokens) {
        var nameToIndex = new HashMap<String, Integer>();

        var index = 0;
        for (var token : tokens) {
            switch (token) {
                case StaticToken s -> {
                    // 直接跳过.
                }
                case ParamToken p -> {
                    String name = p.name();
                    // 重复 参数名 校验.
                    if (nameToIndex.putIfAbsent(name, index) != null) {
                        throw new IllegalArgumentException("duplicate param name: " + name);
                    }
                    index += 1;
                }
                case WildcardToken w -> {
                    nameToIndex.put("*", index);
                    index += 1;
                }
            }

        }
        // 保证不可变.
        return Map.copyOf(nameToIndex);
    }

    public static Token[] templateToToken(String template) {
        var segments = template.split("/", -1);

        // 我们需要从 segments 中丢弃第一段, 因为这是来自 split 的副作用.
        var tokens = new Token[segments.length - 1];
        for (int i = 1; i < segments.length; i++) {
            var segment = segments[i];
            Token token;
            if (segment.equals("*")) {
                // "*" 必须是最后一段.
                if (i != segments.length - 1) {
                    throw new IllegalArgumentException("'*' must be last");
                }
                token = WILDCARD_TOKEN;
            } else if (segment.startsWith(":")) {
                token = new ParamToken(segment.substring(1));
            } else {
                // 静态段
                token = new StaticToken(segment);
            }
            tokens[i - 1] = token;
        }
        return tokens;
    }

}
