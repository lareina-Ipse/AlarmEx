package kr.co.chience.alarmex.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.co.chience.alarmex.AddActivity;
import kr.co.chience.alarmex.R;
import kr.co.chience.alarmex.clud.CRUDAlarm;
import kr.co.chience.alarmex.model.Alarm;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHoler> {

    private static final String TAG = AlarmAdapter.class.getSimpleName();
    Context context;
    ArrayList<Alarm> items;

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

    @SuppressLint("ResourceAsColor")
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
                long makeTime;

                makeTime = items.get(position).getMakeTime();
                Intent intent = new Intent(context, AddActivity.class);

                intent.putExtra("makeTime", makeTime);
                intent.putExtra("activity", false);

                getData(makeTime, intent);
                context.startActivity(intent);
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


}

