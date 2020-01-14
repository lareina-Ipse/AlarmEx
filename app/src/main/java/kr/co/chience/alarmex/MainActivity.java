package kr.co.chience.alarmex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.chience.alarmex.Util.LogUtil;
import kr.co.chience.alarmex.adapter.AlarmAdapter;
import kr.co.chience.alarmex.base.BaseInterface;
import kr.co.chience.alarmex.model.Alarm;

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
    boolean mon, tue, wed, thu, fri, sat, sun = false;
    TextView textViewMon, textViewTue, textViewWed, textViewThu, textViewFri, textViewSat, textViewSun;


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
        arrayList = new ArrayList<>();
        mAdapter = new AlarmAdapter(arrayList);
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

        if (!textViewMon.equals(null)) {
            addItem(time);
        }
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    public void addItem(String time) {
        intent = getIntent();
        hour = intent.getIntExtra("hour", 0);
        minute = intent.getIntExtra("minute", 0);
        mon = intent.getBooleanExtra("mon", false);
        tue = intent.getBooleanExtra("tue", false);
        wed = intent.getBooleanExtra("wed", false);
        thu = intent.getBooleanExtra("thu", false);
        fri = intent.getBooleanExtra("fri", false);
        sat = intent.getBooleanExtra("sat", false);
        sun = intent.getBooleanExtra("sun", false);

        time = String.format("%d : %d", hour, minute);

        if (!textViewMon.equals(null)) {
            checkDays(mon, textViewMon);
            checkDays(tue, textViewTue);
            checkDays(wed, textViewWed);
            checkDays(thu, textViewThu);
            checkDays(fri, textViewFri);
            checkDays(sat, textViewSat);
            checkDays(sun, textViewSun);
        }

        LogUtil.e(TAG, "hour :::::: " + hour);
        LogUtil.e(TAG, "minute :::::: " + minute);
        LogUtil.e(TAG, "mon :::::: " + mon);
        LogUtil.e(TAG, "tue :::::: " + tue);
        LogUtil.e(TAG, "wed :::::: " + wed);
        LogUtil.e(TAG, "thu :::::: " + thu);
        LogUtil.e(TAG, "fri :::::: " + fri);
        LogUtil.e(TAG, "sat :::::: " + sat);
        LogUtil.e(TAG, "sun :::::: " + sun);


    }

    public void checkDays(boolean day, TextView textView) {
        if (day) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add:
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);

                break;
        }
    }




}
