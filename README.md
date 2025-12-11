# 📱 Ứng dụng Android - Fragments và Navigation Component

## 📖 Mô tả cơ bản

Ứng dụng Android mẫu minh họa các khái niệm về **Fragments và Navigation Component** trong Android hiện đại.

### Chức năng:
- **WelcomeFragment**: Màn hình chào mừng với nút điều hướng
- **MessageFragment**: Màn hình hiển thị thông điệp
- **Navigation Component**: Điều hướng an toàn giữa các Fragment
- **Safe Args**: Truyền dữ liệu type-safe giữa các Fragment
- **ViewModel**: Quản lý state của ứng dụng

### Công nghệ:
- Java, Android SDK 36
- Navigation Component, ViewModel, LiveData
- ViewBinding, ConstraintLayout

---

## 🛠️ Cách cài đặt

### Yêu cầu:
- Android Studio Hedgehog (2023.1.1) trở lên
- JDK 11+
- Android SDK API 36

### Các bước:

1. **Mở dự án**
   - Mở Android Studio → File → Open → Chọn thư mục `MyApplication22`

2. **Sync Gradle**
   - Chờ Android Studio tự động sync hoặc File → Sync Project with Gradle Files

3. **Build Project** (Quan trọng!)
   ```bash
   ./gradlew build
   ```
   Hoặc: Build → Make Project (Ctrl+F9)
   
   **Lưu ý:** Cần build để generate Safe Args classes

4. **Chạy ứng dụng**
   - Kết nối thiết bị/Emulator
   - Click Run (Shift+F10) hoặc nút ▶️

### Kiểm tra:
- ✅ App hiển thị WelcomeFragment
- ✅ Click "Go to Message" → Điều hướng sang MessageFragment
- ✅ Click "Quay lại" → Quay về WelcomeFragment

---

---

## 💻 Thông tin Source Code

### 📁 Cấu trúc dự án

```
MyApplication22/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/myapplication22/
│   │   │   ├── MainActivity.java              # Activity chính chứa NavHostFragment
│   │   │   ├── WelcomeFragment.java          # Fragment màn hình chào mừng
│   │   │   ├── MessageFragment.java          # Fragment hiển thị thông điệp
│   │   │   └── AppViewModel.java             # ViewModel quản lý state
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml         # Layout chính với NavHostFragment
│   │   │   │   ├── fragment_welcome.xml      # Layout WelcomeFragment
│   │   │   │   └── fragment_message.xml      # Layout MessageFragment
│   │   │   └── navigation/
│   │   │       └── nav_graph.xml             # Navigation Graph định nghĩa flow
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts                      # Dependencies và cấu hình
├── gradle/
│   └── libs.versions.toml                    # Version catalog
└── build.gradle.kts                          # Root build file
```

### 🔑 Các file quan trọng

#### 1. **MainActivity.java**
- Chứa `NavHostFragment` để quản lý navigation
- Thiết lập `NavController` và `AppBarConfiguration`
- Xử lý back navigation

```java
// Thiết lập NavController
NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
        .findFragmentById(R.id.nav_host_fragment);
navController = navHostFragment.getNavController();
```

#### 2. **WelcomeFragment.java**
- Fragment đầu tiên (start destination)
- Có nút điều hướng sang MessageFragment
- Đầy đủ lifecycle callbacks với logging

```java
// Điều hướng với Safe Args
Bundle args = new Bundle();
args.putString("userName", "Người dùng Android");
Navigation.findNavController(v)
        .navigate(R.id.action_welcomeFragment_to_messageFragment, args);
```

#### 3. **MessageFragment.java**
- Nhận dữ liệu từ WelcomeFragment qua arguments
- Hiển thị thông điệp tùy chỉnh
- Có nút quay lại WelcomeFragment

```java
// Nhận arguments từ Safe Args
Bundle args = getArguments();
String userName = args.getString("userName", "Người dùng");
String message = args.getString("message", "Ứng dụng tôi là số 1");
```

#### 4. **AppViewModel.java**
- Quản lý state với `LiveData`
- Dữ liệu được giữ lại khi Fragment recreate
- Sử dụng `MutableLiveData` cho reactive updates

```java
private final MutableLiveData<String> userName = new MutableLiveData<>();
private final MutableLiveData<String> message = new MutableLiveData<>();
```

#### 5. **nav_graph.xml**
- Định nghĩa navigation flow giữa các Fragment
- Cấu hình arguments cho Safe Args
- Quản lý back stack với `popUpTo`

```xml
<fragment android:id="@+id/messageFragment">
    <argument android:name="userName" android:argType="string" />
    <argument android:name="message" android:argType="string" />
    <action app:destination="@id/welcomeFragment" 
            app:popUpTo="@id/welcomeFragment" />
</fragment>
```

### 🎯 Tính năng chính trong code

#### **Fragment Lifecycle Management**
- Đầy đủ lifecycle callbacks: `onCreate()`, `onStart()`, `onResume()`, `onPause()`, `onStop()`, `onDestroy()`
- Logging để debug và theo dõi lifecycle
- Quản lý ViewBinding đúng cách (set null trong `onDestroyView()`)

#### **Navigation Component**
- Sử dụng `NavHostFragment` trong `activity_main.xml`
- `NavController` điều khiển navigation flow
- Safe Args cho type-safe data passing

#### **ViewBinding**
- Tự động generate binding classes
- Truy cập views an toàn, không cần `findViewById()`
- Tự động null check

#### **ViewModel Pattern**
- Tách biệt business logic khỏi UI
- Dữ liệu survive configuration changes
- Reactive updates với LiveData

### 📦 Dependencies chính

```kotlin
// Navigation Component
implementation("androidx.navigation:navigation-fragment:2.7.7")
implementation("androidx.navigation:navigation-ui:2.7.7")

// Fragment
implementation("androidx.fragment:fragment:1.8.5")

// ViewModel & Lifecycle
implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.6")
implementation("androidx.lifecycle:lifecycle-livedata:2.8.6")

// Safe Args Plugin
plugins {
    alias(libs.plugins.androidx.navigation.safe.args)
}
```

### 🔍 Code Flow

1. **App khởi động** → `MainActivity.onCreate()`
2. **Load NavHostFragment** → Tự động load `WelcomeFragment` (start destination)
3. **User click "Go to Message"** → `WelcomeFragment` navigate với arguments
4. **MessageFragment nhận data** → Hiển thị từ arguments
5. **User click "Quay lại"** → Pop back stack về `WelcomeFragment`

---

## 🔧 Xử lý lỗi

**Lỗi: "Cannot resolve symbol WelcomeFragmentDirections"**
→ Build lại project: `./gradlew clean build`

**Lỗi: "Gradle sync failed"**
→ File → Invalidate Caches / Restart

**Lỗi: "SDK not found"**
→ Tools → SDK Manager → Cài đặt Android SDK Platform 36

