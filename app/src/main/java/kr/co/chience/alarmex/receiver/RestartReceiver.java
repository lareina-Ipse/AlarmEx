package kr.co.chience.alarmex.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import kr.co.chience.alarmex.Util.LogUtil;
import kr.co.chience.alarmex.Util.SetAlarm;

public class RestartReceiver extends BroadcastReceiver {

    private static final String TAG = RestartReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            LogUtil.e(TAG, "Restart >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            LogUtil.e(TAG, "Restart >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            SetAlarm.multiAlarm(context, TAG);

        }
    }



}
