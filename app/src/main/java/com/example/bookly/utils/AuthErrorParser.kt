package com.example.bookly.utils

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

object AuthErrorParser {

    fun parse(throwable: Throwable?): String? {
        if (throwable == null) return "Đã xảy ra lỗi không xác định. Vui lòng thử lại sau."

        val message = throwable.message?.lowercase() ?: ""

        if (message.contains("canceled") ||
            message.contains("cancelled") ||
            message.contains("closed") ||
            message.contains("12501") ||
            message.contains("web-context-cancelled") ||
            message.contains("user_cancelled")
        ) {
            return null
        }

        if (throwable is FirebaseAuthUserCollisionException ||
            message.contains("already exists") ||
            message.contains("already in use") ||
            message.contains("account-exists-with-different-credential") ||
            message.contains("collision")
        ) {
            return "Email này đã được sử dụng cho một tài khoản khác. Vui lòng đăng nhập bằng Email/Mật khẩu hoặc phương thức đã liên kết trước đó (Google/Facebook/GitHub)."
        }

        if (throwable is FirebaseAuthInvalidCredentialsException ||
            message.contains("invalid-credential") ||
            message.contains("wrong-password") ||
            message.contains("invalid password") ||
            message.contains("malformed")
        ) {
            return "Tài khoản hoặc mật khẩu không chính xác. Vui lòng kiểm tra lại!"
        }

        if (throwable is FirebaseAuthInvalidUserException ||
            message.contains("user-not-found") ||
            message.contains("user-disabled")
        ) {
            return "Tài khoản không tồn tại hoặc đã bị khóa. Vui lòng kiểm tra lại email hoặc đăng ký tài khoản mới!"
        }

        if (throwable is FirebaseAuthWeakPasswordException || message.contains("weak-password")) {
            return "Mật khẩu quá yếu. Vui lòng đặt mật khẩu có ít nhất 6 ký tự."
        }

        if (throwable is FirebaseNetworkException ||
            message.contains("network") ||
            message.contains("timeout") ||
            message.contains("connection") ||
            message.contains("unreachable")
        ) {
            return "Lỗi kết nối mạng. Vui lòng kiểm tra lại kết nối Internet và thử lại."
        }

        if (message.contains("too-many-requests")) {
            return "Bạn đã thử quá nhiều lần trong thời gian ngắn. Vui lòng đợi vài phút rồi thử lại."
        }

        if (message.contains("operation-not-allowed") || message.contains("configuration-not-found")) {
            return "Phương thức đăng nhập này chưa được kích hoạt trên hệ thống. Vui lòng liên hệ quản trị viên."
        }

        if (throwable is FirebaseAuthRecentLoginRequiredException) {
            return "Phiên làm việc đã hết hạn. Vui lòng đăng nhập lại để tiếp tục."
        }

        val rawMsg = throwable.message
        if (!rawMsg.isNullOrBlank() &&
            !rawMsg.contains("Exception") &&
            !rawMsg.contains("com.google") &&
            !rawMsg.contains("firebase") &&
            !rawMsg.contains("Error")
        ) {
            return rawMsg
        }

        return "Đăng nhập không thành công. Vui lòng thử lại sau."
    }
}
