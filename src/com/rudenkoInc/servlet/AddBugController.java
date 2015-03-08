package com.rudenkoInc.servlet;

import com.rudenkoInc.dao.RecordDao;
import com.rudenkoInc.dao.impl.RecordJdbcDao;
import com.rudenkoInc.dao.impl.RecordUtils;
import com.rudenkoInc.dao.transactionDao.TransactionManager;
import com.rudenkoInc.dao.transactionDao.impl.TransactionManagerImpl;
import com.rudenkoInc.dao.workToDoInterface.UnitOfWork;
import com.rudenkoInc.servlet.helper.ServletUtils;
import exceptions.InvalidStringRecordException;
import recordImpl.Record;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public class AddBugController extends HttpServlet {

    TransactionManager tm;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Record rec = null;
        try {
            rec = ServletUtils.getRecordFromServlet(req);

        } catch (InvalidStringRecordException e) {
            System.out.println("Invalid String Record!");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Exception while parsing Record!");
            e.printStackTrace();
        }

        tm = new TransactionManagerImpl();

        try {
            final Record finalRec = rec;
            tm.doInTransaction(new UnitOfWork<Void,Exception>() {
                @Override
                public Void doInTx() throws Exception {
                    new RecordJdbcDao().addRecord(finalRec);
                    return null;
                }
            });
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
