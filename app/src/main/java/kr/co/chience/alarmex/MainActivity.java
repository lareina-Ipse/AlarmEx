package kr.co.chience.alarmex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import kr.co.chience.alarmex.Util.LogUtil;
import kr.co.chience.alarmex.adapter.AlarmAdapter;
import kr.co.chience.alarmex.base.BaseInterface;
import kr.co.chience.alarmex.clud.CRUDAlarm;
import kr.co.chience.alarmex.model.Alarm;
import kr.co.chience.alarmex.receiver.AlarmReceiver;
import kr.co.chience.alarmex.service.AlarmService;

public class MainActivity extends AppCompatActivity implements BaseInterface, View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Alarm> arrayList;
    AlarmAdapter mAdapter;
    Button buttonAdd;
    int hour, minute;
    String time;
    Intent intent;
    TextView textViewMon, textViewTue, textViewWed, textViewThu, textViewFri, textViewSat, textViewSun;
    Realm realm;
    RealmChangeListener realmChangeListener;
    CRUDAlarm.ItemAlarm itemAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListener();
        initItems();
        initProcess();

    }

    @Override
    public void initViews() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        arrayList = new ArrayList<Alarm>();
        realm = Realm.getDefaultInstance();
        itemAlarm = new CRUDAlarm.ItemAlarm(realm);
        itemAlarm.selectFromDB();
        mAdapter = new AlarmAdapter(this, itemAlarm.justRefresh());
        buttonAdd = findViewById(R.id.button_add);
        textViewMon = findViewById(R.id.textview_mon);
        textViewTue = findViewById(R.id.textview_tue);
        textViewWed = findViewById(R.id.textview_wed);
        textViewThu = findViewById(R.id.textview_thu);
        textViewFri = findViewById(R.id.textview_fri);
        textViewSat = findViewById(R.id.textview_sat);
        textViewSun = findViewById(R.id.textview_sun);

    }

    @Override
    public void initListener() {
        buttonAdd.setOnClickListener(this);
    }

    @Override
    public void initItems() {

    }

    @Override
    public void initProcess() {
        reFresh();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        multiAlarm();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add:
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
                break;
        }
    }

    private void reFresh() {
        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                AlarmAdapter alarmAdapter = new AlarmAdapter(MainActivity.this, itemAlarm.justRefresh());
                mRecyclerView.setAdapter(alarmAdapter);
                mAdapter.notifyDataSetChanged();
            }
        };
        realm.addChangeListener(realmChangeListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.removeAllChangeListeners();
        realm.close();

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

        LogUtil.e(TAG, "Alarms Count ::::: " + alarms.size());

        if (alarms.size() != 0) {


            for (int i = 0; i < alarms.size(); i++) {
                LogUtil.e(TAG, "Alarms Get  :::::" + alarms.get(i));
                if (alarms.get(i).getaSwitch() == 0) {
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
                        month = calendar.get(Calendar.MONTH) + 1;
                        date = calendar.get(Calendar.DATE);
                        LogUtil.e(TAG, "Calender ::: " + month + " / " + date);
                    }

                    intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    intent.putExtra("state", "on");

                    PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
                } else {
                    LogUtil.e(TAG, "알람 숨김 된 내용임 ::: " + alarms.get(i).getaSwitch());
                }

            }

        }

    }
}


