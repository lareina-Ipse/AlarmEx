package kr.co.chience.alarmex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.nio.channels.InterruptedByTimeoutException;
import java.sql.Time;
import java.util.Calendar;

import kr.co.chience.alarmex.Util.LogUtil;
import kr.co.chience.alarmex.base.BaseInterface;

public class AddActivity extends AppCompatActivity implements BaseInterface, View.OnClickListener {

    private static final String TAG = AddActivity.class.getSimpleName();

    TimePicker timePicker;
    int aHourday, aMinute;
    boolean activity = true;
    Intent intent;
    Button buttonSave;
    Button buttonCancel;
    boolean mon, tue, wed, thu, fri, sat, sun = false;
    Button buttonMon, buttonTue, buttonWed, buttonThu, buttonFri, buttonSat, buttonSun;
    TextView textViewMon, textViewTue, textViewWed, textViewThu, textViewFri, textViewSat, textViewSun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initViews();
        initListener();
        initItems();
        initProcess();

    }

    @Override
    public void initViews() {
        timePicker = findViewById(R.id.timepicker);
        buttonSave = findViewById(R.id.button_save);
        buttonCancel = findViewById(R.id.button_cancel);
        buttonMon = findViewById(R.id.button_mon);
        buttonTue = findViewById(R.id.button_tue);
        buttonWed = findViewById(R.id.button_wed);
        buttonThu = findViewById(R.id.button_thu);
        buttonFri = findViewById(R.id.button_fri);
        buttonSat = findViewById(R.id.button_sat);
        buttonSun = findViewById(R.id.button_sun);
        textViewMon = findViewById(R.id.textview_mon);
        textViewTue = findViewById(R.id.textview_tue);
        textViewWed = findViewById(R.id.textview_wed);
        textViewThu = findViewById(R.id.textview_thu);
        textViewFri = findViewById(R.id.textview_fri);
        textViewSat = findViewById(R.id.textview_sat);
        textViewSun = findViewById(R.id.textview_sun);

        intent = getIntent();
    }

    @Override
    public void initListener() {
        buttonSave.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
        buttonMon.setOnClickListener(this);
        buttonTue.setOnClickListener(this);
        buttonWed.setOnClickListener(this);
        buttonThu.setOnClickListener(this);
        buttonFri.setOnClickListener(this);
        buttonSat.setOnClickListener(this);
        buttonSun.setOnClickListener(this);



    }

    @Override
    public void initItems() {
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        activity = intent.getBooleanExtra("activity", true);
    }

    @Override
    public void initProcess() {
        buttonSetText(activity);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_save:
                aHourday = timePicker.getHour();
                aMinute = timePicker.getMinute();
                Intent intent;
                //세이브 버튼이 눌리면서, time과 요일의 값을 MainActivity에 보내줘야한다.
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("hour", aHourday);
                intent.putExtra("minute", aMinute);
                intent.putExtra("mon", mon);
                intent.putExtra("tue", tue);
                intent.putExtra("wed", wed);
                intent.putExtra("thu", thu);
                intent.putExtra("fri", fri);
                intent.putExtra("sat", sat);
                intent.putExtra("sun", sun);
                startActivity(intent);
                break;
            case R.id.button_cancel:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.button_mon:
                if (!mon) {
                    mon = true;
                    buttonSetColor(mon, buttonMon, textViewMon);
                } else {
                    mon = false;
                    buttonSetColor(mon, buttonMon, textViewMon);
                }
                break;
            case R.id.button_tue:
                if (!tue) {
                    tue = true;
                    buttonSetColor(tue, buttonTue, textViewTue);
                } else {
                    tue = false;
                    buttonSetColor(tue, buttonTue, textViewTue);
                }
                break;
            case R.id.button_wed:
                if (!wed) {
                    wed = true;
                    buttonSetColor(wed, buttonWed, textViewWed);
                } else {
                    wed = false;
                    buttonSetColor(wed, buttonWed, textViewWed);
                }
                break;
            case R.id.button_thu:
                if (!thu) {
                    thu = true;
                    buttonSetColor(thu, buttonThu, textViewThu);
                } else {
                    thu = false;
                    buttonSetColor(thu, buttonThu, textViewThu);
                }
                break;
            case R.id.button_fri:
                if (!fri) {
                    fri = true;
                    buttonSetColor(fri, buttonFri, textViewFri);
                } else {
                    fri = false;
                    buttonSetColor(fri, buttonFri, textViewFri);
                }
                break;
            case R.id.button_sat:
                if (!sat) {
                    sat = true;
                    buttonSetColor(sat, buttonSat, textViewSat);
                } else {
                    sat = false;
                    buttonSetColor(sat, buttonSat,textViewSat);
                }
                break;
            case R.id.button_sun:
                if (!sun) {
                    sun = true;
                    buttonSetColor(sun, buttonSun, textViewSun);
                } else {
                    sun = false;
                    buttonSetColor(sun, buttonSun, textViewSun);
                }
                break;
            default:
                break;
        }
    }

    public void buttonSetText(boolean activity) {
        if (activity) {
            buttonSave.setText(R.string.save);
        } else {
            buttonSave.setText(R.string.modify);
        }
    }

    public void buttonSetColor(boolean color, Button button, TextView textView) {
        if (!color) {
            button.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.button_unselect));
            textView.setVisibility(View.GONE);
        } else {
            button.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.button_select));
            textView.setVisibility(View.VISIBLE);
        }
    }

}

