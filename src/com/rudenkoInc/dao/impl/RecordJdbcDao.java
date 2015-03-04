package com.rudenkoInc.dao.impl;

import com.rudenkoInc.dao.RecordDao;
import com.rudenkoInc.dao.exception.NoSuchRecordException;
import com.rudenkoInc.dao.transactionDao.impl.TransactionManagerImpl;
import exceptions.InvalidStringRecordException;
import journals.CollectionJournal;
import journals.Journal;
import recordImpl.Importance;
import recordImpl.Record;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RecordJdbcDao implements RecordDao {

    public static final String GET_RECORDS = "select * from " +
                                             "( select a.*, ROWNUM rnum from " +
                                             "  ( select a.recDate, a.importance, a.source, a.message " +
                                             "  FROM record" +
                                             "  ORDER BY a.recDate desc) a " +
                                             "  where ROWNUM <= ? ) " +
                                             "where rnum  >= ?;";
    public static final String GET_ALL_RECORDS = "SELECT recDate, importance, source, message FROM record";
    public static final String INSERT_RECORD = "INSERT INTO record (recDate, importance, source, message) " +
                                               "VALUES (?, ?, ?, ?)";
    public static final String DELETE_RECORD = "DELETE record WHERE recDate = ?";
    public static final String CREATE_TABLE = "CREATE TABLE record (recDate DATE, importance VARCHAR2(15), source VARCHAR2(50), message VARCHAR2(250));";

    public static final String COLUMN_LABEL_DATE = "recDate";
    public static final String COLUMN_LABEL_IMPORTANCE = "importance";
    public static final String COLUMN_LABEL_SOURCE = "source";
    public static final String COLUMN_LABEL_MESSAGE = "message";

    DataSource dataSource;

    @Override
    public Journal getAllRecords() {
        Journal journal = null;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = TransactionManagerImpl.getConnection().createStatement();
            rs = stmt.executeQuery(GET_ALL_RECORDS);

            while (rs.next()) {
                Calendar cal = new GregorianCalendar();
                cal.setTime(rs.getDate(COLUMN_LABEL_DATE));
                Importance imp = RecordUtils.parseImportance(rs.getString(COLUMN_LABEL_IMPORTANCE));

                journal = new CollectionJournal();
                journal.add(new Record(cal,
                        imp,
                        rs.getString(COLUMN_LABEL_SOURCE),
                        rs.getString(COLUMN_LABEL_MESSAGE)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidStringRecordException e) {
            e.printStackTrace();
        }

        return journal;
    }

    @Override
    public Journal getRecords(int maxRow, int minRow) {
        Journal journal = null;
        PreparedStatement stmt;
        ResultSet rs;
        try {
            stmt = TransactionManagerImpl.getConnection().prepareStatement(GET_RECORDS);
            stmt.setInt(1, maxRow);
            stmt.setInt(2, minRow);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Calendar cal = new GregorianCalendar();
                cal.setTime(rs.getDate(COLUMN_LABEL_DATE));
                Importance imp = RecordUtils.parseImportance(rs.getString(COLUMN_LABEL_IMPORTANCE));

                journal = new CollectionJournal();
                journal.add(new Record(cal,
                        imp,
                        rs.getString(COLUMN_LABEL_SOURCE),
                        rs.getString(COLUMN_LABEL_MESSAGE)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidStringRecordException e) {
            e.printStackTrace();
        }

        return journal;
    }

    @Override
    public void addRecord(Record rec) {

        try {
            PreparedStatement stmt = TransactionManagerImpl.getConnection().prepareStatement(INSERT_RECORD);
            stmt.setDate(1, new Date(rec.getTime().getTimeInMillis()));
            stmt.setString(2, rec.getImportance().toString());
            stmt.setString(3, rec.getSource());
            stmt.setString(4, rec.getMessage());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecord(Record rec) throws NoSuchRecordException {
        deleteRecord(rec);
        addRecord(rec);
    }

    @Override
    public void deleteRecord(Record rec) throws NoSuchRecordException {
        try {
            PreparedStatement stmt = TransactionManagerImpl.getConnection().prepareStatement(DELETE_RECORD);
            stmt.setDate(1, new Date(rec.getTime().getTimeInMillis()));
            if(!stmt.execute()) {
                throw new NoSuchRecordException("There is no record with such date!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
