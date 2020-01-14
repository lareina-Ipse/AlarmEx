package kr.co.chience.alarmex.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.co.chience.alarmex.AddActivity;
import kr.co.chience.alarmex.MainActivity;
import kr.co.chience.alarmex.R;
import kr.co.chience.alarmex.Util.LogUtil;
import kr.co.chience.alarmex.model.Alarm;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHoler> {

    private ArrayList<Alarm> items = new ArrayList<Alarm>();
    Context context;

    public AlarmAdapter(ArrayList<Alarm> items) {
        this.items = items;
    }

    public AlarmAdapter() {

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
      //  holder.days.setText(items.get(position).getDays());


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

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class AlarmViewHoler extends RecyclerView.ViewHolder {

        TextView time;
        TextView days;
        TextView mon, tue, wed, thu, fri, sat, sun;

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

        }
    }



}

