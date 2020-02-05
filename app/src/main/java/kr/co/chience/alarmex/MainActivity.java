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
import kr.co.chience.alarmex.Util.SetAlarm;
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
        SetAlarm.multiAlarm(getApplicationContext(), TAG);
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



}


