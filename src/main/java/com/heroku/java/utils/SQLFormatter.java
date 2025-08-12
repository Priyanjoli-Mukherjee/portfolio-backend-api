package com.heroku.java.utils;

import java.util.UUID;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class SQLFormatter {
    public static String replaceQuotes(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\'') {
                result += "''";
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

    public static String formatString(String str) {
        if (str == null) {
            return "NULL";
        }
        return "'" + replaceQuotes(str) + "'";
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "NULL";
        }
        return formatString(String.format("%tY-%tm-%td", date, date, date));
    }

    public static String formatTimestamp(Date timestamp) {
        if (timestamp == null) {
            return "NULL";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatString(sdf.format(timestamp));
    }

    public static String formatUUID(UUID id) {
        if (id == null) {
            return "NULL";
        }
        return formatString(id.toString());
    }

    public static String formatStringArray(String[] arr) {
        if (arr == null) {
            return "NULL";
        }
        String[] values = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            values[i] = formatString(arr[i]);
        }
        return "ARRAY [ " + String.join(", ", values) + " ]";
    }

    public static String formatUUIDArray(UUID[] arr) {
        if (arr == null) {
            return "NULL";
        }
        String[] values = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            values[i] = formatUUID(arr[i]);
        }
        return "ARRAY [" + String.join(", ", values) + " ]::UUID[]";
    }
}