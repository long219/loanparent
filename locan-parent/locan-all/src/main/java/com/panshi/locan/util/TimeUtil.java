package com.panshi.locan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    /**
     * 获取毫秒
     * @param strTime
     * @return

     */

    private static Logger logger =  LoggerFactory.getLogger(TimeUtil.class);

    public static long getMsec(String strTime) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = dateFormat.parse(strTime);

        logger.info(strTime + "  is  " + date.getTime());

        return date.getTime();
    }

}
