package kr.co.chience.alarmex.clud;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import kr.co.chience.alarmex.Util.LogUtil;
import kr.co.chience.alarmex.model.Alarm;

public class CRUDAlarm {

    private static final String TAG = CRUDAlarm.class.getSimpleName();

    //데이터 저장
    public final static void addAlarm(final Alarm alarm) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Alarm realmAlarm = realm.createObject(Alarm.class);
                realmAlarm.setTime(alarm.getTime());
                realmAlarm.setMon(alarm.getMon());
                realmAlarm.setTue(alarm.getTue());
                realmAlarm.setWed(alarm.getWed());
                realmAlarm.setThu(alarm.getThu());
                realmAlarm.setFri(alarm.getFri());
                realmAlarm.setSat(alarm.getSat());
                realmAlarm.setSun(alarm.getSun());
                realmAlarm.setMakeTime(alarm.getMakeTime());

                LogUtil.e(TAG, "time :::: " + alarm.getTime());
                LogUtil.e(TAG, "Mon :::: " + alarm.getMon());
                LogUtil.e(TAG, "Tue :::: " + alarm.getTue());
                LogUtil.e(TAG, "Wed :::: " + alarm.getWed());
                LogUtil.e(TAG, "Thu :::: " + alarm.getThu());
                LogUtil.e(TAG, "Fri :::: " + alarm.getFri());
                LogUtil.e(TAG, "Sat :::: " + alarm.getSat());
                LogUtil.e(TAG, "Sun :::: " + alarm.getSun());
                LogUtil.e(TAG, "MakeTime :::: " + alarm.getMakeTime());
                LogUtil.e(TAG, "===============================================================");

            }
        });
    }

    //저장된 데이터 불러오기
    public final static List<Alarm> readAllAlarm() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Alarm> alarms = realm.where(Alarm.class).findAll();
        for (Alarm alarm : alarms) {

        }

        return alarms;
    }

    public final static void updateInfo(String time,
                                        String mon, String tue,
                                        String wed, String thu,
                                        String fri, String sat,
                                        String sun,
                                        long makeTime) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Alarm alarm = realm.where(Alarm.class).equalTo("makeTime", makeTime).findFirst();

        alarm.setTime(time);
        alarm.setMon(mon);
        alarm.setTue(tue);
        alarm.setWed(wed);
        alarm.setThu(thu);
        alarm.setFri(fri);
        alarm.setSat(sat);
        alarm.setSun(sun);

        realm.insertOrUpdate(alarm);
        realm.commitTransaction();

//        LogUtil.e(TAG, "============================수정===============================");
//        LogUtil.e(TAG, "time :::: " + alarm.getTime());
//        LogUtil.e(TAG, "Mon :::: " + alarm.getMon());
//        LogUtil.e(TAG, "Tue :::: " + alarm.getTue());
//        LogUtil.e(TAG, "Wed :::: " + alarm.getWed());
//        LogUtil.e(TAG, "Thu :::: " + alarm.getThu());
//        LogUtil.e(TAG, "Fri :::: " + alarm.getFri());
//        LogUtil.e(TAG, "Sat :::: " + alarm.getSat());
//        LogUtil.e(TAG, "Sun :::: " + alarm.getSun());
//        LogUtil.e(TAG, "Position :::: " + alarm.getMakeTime());
//        LogUtil.e(TAG, "===============================================================");


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

    public final static void deleteAlarm(long makeTime) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Alarm alarm = realm.where(Alarm.class).equalTo("makeTime", makeTime).findFirst();
        alarm.deleteFromRealm();
        realm.commitTransaction();
    }

    public final static Alarm readData(long makeTime) {
        Realm realm = Realm.getDefaultInstance();
        Alarm alarm = realm.where(Alarm.class).equalTo("makeTime", makeTime).findFirst();

        LogUtil.e(TAG, "============================불러오기===============================");
        LogUtil.e(TAG, "time :::: " + alarm.getTime());
        LogUtil.e(TAG, "Mon :::: " + alarm.getMon());
        LogUtil.e(TAG, "Tue :::: " + alarm.getTue());
        LogUtil.e(TAG, "Wed :::: " + alarm.getWed());
        LogUtil.e(TAG, "Thu :::: " + alarm.getThu());
        LogUtil.e(TAG, "Fri :::: " + alarm.getFri());
        LogUtil.e(TAG, "Sat :::: " + alarm.getSat());
        LogUtil.e(TAG, "Sun :::: " + alarm.getSun());
        LogUtil.e(TAG, "Position :::: " + alarm.getMakeTime());
        LogUtil.e(TAG, "===============================================================");

        return alarm;
    }
}


