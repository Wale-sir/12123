# 交警短信监控器 - 部署指南

## 🚀 快速部署方案

### 方案1: GitHub自动构建 (推荐)

1. **创建GitHub仓库**
   ```bash
   git init
   git add .
   git commit -m "Initial commit: 交警短信监控器"
   git branch -M main
   git remote add origin https://github.com/你的用户名/sms-police-monitor.git
   git push -u origin main
   ```

2. **自动构建APK**
   - 项目已包含 `.github/workflows/build.yml`
   - 推送代码后自动触发构建
   - 在Actions标签页下载APK文件

### 方案2: 在线构建服务

#### GitPod (免费)
1. 访问 [gitpod.io](https://gitpod.io)
2. 导入GitHub仓库
3. 在终端运行: `./gradlew assembleDebug`

#### CodeSandbox
1. 访问 [codesandbox.io](https://codesandbox.io)
2. 导入项目
3. 使用预配置的Android环境

### 方案3: 本地构建

#### 使用Android Studio
1. 下载 [Android Studio](https://developer.android.com/studio)
2. 打开项目 `D:\12123`
3. 等待Gradle同步
4. Build → Build Bundle(s) / APK(s) → Build APK(s)

#### 使用命令行
```bash
# 安装Java JDK 8+
# 设置JAVA_HOME环境变量
.\gradlew.bat assembleDebug
```

## 📱 项目结构

```
D:\12123\
├── app/
│   ├── src/main/
│   │   ├── java/com/example/smspolice/
│   │   │   ├── MainActivity.java          # 主界面
│   │   │   ├── service/
│   │   │   │   └── SMSMonitorService.java # 短信监控服务
│   │   │   ├── receiver/
│   │   │   │   ├── SMSReceiver.java       # 短信接收器
│   │   │   │   └── BootReceiver.java      # 开机启动器
│   │   │   └── util/
│   │   │       ├── PoliceSMSChecker.java  # 交警短信识别
│   │   │       └── SoundPlayer.java       # 声音播放工具
│   │   ├── res/                           # 资源文件
│   │   └── AndroidManifest.xml            # 应用配置
│   └── build.gradle                       # 应用配置
├── build.gradle                           # 项目配置
├── gradle/                                # Gradle包装器
├── .github/workflows/build.yml            # GitHub Actions配置
└── README.md                              # 项目说明
```

## 🔧 构建配置

### Gradle配置
- **compileSdk**: 33 (Android 13)
- **minSdk**: 21 (Android 5.0)
- **targetSdk**: 33 (Android 13)

### 依赖库
- AndroidX AppCompat
- Material Design Components
- ConstraintLayout
- WorkManager

## 📋 构建步骤

### 1. 环境检查
```bash
# 检查Java版本
java -version

# 检查Android SDK
echo $ANDROID_HOME

# 检查Gradle
.\gradlew.bat --version
```

### 2. 构建APK
```bash
# 清理项目
.\gradlew.bat clean

# 构建Debug版本
.\gradlew.bat assembleDebug

# 构建Release版本
.\gradlew.bat assembleRelease
```

### 3. 找到APK文件
```
app/build/outputs/apk/debug/app-debug.apk
app/build/outputs/apk/release/app-release.apk
```

## 🚨 常见问题

### 问题1: Gradle同步失败
**解决方案**:
- 检查网络连接
- 更新Gradle版本
- 清理项目缓存

### 问题2: 构建工具版本不匹配
**解决方案**:
- 更新Android SDK Build Tools
- 修改compileSdk版本

### 问题3: 权限问题
**解决方案**:
- 以管理员身份运行
- 检查磁盘权限

## 📞 技术支持

如果遇到问题：

1. **查看日志**: 检查构建输出和错误信息
2. **环境检查**: 确认Java、Android SDK、Gradle版本
3. **网络问题**: 检查防火墙和代理设置
4. **寻求帮助**: 提交Issue或联系开发者

## 🎯 下一步

1. **选择部署方案**: 推荐使用GitHub Actions
2. **上传代码**: 创建GitHub仓库并推送代码
3. **自动构建**: 等待GitHub Actions完成构建
4. **下载APK**: 在Actions页面下载生成的APK文件
5. **安装测试**: 将APK安装到Android设备进行测试

---

**祝您部署顺利！** 🎉 