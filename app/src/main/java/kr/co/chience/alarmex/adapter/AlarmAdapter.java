package kr.co.chience.alarmex.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.co.chience.alarmex.AddActivity;
import kr.co.chience.alarmex.R;
import kr.co.chience.alarmex.Util.LogUtil;
import kr.co.chience.alarmex.model.Alarm;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHoler> {

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

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHoler holder, final int position) {

        Alarm item = items.get(position);
        holder.time.setText(items.get(position).getTime());
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
                LogUtil.e("WhereAreYou", items.get(position).getTime());
                Intent intent = new Intent(context, AddActivity.class);
                String time = items.get(position).getTime();
        //        String days = items.get(position).getDays();
                intent.putExtra("time", time);
        //        intent.putExtra("days", days);
                intent.putExtra("activity", false);
                context.startActivity(intent);
            }
        });

      //  holder.aSwitch.set;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class AlarmViewHoler extends RecyclerView.ViewHolder {

        TextView time;
        TextView days;
        TextView mon, tue, wed, thu, fri, sat, sun;
        Switch aSwitch;

        public AlarmViewHoler(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.textview_time);
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



}

