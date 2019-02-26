package com.shark.application.util;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ServletUtil {

    public static HashMap<String, String> generateServiceParameters(HttpServletRequest request, String... keys) {
        HashMap<String, String> parameters = new HashMap<>();
        for (String key : keys) {
            parameters.put(key, request.getParameter(key));
        }
        return parameters;
    }

    public static HashMap<String, String> generateServiceParameters(HttpServletRequest request, Class<?> controller, String methodName, boolean hasResponseInput) {
        HashMap<String, String> parameters = new HashMap<>();
        try {
            Method method;
            if (hasResponseInput) {
                method = controller.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            } else {
                method = controller.getMethod(methodName, HttpServletRequest.class);
            }
            ApiImplicitParams apiImplicitParams = method.getAnnotation(ApiImplicitParams.class);
            if (apiImplicitParams != null) {
                ApiImplicitParam[] paramArray = apiImplicitParams.value();
                if (paramArray.length > 0) {
                    for (ApiImplicitParam item : paramArray) {
                        parameters.put(item.name(), request.getParameter(item.name()));
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return parameters;
    }
}
