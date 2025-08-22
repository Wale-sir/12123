package com.example.smspolice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.smspolice.service.SMSMonitorService;

public class BootReceiver extends BroadcastReceiver {
    
    private static final String TAG = "BootReceiver";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "收到广播: " + action);
        
        if (Intent.ACTION_BOOT_COMPLETED.equals(action) ||
            "android.intent.action.QUICKBOOT_POWERON".equals(action) ||
            "android.intent.action.REBOOT".equals(action)) {
            
            Log.d(TAG, "系统启动完成，启动短信监控服务");
            
            // 延迟启动服务，确保系统完全启动
            try {
                Thread.sleep(10000); // 等待10秒
            } catch (InterruptedException e) {
                Log.e(TAG, "延迟启动被中断", e);
            }
            
            // 启动短信监控服务
            Intent serviceIntent = new Intent(context, SMSMonitorService.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent);
            } else {
                context.startService(serviceIntent);
            }
            
            Log.d(TAG, "短信监控服务已启动");
        }
    }
} 