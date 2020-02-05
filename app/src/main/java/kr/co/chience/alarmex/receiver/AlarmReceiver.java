package kr.co.chience.alarmex.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;
import java.util.List;

import kr.co.chience.alarmex.Util.LogUtil;
import kr.co.chience.alarmex.clud.CRUDAlarm;
import kr.co.chience.alarmex.model.Alarm;
import kr.co.chience.alarmex.service.AlarmService;

public class AlarmReceiver extends BroadcastReceiver {
    private final static int NOTICATION_ID = 222;
    private static final String TAG = AlarmReceiver.class.getSimpleName();
    private int size;

    @Override
    public void onReceive(Context context, Intent intent) {

        Calendar today = Calendar.getInstance();
        int week = today.get(Calendar.DAY_OF_WEEK);
        List<Alarm> alarms;
        alarms = CRUDAlarm.readAllAlarm();

        if (alarms.size() != 0) {
            for (int i = 0; i < alarms.size(); i++) {
                switch (week) {
                    case 1:
                        if (alarms.get(i).getSun() != null) {
                            sendReceive(context, intent);
                        }
                        break;
                    case 2:
                        if (alarms.get(i).getMon() != null) {
                            sendReceive(context, intent);
                        }
                        break;
                    case 3:
                        if (alarms.get(i).getTue() != null) {
                            sendReceive(context, intent);
                        }
                        break;
                    case 4:
                        if (alarms.get(i).getWed() != null) {
                            sendReceive(context, intent);
                        }
                        break;
                    case 5:
                        if (alarms.get(i).getThu() != null) {
                            sendReceive(context, intent);
                        }
                        break;
                    case 6:
                        if (alarms.get(i).getFri() != null) {
                            sendReceive(context, intent);
                        }
                        break;
                    case 7:
                        if (alarms.get(i).getSat() != null) {
                            sendReceive(context, intent);
                        }
                        break;
                }
            }
        }

    }

    public void sendReceive(Context context, Intent intent) {
        Intent alarmState = new Intent(context, AlarmService.class);
        alarmState.putExtra("state", intent.getStringExtra("state"));

        // Oreo 버전 이후부터는 Background에서 실행을 금지하기 때문에 Foreground에서 실행해야 한다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(alarmState);
        } else {
            context.startService(alarmState);
        }

        LogUtil.e(TAG, "Put State------->" + intent.getStringExtra("state"));
    }


}
