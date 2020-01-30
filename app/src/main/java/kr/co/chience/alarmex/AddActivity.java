package kr.co.chience.alarmex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import io.realm.Realm;
import kr.co.chience.alarmex.Util.LogUtil;
import kr.co.chience.alarmex.base.BaseInterface;
import kr.co.chience.alarmex.clud.CRUDAlarm;
import kr.co.chience.alarmex.model.Alarm;
import kr.co.chience.alarmex.receiver.AlarmReceiver;

public class AddActivity extends AppCompatActivity implements BaseInterface, View.OnClickListener {

    private static final String TAG = AddActivity.class.getSimpleName();

    TimePicker timePicker;
    int aHourday, aMinute, aSwitch;
    boolean activity = true;
    Intent intent;
    Button buttonSave;
    Button buttonCancel;
    Button buttonDelete;
    boolean mon, tue, wed, thu, fri, sat, sun = false;
    String sMon, sTue, sWed, sThu, sFri, sSat, sSun = null;
    Button buttonMon, buttonTue, buttonWed, buttonThu, buttonFri, buttonSat, buttonSun;
    TextView textViewMon, textViewTue, textViewWed, textViewThu, textViewFri, textViewSat, textViewSun;
    Alarm alarm;
    Realm realm;
    boolean day;
    long position;
    long makeTime;

    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    long interval = 1000 * 60 * 60 * 24;
    long bTime;
    Calendar calendar;

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
        buttonDelete = findViewById(R.id.button_delete);
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
        realm = Realm.getDefaultInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();


    }

    @Override
    public void initListener() {
        buttonSave.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
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
        makeTime = intent.getLongExtra("makeTime", System.currentTimeMillis());
        day = intent.getBooleanExtra("day", false);
        alarm = realm.where(Alarm.class).equalTo("makeTime", makeTime).findFirst();
        if (alarm == null) {
            alarm = new Alarm();
        }
    }

    @Override
    public void initProcess() {
        buttonSetText(activity);
        if (day) {
            buttonSetText(activity);
        }

        if (!activity) {
            setData();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_save:
                if (!activity) {
                    modifyData();
                } else {
                    saveData();
                }
                break;

            case R.id.button_cancel:
                finish();
                break;

            case R.id.button_delete:
                LogUtil.e(TAG, "getMakeTime :::: " + alarm.getMakeTime());
                CRUDAlarm.deleteAlarm(alarm.getMakeTime());
                finishAffinity();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;

            case R.id.button_mon:
                if (!mon) {
                    mon = true;
                    buttonSetColor(mon, buttonMon, textViewMon);
                    sMon = "월";
                } else {
                    mon = false;
                    buttonSetColor(mon, buttonMon, textViewMon);
                }
                break;

            case R.id.button_tue:
                if (!tue) {
                    tue = true;
                    buttonSetColor(tue, buttonTue, textViewTue);
                    sTue = "화";
                } else {
                    tue = false;
                    buttonSetColor(tue, buttonTue, textViewTue);
                }
                break;

            case R.id.button_wed:
                if (!wed) {
                    wed = true;
                    buttonSetColor(wed, buttonWed, textViewWed);
                    sWed = "수";
                } else {
                    wed = false;
                    buttonSetColor(wed, buttonWed, textViewWed);
                }
                break;

            case R.id.button_thu:
                if (!thu) {
                    thu = true;
                    buttonSetColor(thu, buttonThu, textViewThu);
                    sThu = "목";
                } else {
                    thu = false;
                    buttonSetColor(thu, buttonThu, textViewThu);
                }
                break;

            case R.id.button_fri:
                if (!fri) {
                    fri = true;
                    buttonSetColor(fri, buttonFri, textViewFri);
                    sFri = "금";
                } else {
                    fri = false;
                    buttonSetColor(fri, buttonFri, textViewFri);
                }
                break;

            case R.id.button_sat:
                if (!sat) {
                    sat = true;
                    buttonSetColor(sat, buttonSat, textViewSat);
                    sSat = "토";
                } else {
                    sat = false;
                    buttonSetColor(sat, buttonSat, textViewSat);
                }
                break;

            case R.id.button_sun:
                if (!sun) {
                    sun = true;
                    buttonSetColor(sun, buttonSun, textViewSun);
                    sSun = "일";
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
            buttonDelete.setVisibility(View.GONE);
        } else {
            buttonSave.setText(R.string.modify);
            buttonDelete.setVisibility(View.VISIBLE);
        }
    }

    public void buttonDasy(boolean activity, boolean days, Button button, TextView textView) {
        if (!activity) {
            if (days) {
                button.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.button_select));
                textView.setVisibility(View.VISIBLE);
            } else {
                button.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.button_unselect));
                textView.setVisibility(View.GONE);
            }
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

    public void saveData() {
        Intent data = new Intent(getApplicationContext(), MainActivity.class);
        aHourday = timePicker.getHour();
        aMinute = timePicker.getMinute();
        String time = String.format("%02d : %02d", aHourday, aMinute);
        alarm.setMon(sMon);
        alarm.setTue(sTue);
        alarm.setWed(sWed);
        alarm.setThu(sThu);
        alarm.setFri(sFri);
        alarm.setSat(sSat);
        alarm.setSun(sSun);
        alarm.setHour(String.format("%02d", aHourday));
        alarm.setMinute(String.format("%02d", aMinute));
        alarm.setaSwitch(aSwitch);
        alarm.setMakeTime(System.currentTimeMillis());

        CRUDAlarm.addAlarm(alarm);
        finishAffinity();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void modifyData() {
        aHourday = timePicker.getHour();
        aMinute = timePicker.getMinute();

        CRUDAlarm.updateInfo(String.format("%02d", aHourday), String.format("%02d", aMinute), sMon, sTue, sWed, sThu, sFri, sSat, sSun, makeTime, aSwitch);

        finishAffinity();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void setData() {
        int hour, minute;

        mon = (intent.getBooleanExtra("mon", false));
        tue = (intent.getBooleanExtra("tue", false));
        wed = (intent.getBooleanExtra("wed", false));
        thu = (intent.getBooleanExtra("thu", false));
        fri = (intent.getBooleanExtra("fri", false));
        sat = (intent.getBooleanExtra("sat", false));
        sun = (intent.getBooleanExtra("sun", false));

        hour = Integer.parseInt((intent.getStringExtra("hour")));
        minute = Integer.parseInt(intent.getStringExtra("minute"));

        buttonSetColor(mon, buttonMon, textViewMon);
        buttonSetColor(tue, buttonTue, textViewTue);
        buttonSetColor(wed, buttonWed, textViewWed);
        buttonSetColor(thu, buttonThu, textViewThu);
        buttonSetColor(fri, buttonFri, textViewFri);
        buttonSetColor(sat, buttonSat, textViewSat);
        buttonSetColor(sun, buttonSun, textViewSun);

        timePicker.setHour(hour);
        timePicker.setMinute(minute);

    }

    private void multiAlarm() {
        List<Alarm> alarms;
        alarms = CRUDAlarm.readAllAlarm();

        Intent intent = null;
        int hour = 0;
        int minute = 0;
        long alarmTime = 0;
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        long interval = 1000 * 60 * 60 * 24;

        LogUtil.e(TAG, "Alarms Size ::::: " + alarms.size());

        if (alarms.size() != 0) {
            for (int i = 0; i < alarms.size(); i++) {
                LogUtil.e(TAG, "Alarms Get  :::::" + alarms.get(i));

                hour = Integer.parseInt(alarms.get(i).getHour());
                minute = Integer.parseInt(alarms.get(i).getMinute());

                Calendar calendar = new GregorianCalendar();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                alarmTime = calendar.getTimeInMillis();

                LogUtil.e(TAG, "Hour   :: " + hour);
                LogUtil.e(TAG, "Minute :: " + minute);

                //현 시간에서 지나간 시간이면 다음 날로 지정
                if (calendar.before(Calendar.getInstance())) {
                    calendar.add(Calendar.DATE, 1);
                    int month, date;
                    month = calendar.get(Calendar.MONTH) + 1 ;
                    date = calendar.get(Calendar.DATE);
                    LogUtil.e(TAG, "Calender ::: " + month + " / " + date );
                }

                intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                intent.putExtra("state", "on");

                PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

            }

        }

    }

}

