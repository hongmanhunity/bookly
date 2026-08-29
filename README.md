# 📚 Bookly - Mobile Book Library App

> *"Open A Book, Discover A New World"*

**Bookly** là ứng dụng di động đọc và quản lý tủ sách cá nhân chuẩn **Clean Architecture** trên nền tảng **Android**, xây dựng hoàn toàn bằng **Jetpack Compose** kết hợp với **Firebase Backend** (Authentication & Firestore). Ứng dụng mang phong cách thiết kế tối giản hiện đại với gam màu Xanh Emerald chủ đạo (`#4EBA87`).

---

## ✨ Features (Tính năng nổi bật)

- 🔐 **Authentication & User Profiles**:
  - Đăng ký & Đăng nhập tài khoản an toàn qua Firebase Authentication.
  - Tự động đồng bộ hồ sơ người dùng (User Profile) lưu trữ trên Cloud Firestore.
  - Tự động duy trì phiên đăng nhập và hiển thị trạng thái bằng `StateFlow`.
  - Giao diện nhập liệu mượt mà, ẩn/hiện mật khẩu bằng Material Icons (`Visibility` / `VisibilityOff`).

- 📖 **Book Library Management**:
  - Hiển thị danh sách sách theo lưới (Grid View) với ảnh bìa chất lượng cao.
  - Xem chi tiết thông tin sách (Tác giả, Mô tả, Đánh giá rating star, Thể loại).
  - Tích hợp công cụ Seed dữ liệu tự động (`FirestoreSeeder`) lên Firebase Firestore.

- 🎨 **Minimalist Design System**:
  - Giao diện Emerald Green & White tối giản, thanh lịch.
  - Thành phần UI dạng viên thuốc (Pill Button & Rounded Input Fields).
  - Họa tiết hình học nghệ thuật vẽ bằng `Canvas` Compose tạo điểm nhấn sinh động.

---

## 🏗️ Architecture & Tech Stack (Kiến trúc & Công nghệ)

Dự án tuân thủ nghiêm ngặt **Clean Architecture** chia làm 3 lớp chính:

```
app/src/main/java/com/example/bookly/
├── data/           # Repositories Implementation, Firebase / Firestore Data Sources
├── domain/         # Data Models (User, Book), Repository Interfaces, Use Cases
└── ui/             # Composables (Login, Register, BookList, BookDetail), ViewModels & Theme
```

### Tech Stack:
- **Language**: Kotlin 100%
- **UI Framework**: Jetpack Compose (Material 3)
- **Architecture**: MVVM + Clean Architecture
- **Async & Reactive**: Kotlin Coroutines, StateFlow, `callbackFlow`
- **Backend & Cloud**: 
  - Firebase Authentication (Email / Password)
  - Cloud Firestore (Real-time DB)
- **Image Loading**: Coil 3 (`coil-compose`)
- **Icons**: Material Icons Extended

---

## 🚀 Getting Started (Hướng dẫn cài đặt)

### Prerequisites:
- **Android Studio**: Ladybug (2024.2.1) trở lên.
- **JDK**: Java 11 trở lên.
- **Android Device / Emulator**: API 24 (Android 7.0) trở lên.

### Installation:

1. **Clone repository**:
   ```bash
   git clone https://github.com/your-username/Bookly.git
   cd Bookly
   ```

2. **Cấu hình Firebase**:
   - Thêm dự án mới trên [Firebase Console](https://console.firebase.google.com/).
   - Tải tệp `google-services.json` và chép vào thư mục `app/`.
   - Bật dịch vụ **Email/Password Authentication** và **Cloud Firestore Database**.

3. **Build & Run**:
   - Mở dự án trong Android Studio.
   - Nhấn **Sync Project with Gradle Files**.
   - Chọn Thiết bị / Máy ảo và bấm **Run (`Shift + F10`)**.

---

## 📄 License

Project này được phát hành dưới giấy phép [MIT License](LICENSE).
