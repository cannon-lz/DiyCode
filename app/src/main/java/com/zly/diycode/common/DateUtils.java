package com.zly.diycode.common;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhangluya on 2017/3/22.
 */

public class DateUtils {

    public static String computePastTime(String time) {
        String result = "刚刚";
        //2016-11-25T16:55:21.791+08:00
        time = time.replace("T", " ");
        time = time.substring(0, 22);
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.SIMPLIFIED_CHINESE);
        try {
            Date t = simpleDateFormat.parse(time);
            Date now = new Date(System.currentTimeMillis());
            long diff = (now.getTime() - t.getTime()) / 1000;
            if (diff < 60) {
                result = "刚刚";
            } else if ((diff /= 60) < 60) {
                result = diff + "分钟前";
            } else if ((diff /= 60) < 24) {
                result = diff + "小时前";
            } else if ((diff /= 24) < 30) {
                result = diff + "天前";
            } else if ((diff /= 30) < 12) {
                result = diff + "月前";
            } else {
                diff /= 12;
                result = diff + "年前";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
