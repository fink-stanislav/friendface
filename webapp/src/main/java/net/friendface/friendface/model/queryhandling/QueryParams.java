package net.friendface.friendface.model.queryhandling;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: S. Fink
 * Date: 23.05.11
 * Time: 23:41
 */

public abstract class QueryParams {
    protected Map<String, Object> queryParams;

    protected QueryParams() {
        queryParams = new HashMap<String, Object>();
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
