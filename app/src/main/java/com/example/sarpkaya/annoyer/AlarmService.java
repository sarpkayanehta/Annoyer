package com.example.sarpkaya.annoyer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by SarpKaya on 26/03/2015.
 */
public class AlarmService {
    private Context context;
    private PendingIntent mAlarmSender;
    private final MedicationModel medicationModel;
    public AlarmService(Context context, MedicationModel medicationModel) {
        this.context = context;
        this.medicationModel = medicationModel;
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("model", medicationModel);
        mAlarmSender = PendingIntent.getBroadcast(context, (int)(long)medicationModel.getSctid(),intent , 0);
    }

    public void startAlarm(){
        //Set the alarm to 10 seconds from now
        Calendar c = Calendar.getInstance();
        c.setTime(medicationModel.getMedicationTimeToBeTaken().getValue());
        long firstTime = c.getTimeInMillis();
        // Schedule the alarm!
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, firstTime, mAlarmSender);
    }
}