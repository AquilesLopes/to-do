package br.com.todo.todo.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Link implements Serializable {
    private static final long serialVersionUID = 1L;

    private String url;
    private String description;
    private String method;

    private Map<String,String> parameters = new HashMap<String,String>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(String key, String value) {
        this.parameters.put(key, value);
    }

    @Override
    public String toString() {
        return "Link{" +
                "url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", method='" + method + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
