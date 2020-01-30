package kr.co.chience.alarmex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kr.co.chience.alarmex.receiver.AlarmReceiver;
import kr.co.chience.alarmex.service.AlarmService;

public class AlarmActivity extends AppCompatActivity {

    Button button;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        button = findViewById(R.id.button_stop);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

    }

    private void stop() {
        stopService(new Intent(AlarmActivity.this, AlarmService.class));
        finish();
    }

}
