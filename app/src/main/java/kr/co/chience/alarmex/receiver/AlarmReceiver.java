package kr.co.chience.alarmex.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import kr.co.chience.alarmex.service.AlarmService;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent gIntent = new Intent(context, AlarmService.class);
        gIntent.putExtra("state", intent.getStringExtra("state"));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(gIntent);
        } else {
            context.startService(gIntent);
        }
    }
}
