package com.shark.base.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ServletUtil {

    public static HashMap<String, String> generateServiceParameters(HttpServletRequest request, String... keys) {
        HashMap<String, String> parameters = new HashMap<String, String>();
        for(String key: keys) {
            parameters.put(key, request.getParameter(key));
        }
        return parameters;
    }
}
