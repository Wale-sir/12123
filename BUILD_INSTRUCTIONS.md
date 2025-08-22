# 交警短信监控器 - 构建说明

## 方法1: 使用Android Studio (推荐)

### 步骤1: 安装Android Studio
1. 下载并安装 [Android Studio](https://developer.android.com/studio)
2. 安装Android SDK (API 21+)
3. 安装必要的构建工具

### 步骤2: 打开项目
1. 启动Android Studio
2. 选择 "Open an existing Android Studio project"
3. 选择项目目录 `D:\12123`
4. 等待Gradle同步完成

### 步骤3: 构建APK
1. 点击菜单 `Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`
2. 等待构建完成
3. 点击 "locate" 找到生成的APK文件

**APK文件位置**: `app/build/outputs/apk/debug/app-debug.apk`

## 方法2: 使用命令行 (需要Java JDK)

### 步骤1: 安装Java JDK
1. 下载并安装 [OpenJDK 11](https://adoptium.net/) 或 [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
2. 设置环境变量 `JAVA_HOME` 和 `PATH`

### 步骤2: 构建APK
```bash
# 在项目根目录执行
.\gradlew.bat assembleDebug
```

### 步骤3: 找到APK
构建成功后，APK文件位于：
`app/build/outputs/apk/debug/app-debug.apk`

## 方法3: 使用预构建的APK

如果您无法构建，我可以为您提供一个预构建的APK文件。

## 系统要求

- **操作系统**: Windows 10/11, macOS, Linux
- **Java**: JDK 8+ (用于Gradle构建)
- **Android SDK**: API 21+ (Android 5.0+)
- **内存**: 至少4GB RAM
- **磁盘空间**: 至少2GB可用空间

## 常见问题

### 问题1: Gradle同步失败
**解决方案**: 
- 检查网络连接
- 更新Gradle版本
- 清理项目: `.\gradlew.bat clean`

### 问题2: 构建工具版本不匹配
**解决方案**:
- 更新Android SDK Build Tools
- 修改 `app/build.gradle` 中的 `compileSdk` 版本

### 问题3: 权限问题
**解决方案**:
- 确保有足够的磁盘权限
- 以管理员身份运行命令行

### 问题4: Java版本不兼容
**解决方案**:
- 使用Java 8-11版本
- 设置正确的 `JAVA_HOME` 环境变量

## 验证APK

构建成功后，您可以：

1. **安装到设备**: 将APK传输到Android设备并安装
2. **测试功能**: 启动应用并测试短信监控功能
3. **检查权限**: 确保应用获得了必要的短信权限

## 联系支持

如果遇到构建问题，请：
1. 检查错误日志
2. 确认系统环境配置
3. 参考Android官方文档
4. 联系开发者获取支持

---

**注意**: 首次构建可能需要较长时间，因为需要下载依赖包。请耐心等待。 