package kr.co.chience.alarmex.model;

import android.widget.TextView;

public class Alarm {

    String time;

    public Alarm(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
