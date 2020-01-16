package kr.co.chience.alarmex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import kr.co.chience.alarmex.Util.LogUtil;
import kr.co.chience.alarmex.base.BaseInterface;
import kr.co.chience.alarmex.clud.CRUDAlarm;
import kr.co.chience.alarmex.model.Alarm;

public class AddActivity extends AppCompatActivity implements BaseInterface, View.OnClickListener {

    private static final String TAG = AddActivity.class.getSimpleName();

    TimePicker timePicker;
    int aHourday, aMinute;
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
    int itemPostion;
    int position;
    Realm realm;
    List<Alarm> datas;


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
        alarm = new Alarm();
        realm = Realm.getDefaultInstance();
        datas = CRUDAlarm.readAllAlarm();
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
        itemPostion = intent.getIntExtra("position", 100);
    }

    @Override
    public void initProcess() {
        buttonSetText(activity);

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
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;

            case R.id.button_delete:
                LogUtil.e(TAG, "position :::: " + itemPostion);

                CRUDAlarm.deleteAlarm(itemPostion);
                if (datas.size() > itemPostion) {
                    itemPostion++;
                    for (int i = itemPostion; itemPostion <= datas.size(); i++) {
                        RealmResults<Alarm> alarm = realm.where(Alarm.class).findAll();
                        CRUDAlarm.updateId(itemPostion);
                    }
                }

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
        //포지션값
        int pos = 0;
        if (datas.size() > 0) {
            pos = datas.get(datas.size() - 1).getPosition() + 1;
        }
        Intent data = new Intent(getApplicationContext(), MainActivity.class);
        data.putExtra("position", position);
        aHourday = timePicker.getHour();
        aMinute = timePicker.getMinute();
        String time = String.format("%d : %d", aHourday, aMinute);
        alarm.setMon(sMon);
        alarm.setTue(sTue);
        alarm.setWed(sWed);
        alarm.setThu(sThu);
        alarm.setFri(sFri);
        alarm.setSat(sSat);
        alarm.setSun(sSun);
        alarm.setTime(time);
        alarm.setPosition(pos);

        CRUDAlarm.addAlarm(alarm);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void modifyData() {
        aHourday = timePicker.getHour();
        aMinute = timePicker.getMinute();
        String time = String.format("%d : %d", aHourday, aMinute);
        alarm.setMon(sMon);
        alarm.setTue(sTue);
        alarm.setWed(sWed);
        alarm.setThu(sThu);
        alarm.setFri(sFri);
        alarm.setSat(sSat);
        alarm.setSun(sSun);
        alarm.setTime(time);
        CRUDAlarm.updateInfo(itemPostion, time, sMon, sTue, sWed, sThu, sFri, sSat, sSun);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}

