@echo off
echo 正在构建交警短信监控器APK...

REM 检查Android SDK是否安装
if not exist "%ANDROID_HOME%" (
    echo 错误: 未找到Android SDK，请设置ANDROID_HOME环境变量
    echo 或者安装Android Studio
    pause
    exit /b 1
)

echo 使用Android SDK构建工具...
echo Android SDK路径: %ANDROID_HOME%

REM 使用aapt2编译资源
echo 编译资源文件...
if exist "%ANDROID_HOME%\build-tools\33.0.0\aapt2.exe" (
    "%ANDROID_HOME%\build-tools\33.0.0\aapt2.exe" compile --dir app\src\main\res -o compiled_resources.zip
) else (
    echo 错误: 未找到aapt2工具，请确保Android SDK build-tools已安装
    pause
    exit /b 1
)

echo.
echo 构建完成！请使用Android Studio打开项目进行完整构建。
echo.
echo 或者安装Java JDK后运行: .\gradlew.bat assembleDebug
echo.
pause 