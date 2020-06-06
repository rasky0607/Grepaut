package com.pablolopezs.grepaut.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.pablolopezs.grepaut.ui.login.SplashActivity;
/*Este boradcast esta atento al los eventos del servicio*/
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, SplashActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        context.startService(new Intent(context, MyService.class));
    }
}
