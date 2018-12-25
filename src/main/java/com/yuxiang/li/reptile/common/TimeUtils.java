package com.yuxiang.li.reptile.common;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yuxiang.li on 2018/12/4.
 * 时间处理工具
 */
@Component
public class TimeUtils {

    public static boolean judgeTime(Integer timeType, String lotteryDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date currDate = new Date();
        System.out.println("--------"+simpleDateFormat.format(currDate)+"--------------");
        Calendar calendar = Calendar.getInstance();
        String newStringDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get((Calendar.MONTH)) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + lotteryDate;
        Long newLongDate = null;
        try {
            newLongDate = simpleDateFormat.parse(newStringDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long currLongDate = currDate.getTime();
        int minutes = (int) ((currLongDate - newLongDate) / (1000 * 60));
        if (timeType == Constant.TEN_MINUTE_TIMES) {
            if (minutes >= 2 && minutes <= 8) {
                return true;
            } else {
                return false;
            }
        } else {
            if (minutes >= 1 && minutes <= 5) {
                return true;
            } else {
                return false;
            }
        }


    }

}
