package dev.scx.http.routing.path_matcher.template;

sealed interface Token permits StaticToken, ParamToken, WildcardToken {

}
