package com.pablolopezs.grepaut.broadcast;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationManagerCompat;

import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.dao.GreapautApplication;

import java.util.Random;

/*Que va hacer cuando se produce el evento del broadcast*/
public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification.Builder builder = new Notification.Builder(this, GreapautApplication.CHANNEL_ID)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_menu_send)
                .setContentText("Se recibio un SMS")
                .setContentTitle("Service");

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        notificationManagerCompat.notify(new Random().nextInt(100), builder.build());
        return super.onStartCommand(intent, flags, startId);

    }
}
