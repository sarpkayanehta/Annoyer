package com.example.sarpkaya.annoyer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by SarpKaya on 26/03/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MedicationModel medicationModel = (MedicationModel) intent.getExtras().getSerializable("model");
        notifyPhone(medicationModel, context);
    }

    public void notifyPhone(MedicationModel medication, Context context) {
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.warning_sign)
                        .setContentTitle("Medication Time")
                        .setContentText(medication.getName())
                        .setSound(soundUri);
        Intent resultIntent = new Intent(context, Medications.class);
        mBuilder.setOngoing(true);
        mBuilder.setAutoCancel(true);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(Medications.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify((int)(long)medication.getSctid(), mBuilder.build());
    }
}