package dev.scx.http.routing.path_matcher;

import dev.scx.http.parameters.ParametersWritable;

//todo 需要重构
public class TemplatePathMatch implements PathMatch {

    private final boolean accepted;
    private final ParametersWritable<String, String> pathParams;

    public TemplatePathMatch(boolean accepted, ParametersWritable<String, String> pathParams) {
        this.accepted = accepted;
        this.pathParams = pathParams;
    }

    @Override
    public String get(String name) {
        return pathParams.get(name);
    }

    @Override
    public String get(int index) {
        return "";
    }
}
