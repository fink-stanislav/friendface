package net.friendface.friendface.model.queryhandling;

import org.apache.commons.lang.StringUtils;

/**
 * Author: S. Fink
 * Date: 23.05.11
 * Time: 23:45
 */

public class SearchQueryParams<T> extends QueryParams<T> {
    public SearchQueryParams() {
        super();
    }

    @Override
    public void setParam(String paramName, T paramValue) {
        String value;
        if (paramValue instanceof String) {
            value = (String) paramValue;
            if (StringUtils.isNotBlank(value)) {
                super.setParam(paramName, paramValue);
            }
        }
    }
}
