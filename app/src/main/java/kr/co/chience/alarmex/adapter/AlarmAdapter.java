package kr.co.chience.alarmex.adapter;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import kr.co.chience.alarmex.AddActivity;
import kr.co.chience.alarmex.R;
import kr.co.chience.alarmex.Util.LogUtil;
import kr.co.chience.alarmex.clud.CRUDAlarm;
import kr.co.chience.alarmex.model.Alarm;
import kr.co.chience.alarmex.receiver.AlarmReceiver;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHoler> {

    private static final String TAG = AlarmAdapter.class.getSimpleName();
    Context context;
    ArrayList<Alarm> items;

    long makeTime;
    long swaitchMaketime;


    public AlarmAdapter(Context context, ArrayList<Alarm> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public AlarmViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_alarm, parent, false);
        RecyclerView.ViewHolder viewHolder = new AlarmViewHoler(view);

        return (AlarmViewHoler) viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AlarmViewHoler holder, final int position) {

        final Alarm item = items.get(position);
        holder.hour.setText(items.get(position).getHour());
        holder.minute.setText(items.get(position).getMinute());
        holder.mon.setText(items.get(position).getMon());
        holder.tue.setText(items.get(position).getTue());
        holder.wed.setText(items.get(position).getWed());
        holder.thu.setText(items.get(position).getThu());
        holder.fri.setText(items.get(position).getFri());
        holder.sat.setText(items.get(position).getSat());
        holder.sun.setText(items.get(position).getSun());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeTime = items.get(position).getMakeTime();
                Intent intent = new Intent(context, AddActivity.class);

                intent.putExtra("makeTime", makeTime);
                intent.putExtra("activity", false);

                getData(makeTime, intent);
                context.startActivity(intent);
            }
        });

        int switchonOff = items.get(position).getaSwitch();
        if (switchonOff == 0) {
            holder.aSwitch.setChecked(true);
            holder.hour.setTextColor(context.getColor(R.color.black));
            holder.minute.setTextColor(context.getColor(R.color.black));
            holder.text.setTextColor(context.getColor(R.color.black));
        } else {
            holder.aSwitch.setChecked(false);
            holder.hour.setTextColor(context.getColor(R.color.colorText));
            holder.minute.setTextColor(context.getColor(R.color.colorText));
            holder.text.setTextColor(context.getColor(R.color.colorText));
        }

        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LogUtil.i(TAG, "onCheckedChanged : " + isChecked + " !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                if (isChecked) {
                    makeTime = items.get(position).getSwitchMaketime();
                    CRUDAlarm.updateSwitch(makeTime, 0);
                    holder.aSwitch.setChecked(true);
                    multiAlarm(context);
                } else {
                    makeTime = items.get(position).getSwitchMaketime();
                    CRUDAlarm.updateSwitch(makeTime, 1);
                    holder.aSwitch.setChecked(false);
                    multiAlarm(context);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class AlarmViewHoler extends RecyclerView.ViewHolder {

        TextView days;
        TextView hour, minute;
        TextView mon, tue, wed, thu, fri, sat, sun;
        TextView text;
        Switch aSwitch;

        public AlarmViewHoler(@NonNull View itemView) {
            super(itemView);

            hour = itemView.findViewById(R.id.textview_hour);
            minute = itemView.findViewById(R.id.textview_minute);
            days = itemView.findViewById(R.id.textview_days);
            mon = itemView.findViewById(R.id.textview_mon);
            tue = itemView.findViewById(R.id.textview_tue);
            wed = itemView.findViewById(R.id.textview_wed);
            thu = itemView.findViewById(R.id.textview_thu);
            fri = itemView.findViewById(R.id.textview_fri);
            sat = itemView.findViewById(R.id.textview_sat);
            sun = itemView.findViewById(R.id.textview_sun);
            text = itemView.findViewById(R.id.text);
            aSwitch = itemView.findViewById(R.id.switch_onoff);
        }
    }

    public boolean days(String days) {
        if (days == null) {
            return false;
        } else {
            return true;
        }
    }

    public void getData(long makeTime, Intent intent) {

        Boolean mon, tue = false, wed = false, thu = false, fri = false, sat = false, sun = false;
        String sMon, sTue, sWed, sThu, sFri, sSat, sSun, hour, minute;

        sMon = CRUDAlarm.readData(makeTime).getMon();
        sTue = CRUDAlarm.readData(makeTime).getTue();
        sWed = CRUDAlarm.readData(makeTime).getWed();
        sThu = CRUDAlarm.readData(makeTime).getThu();
        sFri = CRUDAlarm.readData(makeTime).getFri();
        sSat = CRUDAlarm.readData(makeTime).getSat();
        sSun = CRUDAlarm.readData(makeTime).getSun();
        hour = CRUDAlarm.readData(makeTime).getHour();
        minute = CRUDAlarm.readData(makeTime).getMinute();

        mon = days(sMon);
        tue = days(sTue);
        wed = days(sWed);
        thu = days(sThu);
        fri = days(sFri);
        sat = days(sSat);
        sun = days(sSun);

        intent.putExtra("mon", mon);
        intent.putExtra("tue", tue);
        intent.putExtra("wed", wed);
        intent.putExtra("thu", thu);
        intent.putExtra("fri", fri);
        intent.putExtra("sat", sat);
        intent.putExtra("sun", sun);
        intent.putExtra("hour", hour);
        intent.putExtra("minute", minute);

    }

    private void multiAlarm(Context context) {
        List<Alarm> alarms;
        alarms = CRUDAlarm.readAllAlarm();

        Intent intent = null;
        int hour = 0;
        int minute = 0;
        long alarmTime = 0;
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long interval = 1000 * 60 * 60 * 24;

        LogUtil.e(TAG, "Alarms Size ::::: " + alarms.size());

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

                    intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);
                    intent.putExtra("state", "on");

                    PendingIntent sender = PendingIntent.getBroadcast(context.getApplicationContext(), i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
                } else {
                    LogUtil.e(TAG, "알람 숨김 된 내용임 ::: " + alarms.get(i).getaSwitch());
                }

            }

        }

    }

}

