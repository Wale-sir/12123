@echo off
echo ========================================
echo    交警短信监控器 - 简化构建
echo ========================================
echo.

REM 创建必要的目录结构
if not exist "temp_apk" mkdir temp_apk
if not exist "temp_apk\res" mkdir temp_apk\res
if not exist "temp_apk\res\drawable" mkdir temp_apk\res\drawable
if not exist "temp_apk\res\values" mkdir temp_apk\res\values
if not exist "temp_apk\res\layout" mkdir temp_apk\res\layout

echo 正在创建APK文件结构...

REM 复制资源文件
copy "app\src\main\res\drawable\*.*" "temp_apk\res\drawable\" >nul 2>&1
copy "app\src\main\res\values\*.*" "temp_apk\res\values\" >nul 2>&1
copy "app\src\main\res\layout\*.*" "temp_apk\res\layout\" >nul 2>&1

echo 资源文件复制完成

REM 创建AndroidManifest.xml的简化版本
echo ^<?xml version="1.0" encoding="utf-8"?^> > temp_apk\AndroidManifest.xml
echo ^<manifest xmlns:android="http://schemas.android.com/apk/res/android^> >> temp_apk\AndroidManifest.xml
echo     package="com.example.smspolice"^> >> temp_apk\AndroidManifest.xml
echo     ^<uses-permission android:name="android.permission.RECEIVE_SMS" /^> >> temp_apk\AndroidManifest.xml
echo     ^<uses-permission android:name="android.permission.READ_SMS" /^> >> temp_apk\AndroidManifest.xml
echo     ^<uses-permission android:name="android.permission.VIBRATE" /^> >> temp_apk\AndroidManifest.xml
echo     ^<application android:label="交警短信监控器"^> >> temp_apk\AndroidManifest.xml
echo         ^<activity android:name=".MainActivity" android:exported="true"^> >> temp_apk\AndroidManifest.xml
echo             ^<intent-filter^> >> temp_apk\AndroidManifest.xml
echo                 ^<action android:name="android.intent.action.MAIN" /^> >> temp_apk\AndroidManifest.xml
echo                 ^<category android:name="android.intent.category.LAUNCHER" /^> >> temp_apk\AndroidManifest.xml
echo             ^</intent-filter^> >> temp_apk\AndroidManifest.xml
echo         ^</activity^> >> temp_apk\AndroidManifest.xml
echo         ^<receiver android:name=".receiver.SMSReceiver" android:exported="true"^> >> temp_apk\AndroidManifest.xml
echo             ^<intent-filter^> >> temp_apk\AndroidManifest.xml
echo                 ^<action android:name="android.provider.Telephony.SMS_RECEIVED" /^> >> temp_apk\AndroidManifest.xml
echo             ^</intent-filter^> >> temp_apk\AndroidManifest.xml
echo         ^</receiver^> >> temp_apk\AndroidManifest.xml
echo     ^</application^> >> temp_apk\AndroidManifest.xml
echo ^</manifest^> >> temp_apk\AndroidManifest.xml

echo AndroidManifest.xml 创建完成

REM 创建classes.dex的占位符
echo 这是一个占位符文件，实际APK需要编译后的classes.dex > temp_apk\classes.dex

echo.
echo ========================================
echo 简化APK结构创建完成！
echo ========================================
echo.
echo 注意：这是一个简化的APK结构，不是完整的可安装APK
echo 要获得完整的APK，您需要：
echo.
echo 1. 使用Android Studio打开项目
echo 2. 或者使用在线构建服务
echo 3. 或者安装完整的Android开发环境
echo.
echo 项目文件已准备就绪，位于 D:\12123
echo.
pause 