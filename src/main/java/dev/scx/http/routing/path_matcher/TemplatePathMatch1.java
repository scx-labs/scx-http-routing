package dev.scx.http.routing.path_matcher;

import dev.scx.http.parameters.ParametersWritable;

//todo 需要重构
public class TemplatePathMatch1 implements PathMatch {

    private final boolean accepted;
    private final ParametersWritable<String, String> pathParams;

    public TemplatePathMatch1(boolean accepted, ParametersWritable<String, String> pathParams) {
        this.accepted = accepted;
        this.pathParams = pathParams;
    }

    @Override
    public String capture(int index) {
        return "";
    }

    @Override
    public String capture(String name) {
        return pathParams.get(name);
    }

}
