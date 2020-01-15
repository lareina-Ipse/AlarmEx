package kr.co.chience.alarmex.clud;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import kr.co.chience.alarmex.model.Alarm;

public class CRUDAlarm {

    private static final String TAG = CRUDAlarm.class.getSimpleName();

    //인덱스
    private final static int alarmIndex() {
        Realm realm = Realm.getDefaultInstance();
        Number currentIdNum = realm.where(Alarm.class).max("no");
        int nextId;
        if (currentIdNum == null) {
            nextId = 0;
        } else {
            nextId = currentIdNum.intValue() + 1;
        }
        return nextId;
    }

    //데이터 저장
    public final static void addAlarm(final Alarm alarm) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                int index = CRUDAlarm.alarmIndex();
                Alarm realmAlarm = realm.createObject(Alarm.class, index);
                realmAlarm.setTime(alarm.getTime());
                realmAlarm.setMon(alarm.getMon());
                realmAlarm.setTue(alarm.getTue());
                realmAlarm.setWed(alarm.getWed());
                realmAlarm.setThu(alarm.getThu());
                realmAlarm.setFri(alarm.getFri());
                realmAlarm.setSat(alarm.getSat());
                realmAlarm.setSun(alarm.getSun());
            }
        });
    }

    //저장된 데이터 불러오기
    public final static List<Alarm> readAllAlarm() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Alarm> alarms = realm.where(Alarm.class).findAll();
        return alarms;
    }

    //받아온 데이터 Recyclerview에 보여주기
    public static class ItemAlarm {
        Realm realm;
        RealmResults<Alarm> alarms;

        public ItemAlarm(Realm realm) {
            this.realm = realm;
        }

        public void selectFromDB() {
            alarms = realm.where(Alarm.class).findAll();
        }

        public ArrayList<Alarm> justRefresh() {
            ArrayList<Alarm> listItem = new ArrayList<>();
            for (Alarm alarm : alarms) {
                listItem.add(alarm);
            }
            return listItem;
        }
    }
}
