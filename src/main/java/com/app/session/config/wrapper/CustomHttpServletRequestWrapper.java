package com.app.session.config.wrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.HashMap;
import java.util.Map;

public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final Map<String, String[]> additionalParams;

    public CustomHttpServletRequestWrapper(HttpServletRequest request, Map<String, String[]> additionalParams) {
        super(request);
        this.additionalParams = new HashMap<>(additionalParams);
    }

    @Override
    public String getParameter(String name) {
        String[] values = additionalParams.get(name);
        if (values != null && values.length > 0) {
            return values[0];
        }
        return super.getParameter(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> paramMap = new HashMap<>(super.getParameterMap());
        paramMap.putAll(additionalParams);
        return paramMap;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = additionalParams.get(name);
        if (values != null) {
            return values;
        }
        return super.getParameterValues(name);
    }
}
