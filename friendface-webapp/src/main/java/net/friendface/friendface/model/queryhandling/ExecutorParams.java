package net.friendface.friendface.model.queryhandling;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: S. Fink
 * Date: 22.05.11
 * Time: 16:25
 */

public class ExecutorParams {
    private String queryName;
    private Map<String, Object> queryParams;

    public ExecutorParams(String queryName) {
        this.queryName = queryName;
        queryParams = new HashMap<String, Object>();
    }

    public String getQueryName() {
        return queryName;
    }

    public Object getParam(String paramName) {
        return queryParams.get(paramName);
    }

    public void setParam(String paramName, Object paramValue) {
        queryParams.put(paramName, paramValue);
    }

    public void removeParam(String paramName) {
        queryParams.remove(paramName);
    }

    public Set<String> getParamNames() {
        if (queryParams.isEmpty()) {
            return Collections.emptySet();
        }
        return queryParams.keySet();
    }
}
