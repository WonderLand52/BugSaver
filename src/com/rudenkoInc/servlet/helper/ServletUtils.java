package com.rudenkoInc.servlet.helper;

import com.rudenkoInc.dao.impl.RecordUtils;
import com.sun.deploy.net.HttpRequest;
import exceptions.InvalidStringRecordException;
import recordImpl.Record;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class ServletUtils {

    public static final String DATE_PARAMETER = "date";
    public static final String IMPORTANCE_PARAMETER = "importance";
    public static final String SOURCE_PARAMETER = "source";
    public static final String MESSAGE_PARAMETER = "message";

    public static Record getRecordFromServlet(HttpServletRequest req) throws InvalidStringRecordException, ParseException {
        StringBuffer sb = new StringBuffer();
        String strDate = req.getParameter(DATE_PARAMETER);
        sb.append(strDate);
        sb.append("%");
        sb.append(req.getParameter(IMPORTANCE_PARAMETER));
        sb.append("%");
        sb.append(req.getParameter(SOURCE_PARAMETER));
        sb.append("%");
        sb.append(req.getParameter(MESSAGE_PARAMETER));

        return RecordUtils.parseRecord(sb.toString());
    }
}
