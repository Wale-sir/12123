package com.example.smspolice.util;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

public class SoundPlayer {
    
    private static final String TAG = "SoundPlayer";
    private static MediaPlayer mediaPlayer;
    
    /**
     * 播放交警短信提醒铃声
     */
    public static void playPoliceAlert(Context context) {
        try {
            // 停止之前的播放
            stopPlayback();
            
            // 获取系统默认铃声
            Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            
            // 创建MediaPlayer
            mediaPlayer = new MediaPlayer();
            
            // 设置音频属性
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build();
                mediaPlayer.setAudioAttributes(audioAttributes);
            } else {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            }
            
            // 设置数据源
            mediaPlayer.setDataSource(context, ringtoneUri);
            
            // 设置循环播放
            mediaPlayer.setLooping(true);
            
            // 准备播放
            mediaPlayer.prepare();
            
            // 开始播放
            mediaPlayer.start();
            
            Log.d(TAG, "开始播放交警短信提醒铃声");
            
            // 5秒后自动停止
            new android.os.Handler().postDelayed(() -> {
                stopPlayback();
            }, 5000);
            
        } catch (Exception e) {
            Log.e(TAG, "播放铃声失败", e);
        }
    }
    
    /**
     * 停止播放
     */
    public static void stopPlayback() {
        if (mediaPlayer != null) {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
                mediaPlayer = null;
                Log.d(TAG, "停止播放铃声");
            } catch (Exception e) {
                Log.e(TAG, "停止播放失败", e);
            }
        }
    }
    
    /**
     * 震动提醒
     */
    public static void vibrate(Context context) {
        try {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null && vibrator.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // Android 8.0及以上使用VibrationEffect
                    VibrationEffect effect = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE);
                    vibrator.vibrate(effect);
                } else {
                    // 旧版本直接震动
                    vibrator.vibrate(1000);
                }
                Log.d(TAG, "执行震动提醒");
            }
        } catch (Exception e) {
            Log.e(TAG, "震动提醒失败", e);
        }
    }
    
    /**
     * 播放自定义铃声文件
     */
    public static void playCustomSound(Context context, String soundFileName) {
        try {
            // 停止之前的播放
            stopPlayback();
            
            // 从assets文件夹加载音频文件
            android.content.res.AssetFileDescriptor afd = context.getAssets().openFd(soundFileName);
            
            // 创建MediaPlayer
            mediaPlayer = new MediaPlayer();
            
            // 设置音频属性
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build();
                mediaPlayer.setAudioAttributes(audioAttributes);
            } else {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            }
            
            // 设置数据源
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            
            // 设置循环播放
            mediaPlayer.setLooping(true);
            
            // 准备播放
            mediaPlayer.prepare();
            
            // 开始播放
            mediaPlayer.start();
            
            Log.d(TAG, "开始播放自定义铃声: " + soundFileName);
            
            // 5秒后自动停止
            new android.os.Handler().postDelayed(() -> {
                stopPlayback();
            }, 5000);
            
        } catch (Exception e) {
            Log.e(TAG, "播放自定义铃声失败", e);
        }
    }
} 