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

## 🔧 Xử lý lỗi

**Lỗi: "Cannot resolve symbol WelcomeFragmentDirections"**
→ Build lại project: `./gradlew clean build`

**Lỗi: "Gradle sync failed"**
→ File → Invalidate Caches / Restart

**Lỗi: "SDK not found"**
→ Tools → SDK Manager → Cài đặt Android SDK Platform 36

