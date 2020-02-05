package kr.co.chience.alarmex.Util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Switch;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import kr.co.chience.alarmex.clud.CRUDAlarm;
import kr.co.chience.alarmex.model.Alarm;
import kr.co.chience.alarmex.receiver.AlarmReceiver;

public class SetAlarm {

    public static void multiAlarm(Context context, String TAG) {
        List<Alarm> alarms;
        alarms = CRUDAlarm.readAllAlarm();

        Intent intent = null;
        int hour = 0;
        int minute = 0;
        long alarmTime = 0;
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long interval = 1000 * 60 * 60 * 24;

        LogUtil.e(TAG, "Alarms Size ::::: " + alarms.size());


        if (alarms.size() != 0) {
            for (int i = 0; i < alarms.size(); i++) {
                LogUtil.e(TAG, "Alarms Get  :::::" + alarms.get(i));

                if (alarms.get(i).getaSwitch() == 0) {

                    LogUtil.e(TAG, "Alarms Get  :::::" + alarms.get(i));

                    hour = Integer.parseInt(alarms.get(i).getHour());
                    minute = Integer.parseInt(alarms.get(i).getMinute());

                    Calendar calendar = new GregorianCalendar();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);
                    calendar.set(Calendar.SECOND, 0);
                    alarmTime = calendar.getTimeInMillis();

                    LogUtil.e(TAG, "Hour   :: " + hour);
                    LogUtil.e(TAG, "Minute :: " + minute);

                    if (calendar.before(Calendar.getInstance())) {
                        calendar.add(Calendar.DATE, 1);
                        int month, date;
                        month = calendar.get(Calendar.MONTH) + 1;
                        date = calendar.get(Calendar.DATE);
                        LogUtil.e(TAG, "Calender ::: " + month + " / " + date);
                    }


                    intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);
                    intent.putExtra("state", "on");

                    PendingIntent sender = PendingIntent.getBroadcast(context.getApplicationContext(), i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

                } else {
                    LogUtil.e(TAG, "알람 숨김 된 내용임 ::: " + alarms.get(i).getaSwitch());
                    LogUtil.e(TAG, "잠시 알람 숨겨 둡니다. ::: " + alarms.get(i).getSwitchMaketime());
                }

            }

        }
    }

}
