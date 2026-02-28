package dev.scx.http.routing.path_matcher.template;

/// Token
///
/// @author scx567888
/// @version 0.0.1
sealed interface Token permits StaticToken, ParamToken, WildcardToken {

}
