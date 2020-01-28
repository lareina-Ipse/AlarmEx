package kr.co.chience.alarmex.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import kr.co.chience.alarmex.R;
import kr.co.chience.alarmex.Util.LogUtil;

public class AlarmService extends Service {

    private static final String TAG = AlarmService.class.getSimpleName();

    private boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("MissingPermission")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = createNotificationChannel();

            RemoteViews view = new RemoteViews(getPackageName(), R.layout.notification_service);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
            Notification notification = builder.setOngoing(true)
                    .setContent(view)
                    .setContentTitle("알람 아이받니?")
                    .setContentText("니 그거 아니 받으면 죽는다 알았니???")
                    .setSmallIcon(R.drawable.siren).build();

            startForeground(1, notification);
        }

        String state = intent.getStringExtra("state");
        if (!isRunning && state.equals("on")) {
            isRunning = true;
            LogUtil.e(TAG, "AlarmService Start");
        } else if (isRunning && state.equals("off")) {
            isRunning = false;
            LogUtil.e(TAG, "AlarmService Stop");
        }

        return START_NOT_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String createNotificationChannel() {
        String chnnelId = "Alarm";
        String channelName = "알람 아이받니?";

        NotificationChannel channel = new NotificationChannel(chnnelId, channelName, NotificationManager.IMPORTANCE_NONE);
        channel.enableLights(true);
        channel.setSound(null, null);
        channel.setShowBadge(false);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);

        RemoteViews view = new RemoteViews(getPackageName(), R.layout.notification_service);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.siren))
                .setSmallIcon(R.drawable.siren)
                .setTicker(getString(R.string.app_name))
                //.setContentIntent() 눌렀을때 이벤트
                .setContent(view)
                .setContentTitle("알람 아이받니?")
                .setContentText("니 그거 아니 받으면 죽는다 알았니???")
                .setOngoing(true)
                .setAutoCancel(false);

        return chnnelId;
    }



}
