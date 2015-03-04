package com.rudenkoInc.dao.transactionDao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            //NOP
        }
    }

    public static void closeQuietly(ResultSet rs, Statement stmt)  {
        try{
            if(rs != null && stmt != null){
                stmt.close();
                rs.close();
            }
        } catch (SQLException e){
            //NOP
        }
    }
}
