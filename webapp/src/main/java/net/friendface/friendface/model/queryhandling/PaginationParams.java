package net.friendface.friendface.model.queryhandling;

/**
 * Author: S. Fink
 * Date: 22.05.11
 * Time: 16:34
 */

public class PaginationParams {
    private Integer currentRecord;
    private Integer recordCount;

    public PaginationParams() {
        currentRecord = 1;
        recordCount = 10;
    }

    public PaginationParams(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getCurrentRecord() {
        return currentRecord;
    }

    public void setCurrentRecord(Integer currentRecord) {
        this.currentRecord = currentRecord;
    }
}
