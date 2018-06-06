package com.mpif.beautyranking.util;

import java.util.Date;

/**
 * @Author: mpif
 * @Date: 2018-06-06 23:16
 */

public class DateUtils {

    /**
     * 获取精确到秒的时间戳
     * @return
     */
    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }
    
}
