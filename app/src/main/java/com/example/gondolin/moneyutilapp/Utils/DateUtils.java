package com.example.gondolin.moneyutilapp.Utils;

import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

    public static int getCurrentDatePosition(DatePicker current) {
        int dayIndex = current.getDayOfMonth();
        if (dayIndex < 15) {
            return 15 - dayIndex;
        } else {
            Date today = new Date();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);

            calendar.add(current.getYear(), 1);
            calendar.set(current.getMonth(), 1);
            calendar.add(current.getDayOfMonth(), -1);

            Date lastDayOfMonth = calendar.getTime();

            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String lastDayofMonth = sdf.format(lastDayOfMonth).split("-")[2];
            return dayIndex - Integer.valueOf(lastDayofMonth);
        }
    }
}
