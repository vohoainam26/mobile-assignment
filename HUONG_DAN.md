# 📚 Hướng dẫn sử dụng dự án

## 🎯 Mục đích dự án

Dự án này minh họa cách sử dụng **Fragments và Navigation Component** trong Android hiện đại, bao gồm:

- ✅ Fragment lifecycle management
- ✅ Navigation Component với Safe Args
- ✅ ViewModel cho state management
- ✅ Truyền dữ liệu an toàn giữa các Fragment

---

## 🚀 Cài đặt nhanh

### Bước 1: Mở dự án
```
Android Studio → File → Open → Chọn MyApplication22
```

### Bước 2: Sync & Build
```
1. Chờ Gradle sync tự động
2. Build → Make Project (Ctrl+F9)
   Hoặc: ./gradlew build
```

### Bước 3: Chạy app
```
1. Kết nối thiết bị/Emulator
2. Click Run (▶️) hoặc Shift+F10
```

---

## 📁 Cấu trúc dự án

```
MyApplication22/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/myapplication22/
│   │   │   ├── MainActivity.java          # Activity chính
│   │   │   ├── WelcomeFragment.java       # Fragment chào mừng
│   │   │   ├── MessageFragment.java       # Fragment thông điệp
│   │   │   └── AppViewModel.java          # ViewModel quản lý state
│   │   ├── res/
│   │   │   ├── layout/                    # Layout files
│   │   │   └── navigation/
│   │   │       └── nav_graph.xml          # Navigation graph
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts                   # Dependencies
└── gradle/
    └── libs.versions.toml                  # Version catalog
```

---

## 🔑 Tính năng chính

### 1. Navigation Component
- **NavGraph**: Định nghĩa destinations và actions trong `nav_graph.xml`
- **NavHostFragment**: Container chứa các Fragment trong `activity_main.xml`
- **NavController**: Điều khiển navigation flow trong `MainActivity.java`

### 2. Safe Args
- Truyền dữ liệu type-safe giữa Fragment
- Arguments được định nghĩa trong `nav_graph.xml`
- Generated classes sau khi build: `WelcomeFragmentDirections`, `MessageFragmentArgs`

### 3. Fragment Lifecycle
- Đầy đủ lifecycle callbacks: onCreate, onStart, onResume, onPause, onStop, onDestroy
- Logging để theo dõi lifecycle
- Quản lý resources đúng cách (tránh memory leak)

### 4. ViewModel
- `AppViewModel` quản lý state với LiveData
- Dữ liệu được giữ lại khi Fragment recreate

---

## 💡 Sử dụng Safe Args (sau khi build)

```java
// Trong WelcomeFragment - Truyền dữ liệu
WelcomeFragmentDirections.ActionWelcomeFragmentToMessageFragment action = 
    WelcomeFragmentDirections.actionWelcomeFragmentToMessageFragment(
        "Tên người dùng", 
        "Thông điệp"
    );
Navigation.findNavController(v).navigate(action);

// Trong MessageFragment - Nhận dữ liệu
MessageFragmentArgs args = MessageFragmentArgs.fromBundle(getArguments());
String userName = args.getUserName();
String message = args.getMessage();
```

---

## ⚠️ Lưu ý quan trọng

1. **Phải build project** để generate Safe Args classes
2. **Min SDK 36** - Đảm bảo thiết bị/Emulator hỗ trợ
3. **Java 11+** - Kiểm tra JDK version
4. **Internet connection** - Cần để tải dependencies lần đầu

---

## 🐛 Troubleshooting

| Lỗi | Giải pháp |
|-----|-----------|
| Safe Args không generate | `./gradlew clean build` |
| Gradle sync failed | Invalidate Caches / Restart |
| SDK not found | Cài đặt SDK Platform 36 |
| App không chạy | Kiểm tra USB Debugging, Min SDK |

