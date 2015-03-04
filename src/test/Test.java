package test;

import com.rudenkoInc.dao.RecordDao;
import com.rudenkoInc.dao.impl.RecordJdbcDao;
import com.rudenkoInc.dao.transactionDao.TransactionManager;
import com.rudenkoInc.dao.transactionDao.impl.TransactionManagerImpl;
import com.rudenkoInc.dao.workToDoInterface.UnitOfWork;
import recordImpl.Importance;
import recordImpl.Record;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Test {

    public static void main(String[] args) {
//        TransactionManager tm = new TransactionManagerImpl();
//        try {
//            tm.doInTransaction(new UnitOfWork<Void, Exception>() {
//                @Override
//                public Void doInTx() throws Exception {
//                    RecordDao recDao = new RecordJdbcDao();
//                    Record rec = new Record(Calendar.getInstance(), Importance.TRIVIAL,
//                            "Hello source",
//                            "Hello message");
//
////                    System.err.println(rec);
//                    System.err.println(new Date(Calendar.getInstance().getTimeInMillis()));
//                    recDao.addRecord(rec);
//                    return null;
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }




//        String str = "Tue Mar 03 2015 19:59:30 GMT+0200(EET)";
//        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.US);
//        format.setTimeZone(TimeZone.getTimeZone("GMT"));
//        format.setLenient(false);
//        try {
//            java.util.Date date = format.parse(str);
//            System.out.println(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    }
}
