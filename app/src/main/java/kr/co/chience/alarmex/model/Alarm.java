package kr.co.chience.alarmex.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Alarm extends RealmObject {

    String time;
    String mon;
    String tue;
    String wed;
    String thu;
    String fri;
    String sat;
    String sun;
    long makeTime;
    int aSwitch;



    public Alarm() {

    }

    public Alarm(String time, String mon, String tue, String wed, String thu, String fri, String sat, String sun, long makeTime, int aSwitch) {
        this.time = time;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.makeTime = makeTime;
        this.aSwitch = aSwitch;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getTue() {
        return tue;
    }

    public void setTue(String tue) {
        this.tue = tue;
    }

    public String getWed() {
        return wed;
    }

    public void setWed(String wed) {
        this.wed = wed;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public String getFri() {
        return fri;
    }

    public void setFri(String fri) {
        this.fri = fri;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public long getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(long makeTime) {
        this.makeTime = makeTime;
    }

    public int getaSwitch() {
        return aSwitch;
    }

    public void setaSwitch(int aSwitch) {
        this.aSwitch = aSwitch;
    }
}
