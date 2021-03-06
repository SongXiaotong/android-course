package com.example.a12524.experimentone_week6;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("start")) {
            Log.e("hhh","MyReceiver1");
            Bundle bundle = intent.getExtras();
            listitem food = (listitem) bundle.getSerializable("food");
            Bitmap bm= BitmapFactory.decodeResource(context.getResources(),R.mipmap.empty_star);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            String channel_id = "channel_1";
            NotificationChannel channel = null;
            if(channel == null){
                String name = "my_channel";
                String des = "channel_packet";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                channel = new NotificationChannel(channel_id, name, importance);
                channel.setDescription(des);
                channel.enableLights(true);
                manager.createNotificationChannel(channel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel_id);
            builder.setContentTitle("今日推荐")
                    .setContentText(food.getName())
                    .setTicker("您有一条新消息")
                    .setWhen(System.currentTimeMillis())
                    .setLargeIcon(bm)
                    .setSmallIcon(R.mipmap.empty_star)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true);

            Intent mInent = new Intent(context, ListActions.class);
            mInent.putExtras(bundle);
            PendingIntent mPendingIntent=PendingIntent.getActivity(context,0,mInent,PendingIntent.FLAG_UPDATE_CURRENT);
              builder.setContentIntent(mPendingIntent);
              Notification notify=builder.build();
            manager.notify(0,notify);
        }
    }
}
