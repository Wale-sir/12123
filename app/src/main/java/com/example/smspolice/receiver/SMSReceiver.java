package com.example.smspolice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.example.smspolice.service.SMSMonitorService;
import com.example.smspolice.util.PoliceSMSChecker;
import com.example.smspolice.util.SoundPlayer;

public class SMSReceiver extends BroadcastReceiver {
    
    private static final String TAG = "SMSReceiver";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Log.d(TAG, "收到短信");
            
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                        String sender = smsMessage.getDisplayOriginatingAddress();
                        String messageBody = smsMessage.getMessageBody();
                        
                        Log.d(TAG, "发送者: " + sender);
                        Log.d(TAG, "短信内容: " + messageBody);
                        
                        // 检查是否为交警短信
                        if (PoliceSMSChecker.isPoliceSMS(sender, messageBody)) {
                            Log.d(TAG, "检测到交警短信！");
                            
                            // 播放铃声提醒
                            SoundPlayer.playPoliceAlert(context);
                            
                            // 显示通知
                            showPoliceNotification(context, sender, messageBody);
                            
                            // 震动提醒
                            SoundPlayer.vibrate(context);
                        }
                    }
                }
            }
        }
    }
    
    private void showPoliceNotification(Context context, String sender, String messageBody) {
        // 这里可以添加更详细的通知逻辑
        Toast.makeText(context, "收到交警短信！", Toast.LENGTH_LONG).show();
    }
} 