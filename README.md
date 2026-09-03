# 📚 Bookly - Mobile Book Library & E-Reader App

> *"Open A Book, Discover A New World"*

**Bookly** là ứng dụng di động đọc sách điện tử (E-Reader) và quản lý tủ sách cá nhân chuẩn **Clean Architecture** trên Android, phát triển bằng **Jetpack Compose (Material 3)** kết hợp với **Firebase Backend** và dịch vụ xác thực **Email OTP (JavaMail API)**. Ứng dụng sở hữu thiết kế tối giản hiện đại với gam màu **Emerald Green** chủ đạo (`#4EBA87`).

---

## ✨ Features (Tính năng nổi bật)

- 🔐 **Xác thực Email OTP (Lazy User Creation)**: Tự động gửi Email HTML chứa mã OTP 6 số qua cổng Gmail SMTP (`JavaMail API`) để xác minh trước khi kích hoạt tài khoản trên **Firebase Auth**.
- 📖 **E-Reader & Đọc sách Điện tử**: Màn hình đọc truyện/sách chuyên nghiệp (`ReaderScreen`), điều hướng chương mượt mà, theo dõi tiến trình đọc và tìm kiếm sách thời gian thực.
- 📚 **Quản lý Tủ sách & Real-time Sync**: Đồng bộ danh mục sách và hồ sơ người dùng trên **Cloud Firestore**, tích hợp công cụ tự động seed dữ liệu mẫu (`FirestoreSeeder`).
- 🎨 **Minimalist Emerald Design System**: Giao diện thiết kế tối giản, họa tiết nghệ thuật vẽ bằng `Canvas` Compose, nút bấm viên thuốc và UI chuẩn Material 3.

---

## 🏗️ Architecture & Tech Stack

Dự án tuân thủ nghiêm ngặt **Clean Architecture** (MVVM) chia làm 3 lớp chính: `data`, `domain`, `ui`.

- **Language**: Kotlin 100%
- **UI Framework**: Jetpack Compose (Material 3)
- **Architecture**: MVVM + Clean Architecture
- **Async & Reactive**: Kotlin Coroutines, StateFlow
- **Backend & Cloud**: Firebase Authentication, Cloud Firestore
- **Services**: JavaMail API (`com.sun.mail`) cho gửi Email OTP
- **Image Loading**: Coil 3 (`coil-compose`)
- **Security**: Quản lý credentials an toàn qua `local.properties` & `BuildConfig`

---

## 🚀 Getting Started

1. **Clone repository**:
   ```bash
   git clone https://github.com/hongmanhunity/Bookly.git
   cd Bookly
   ```
2. **Cấu hình Firebase & Credentials**:
   - Thêm `google-services.json` vào thư mục `app/`.
   - Cấu hình `SENDER_EMAIL` và `SENDER_APP_PASSWORD` trong `local.properties`.
3. **Build & Run**: Mở dự án trong Android Studio và nhấn **Run (`Shift + F10`)**.

---

## 📄 License
Project này được phát hành dưới giấy phép [MIT License](LICENSE).
