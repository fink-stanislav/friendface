package net.friendface.friendface.controllers.actions.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: S. Fink
 * Date: 15.03.11
 * Time: 23:19
 */

public class ParameterHelper {
    private Map parameters;

    public ParameterHelper(Map parameters) {
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
