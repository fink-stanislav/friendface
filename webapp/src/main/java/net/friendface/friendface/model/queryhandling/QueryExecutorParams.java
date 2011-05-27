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

public class QueryExecutorParams extends QueryParams {
    private String queryName;

    public QueryExecutorParams(String queryName) {
        super();
        this.queryName = queryName;
    }

    public String getQueryName() {
        return queryName;
    }
}
