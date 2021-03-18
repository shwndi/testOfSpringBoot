package com.example.demo.date;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author czy
 * @date 2021/3/18
 */
public class DateTool {
    /**
     * 昨天开始
     */
    static Date yStartTime = DateUtils.truncate(
            DateUtils.addDays(new Date(), -1), Calendar.DATE);
    /**
     * 昨天结束
     */
    static Date yEndTime = DateUtils.addMilliseconds(
            DateUtils.truncate(DateUtils.addDays(new Date(), 0), Calendar.DATE), -1);
    /**
     * 今天开始
     */
    static Date tStartTime = DateUtils.truncate(
            DateUtils.addDays(new Date(), 0), Calendar.DATE);
    /**
     * 今天结束
     */
    static Date tEndTime = DateUtils.addMilliseconds(
            DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DATE), -1);

    public static void main(String[] args) {
        System.out.println(yStartTime);
        System.out.println(yEndTime);
        System.out.println(tStartTime);
        System.out.println(tEndTime);
    }
}/*Output:
Wed Mar 17 00:00:00 CST 2021
Wed Mar 17 23:59:59 CST 2021
Thu Mar 18 00:00:00 CST 2021
Thu Mar 18 23:59:59 CST 2021
*///:`
