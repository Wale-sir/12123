package com.example.smspolice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.smspolice.service.SMSMonitorService;

public class MainActivity extends AppCompatActivity {
    
    private static final int PERMISSION_REQUEST_CODE = 123;
    private static final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS
    };
    
    private TextView statusText;
    private Button startServiceButton;
    private Button stopServiceButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
        checkPermissions();
        updateServiceStatus();
    }
    
    private void initViews() {
        statusText = findViewById(R.id.status_text);
        startServiceButton = findViewById(R.id.start_service_button);
        stopServiceButton = findViewById(R.id.stop_service_button);
        
        startServiceButton.setOnClickListener(v -> startSMSService());
        stopServiceButton.setOnClickListener(v -> stopSMSService());
    }
    
    private void checkPermissions() {
        boolean allPermissionsGranted = true;
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) 
                    != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false;
                break;
            }
        }
        
        if (!allPermissionsGranted) {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, PERMISSION_REQUEST_CODE);
        }
    }
    
    private void startSMSService() {
        if (checkPermissionsGranted()) {
            Intent serviceIntent = new Intent(this, SMSMonitorService.class);
            startForegroundService(serviceIntent);
            updateServiceStatus();
            Toast.makeText(this, "短信监控服务已启动", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "请先授予必要权限", Toast.LENGTH_SHORT).show();
            checkPermissions();
        }
    }
    
    private void stopSMSService() {
        Intent serviceIntent = new Intent(this, SMSMonitorService.class);
        stopService(serviceIntent);
        updateServiceStatus();
        Toast.makeText(this, "短信监控服务已停止", Toast.LENGTH_SHORT).show();
    }
    
    private void updateServiceStatus() {
        boolean isServiceRunning = SMSMonitorService.isServiceRunning();
        if (isServiceRunning) {
            statusText.setText("状态: 服务运行中");
            startServiceButton.setEnabled(false);
            stopServiceButton.setEnabled(true);
        } else {
            statusText.setText("状态: 服务已停止");
            startServiceButton.setEnabled(true);
            stopServiceButton.setEnabled(false);
        }
    }
    
    private boolean checkPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) 
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            
            if (allGranted) {
                Toast.makeText(this, "所有权限已授予", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "部分权限被拒绝，应用可能无法正常工作", Toast.LENGTH_LONG).show();
            }
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        updateServiceStatus();
    }
} 