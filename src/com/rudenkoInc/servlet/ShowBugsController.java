package com.rudenkoInc.servlet;

import com.rudenkoInc.dao.impl.RecordJdbcDao;
import com.rudenkoInc.dao.transactionDao.TransactionManager;
import com.rudenkoInc.dao.transactionDao.impl.TransactionManagerImpl;
import com.rudenkoInc.dao.workToDoInterface.UnitOfWork;
import journals.Journal;
import recordImpl.Record;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowBugsController extends HttpServlet {

    public static final String ATTRIBUTE_JOURNAL = "records";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TransactionManager tx = new TransactionManagerImpl();
        Journal j;
        try {
            j = tx.doInTransaction(new UnitOfWork<Journal, Exception>() {
                @Override
                public Journal doInTx() throws Exception {

                    return new RecordJdbcDao().getAllRecords();

                }
            });

            req.setAttribute(ATTRIBUTE_JOURNAL, j);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
