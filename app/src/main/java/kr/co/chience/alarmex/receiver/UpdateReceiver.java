package kr.co.chience.alarmex.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import kr.co.chience.alarmex.Util.LogUtil;
import kr.co.chience.alarmex.clud.CRUDAlarm;
import kr.co.chience.alarmex.model.Alarm;

public class UpdateReceiver extends BroadcastReceiver {

    private static final String TAG = RestartReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

            LogUtil.e(TAG, "Update >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            LogUtil.e(TAG, "Update >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            multiAlarm(context);
    }

    private void multiAlarm(Context context) {
        List<Alarm> alarms;
        alarms = CRUDAlarm.readAllAlarm();

        Intent intent = null;
        int hour = 0;
        int minute = 0;
        long alarmTime = 0;
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long interval = 1000 * 60 * 60 * 24;

        LogUtil.e(TAG, "Alarms Count ::::: " + alarms.size());

        if (alarms.size() != 0) {
            for (int i = 0; i < alarms.size(); i++) {
                LogUtil.e(TAG, "Alarms Get  :::::" + alarms.get(i));
                if (alarms.get(i).getaSwitch() == 0) {
                    hour = Integer.parseInt(alarms.get(i).getHour());
                    minute = Integer.parseInt(alarms.get(i).getMinute());

                    Calendar calendar = new GregorianCalendar();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);
                    calendar.set(Calendar.SECOND, 0);

                    LogUtil.e(TAG, "Hour   :: " + hour);
                    LogUtil.e(TAG, "Minute :: " + minute);

                    //월
                    if (alarms.get(i).getMon() != null) {
                        sendDay(calendar, 2);
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
                    }

                    //화
                    if (alarms.get(i).getTue() != null) {
                        sendDay(calendar, 3);
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
                    }

                    //수
                    if (alarms.get(i).getWed() != null) {
                        sendDay(calendar, 4);
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
                    }

                    //목
                    if (alarms.get(i).getThu() != null) {
                        sendDay(calendar, 5);
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
                    }

                    //금
                    if (alarms.get(i).getFri() != null) {
                        sendDay(calendar, 6);
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
                    }

                    //토
                    if (alarms.get(i).getSat() != null) {
                        sendDay(calendar, 7);
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
                    }

                    //일
                    if (alarms.get(i).getSun() != null) {
                        sendDay(calendar, 1);
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
                    }

                    //현 시간에서 지나간 시간이면 다음 날로 지정

                } else {
                    LogUtil.e(TAG, "잠시 알람 숨겨 둡니다. ::: " + alarms.get(i).getSwitchMaketime());
                }

            }

        }

    }


    public void sendDay(Calendar calendar, int day) {
        calendar.set(Calendar.DAY_OF_WEEK, day);
    }
}
