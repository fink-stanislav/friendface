package net.friendface.friendface.model.queryhandling;

/**
 * Author: S. Fink
 * Date: 22.05.11
 * Time: 16:25
 */

public class DefaultQueryParams<T> extends QueryParams<T> {
    private String queryName;

    public DefaultQueryParams(String queryName) {
        super();
        this.queryName = queryName;
    }

    public String getQueryName() {
        return queryName;
    }
}
