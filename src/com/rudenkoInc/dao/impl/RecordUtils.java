package com.rudenkoInc.dao.impl;

import exceptions.InvalidStringRecordException;
import recordImpl.Importance;
import recordImpl.MyArray;
import recordImpl.Record;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordUtils {



    public static Record parseRecord(String recordStr) throws InvalidStringRecordException, ParseException {
        if(recordStr == null){
            throw new InvalidStringRecordException("Record string is empty!!!");
        }

//        if(!matchesRecordStr(recordStr)){
//            throw new InvalidStringRecordException("Invalid record string!!!");
//        }



        String[] fields = recordStr.trim().split("%");

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        format.setLenient(false);
        cal.setTime(format.parse(fields[0].trim()));

        String importanceStr = fields[1].trim();
        Importance imp = parseImportance(importanceStr);

        String source = fields[2].trim();
        String message = fields[3];

        return new Record(cal, imp, source, message);
    }

    public static Importance parseImportance(String impStr) throws InvalidStringRecordException {

        String importanceStr = impStr.trim().toLowerCase();
        switch (importanceStr){
            case "trivial":
                return Importance.TRIVIAL;
            case "warning":
                return Importance.WARNING;
            case "error":
                return Importance.ERROR;
            case "critical":
                return Importance.CRITICAL;
            default:
                throw new InvalidStringRecordException("Wrong recordImpl.Importance string!!!");
        }
    }



    private static boolean matchesRecordStr(String recordStr) {
        Pattern pat = Pattern.compile("^\\s*[12]\\d{3}-[01]\\d-[0123]\\d\\s+[012]\\d:\\d{2}:\\d{2}"
                + "\\s+[\\.!]+\\s+\\w+\\s+.+\\s*$");
        Matcher match = pat.matcher(recordStr);

        return match.matches();
    }




}
