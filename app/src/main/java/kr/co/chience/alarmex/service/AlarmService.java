package kr.co.chience.alarmex.service;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import kr.co.chience.alarmex.AlarmActivity;
import kr.co.chience.alarmex.MainActivity;
import kr.co.chience.alarmex.R;
import kr.co.chience.alarmex.Util.LogUtil;
import kr.co.chience.alarmex.Util.SetAlarm;
import kr.co.chience.alarmex.clud.CRUDAlarm;
import kr.co.chience.alarmex.model.Alarm;
import kr.co.chience.alarmex.receiver.AlarmReceiver;

public class AlarmService extends Service {

    private static final String TAG = AlarmService.class.getSimpleName();
    private Vibrator vibrator;
    String state;
    long[] pattern = {1000, 1000, 1000, 1000};

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        state = intent.getStringExtra("state");

        if (state.equals("on")) {
            //     vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            //     vibrator.vibrate(pattern, 0);
            LogUtil.e(TAG, "AlarmService Start");
            setForeground();
            SetAlarm.multiAlarm(getApplicationContext(), TAG);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void setForeground() {
        Intent intent = new Intent(this, AlarmActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0x12345, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews view = new RemoteViews(getPackageName(), R.layout.activity_service);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 버튼 클릭시 종료 시키는 부분같은데..

        NotificationChannel channel = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //channel Id: 앱마다 unique한 ID 생성해야 하며 길면 잘릴 수 있다
            //channel Name: 사용자에게 보여지는 채널의 이름
            //channel Importance: 채널이 중요도를 의미하며, 알람
            channel = new NotificationChannel("Alarm", getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Alarm");                                        //소리알림
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(false);
            channel.setShowBadge(false);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.siren))
                //좌측에 보이는 큰 아이콘을 말한다.
                .setSmallIcon(R.drawable.siren)
                //상대바에 보이는 작은 아이콘
                .setTicker(getString(R.string.app_name))
                //상태바에 표시될 한줄 출력
                .setContentIntent(pendingIntent)
                //알람을 눌렀을 때 실행 할 작업 인텐트를 설정
                .setContent(view)
                .setContentTitle(("상태바 드래그시 보이는 타이틀"))
                //상태바 드래그 시 보이는 타이틀
                .setContentText("상태바 드래그시 보이는 서브타이틀")
                //상태바 드래그 시 보이는 서브타이틀
//                .setStyle(new NotificationCompat.BigTextStyle().bigText("더 많은 내용을 입력해야할 경우 이거 사용"))
                .setOngoing(true)
                //알람 리스트에서 사용자가 그것을 클릭하거나 좌우로 드래그해도 사라지지 않음
                .setAutoCancel(false);
        //알람 터치시 자동으로 삭제할 것인지 설정
        //

 //       vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
   //     vibrator.vibrate(pattern, 0);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId("Alarm");
        }

        startForeground(0x12345, builder.build());
    }

}
