package com.xelitexirish.logbot.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by XeliteXirish on 23/10/2016. www.xelitexirish.com
 */
public class GeneralUtils {

    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy/MMdd_HHmmss").format(Calendar.getInstance().getTime());
    }
}
