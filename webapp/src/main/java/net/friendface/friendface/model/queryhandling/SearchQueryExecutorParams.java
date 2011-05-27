package net.friendface.friendface.model.queryhandling;

/**
 * Author: S. Fink
 * Date: 23.05.11
 * Time: 23:45
 */

public class SearchQueryExecutorParams extends QueryParams {
    private String textToFind;

    public SearchQueryExecutorParams(String textToFind) {
        super();
        this.textToFind = textToFind;
    }

    public String getTextToFind() {
        return textToFind;
    }
}
