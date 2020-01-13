package kr.co.chience.alarmex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        timePicker = findViewById(R.id.timepicker);

        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

    }
}
