package kr.co.chience.alarmex.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import kr.co.chience.alarmex.Util.LogUtil;
import kr.co.chience.alarmex.service.AlarmService;

public class AlarmReceiver extends BroadcastReceiver {
    private final static int NOTICATION_ID = 222;
    private static final String TAG = AlarmReceiver.class.getSimpleName();
    private int size;

    @Override
    public void onReceive(Context context, Intent intent) {
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
