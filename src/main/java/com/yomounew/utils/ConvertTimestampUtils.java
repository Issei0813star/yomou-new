package com.yomounew.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ConvertTimestampUtils {

    /**
     * TimestampをStringに変換するメソッド
     *
     * @param timestamp 変換したいTimestampオブジェクト
     * @return yyyy/MM/dd HH:mm形式のString
     */
    public static String convertTimestampToString(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        outputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));

        return outputFormat.format(timestamp);
    }
}
