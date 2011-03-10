package com.exadel.friendface.controllers.actions;

import org.apache.struts2.interceptor.ParameterAware;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: S. Fink
 * Date: 3/11/11
 * Time: 12:31 AM
 */

public abstract class ParametrizedAction extends StandardAction implements ParameterAware {
    private Map parameters;

    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

    public Map<String, String> getDefaultParameters() {
        Map<String, String> params = new HashMap<String, String>();
        Set<Map.Entry<?, ?>> rawEntrySet = parameters.entrySet();
        try {
            for (Map.Entry<?, ?> entry : rawEntrySet) {
                params.put((String) entry.getKey(), ((String[]) entry.getValue())[0]);
            }
            return params;
        } catch (Exception e) {
            return null;
        }
    }

    public String getParameter(String key) {
        return (String) parameters.get(key);
    }

    public String getDefaultParameter(String key) {
        try {
            String[] result = ((String[]) parameters.get(key));
            if (result != null && result.length > 0) {
                return result[0];
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }

    public void setParameter(String key, String value) {
        parameters.put(key, value);
    }

    public void removeParameter(String key) {
        parameters.remove(key);
    }
}
