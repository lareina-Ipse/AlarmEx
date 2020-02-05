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
import kr.co.chience.alarmex.Util.SetAlarm;
import kr.co.chience.alarmex.clud.CRUDAlarm;
import kr.co.chience.alarmex.model.Alarm;

public class UpdateReceiver extends BroadcastReceiver {

    private static final String TAG = RestartReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        LogUtil.e(TAG, "Update >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        LogUtil.e(TAG, "Update >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        SetAlarm.multiAlarm(context, TAG);


    }

}
