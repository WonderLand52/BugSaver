package com.rudenkoInc.dao.transactionDao.impl;

import com.rudenkoInc.dao.transactionDao.TransactionManager;
import com.rudenkoInc.dao.workToDoInterface.UnitOfWork;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionManagerImpl implements TransactionManager {

    public static String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    public static String ORACLE_USER = "darm";
    public static String ORACLE_PASSWORD = "password";


    private static Connection conn;


    public static Connection getConnection() {

        if(conn == null) {
            try {
                Class.forName(ORACLE_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println("Where is your Jdbc Driver?");
                e.printStackTrace();
            }

            try {
                conn = DriverManager.getConnection(ORACLE_URL, ORACLE_USER, ORACLE_PASSWORD);
            } catch (SQLException e) {
                System.out.println("Where is your Jdbc Connection?");
                e.printStackTrace();
            }

            return conn;

        } else {
            return conn;
        }


    }

    @Override
    public <T, E extends Exception> T doInTransaction(UnitOfWork<T, E> workToDo) throws E {

        T result = null;
        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);
            result = workToDo.doInTx();
            conn.commit();
            return result;
        } catch (SQLException e) {
            System.out.println("Exception while handling unitOfWork. Rollback...");

            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Exception while rollback!");
                e1.printStackTrace();
            }

            e.printStackTrace();
        } finally {
            JdbcUtils.closeQuietly(conn);
        }

        return result;
    }
}
