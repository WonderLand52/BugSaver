package com.rudenkoInc.dao;

import com.rudenkoInc.dao.exception.NoSuchRecordException;
import journals.Journal;
import recordImpl.Record;

public interface RecordDao {

    public Journal getAllRecords();

    public Journal getRecords(int maxRow, int minRow);

    public void addRecord(Record rec);

    public void updateRecord(Record rec) throws NoSuchRecordException;

    public void deleteRecord(Record rec) throws NoSuchRecordException;
}
