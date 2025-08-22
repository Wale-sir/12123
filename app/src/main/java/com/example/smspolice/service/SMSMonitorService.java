package com.example.smspolice.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.smspolice.MainActivity;
import com.example.smspolice.R;

public class SMSMonitorService extends Service {
    
    private static final String TAG = "SMSMonitorService";
    private static final String CHANNEL_ID = "SMSMonitorChannel";
    private static final int NOTIFICATION_ID = 1001;
    
    private static boolean isServiceRunning = false;
    
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "SMSMonitorService created");
        createNotificationChannel();
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "SMSMonitorService started");
        isServiceRunning = true;
        
        // 创建前台服务通知
        startForeground(NOTIFICATION_ID, createNotification());
        
        return START_STICKY; // 服务被杀死后自动重启
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "SMSMonitorService destroyed");
        isServiceRunning = false;
    }
    
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "短信监控服务",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel.setDescription("监控交警短信的服务");
            
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
    
    private Notification createNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, notificationIntent,
                PendingIntent.FLAG_IMMUTABLE
        );
        
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("短信监控服务")
                .setContentText("正在监控交警短信...")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .build();
    }
    
    public static boolean isServiceRunning() {
        return isServiceRunning;
    }
} 