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

public abstract class QueryParams<T> {
    private Map<String, T> queryParams;
    private Integer currentRecord;
    private Integer recordCount;

    protected QueryParams() {
        queryParams = new HashMap<String, T>();
        currentRecord = 0;
        recordCount = 0;
    }

    public T getParam(String paramName) {
        return queryParams.get(paramName);
    }

    public void setParam(String paramName, T paramValue) {
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

    public Integer getRecordCount() {
        return recordCount;
    }

    public Integer getCurrentRecord() {
        return currentRecord;
    }

    public void setCurrentRecord(Integer currentRecord) {
        this.currentRecord = currentRecord;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getParamCount() {
        return queryParams.size();
    }
}
