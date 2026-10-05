package com.example.bookly.data.service

import android.util.Log
import com.example.bookly.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object EmailService {

    private const val TAG = "BooklyEmailService"

    private val SENDER_EMAIL: String get() = BuildConfig.SENDER_EMAIL
    private val SENDER_APP_PASSWORD: String get() = BuildConfig.SENDER_APP_PASSWORD

    fun buildBooklyOtpEmailHtml(otpCode: String, recipientEmail: String): String {
        return """
            <!DOCTYPE html>
            <html lang="vi">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Mã OTP Xác Thực Bookly</title>
                <style>
                    body {
                        font-family: 'Helvetica Neue', Arial, sans-serif;
                        background-color: #F1F5F9;
                        margin: 0;
                        padding: 20px;
                        color: #334155;
                    }
                    .container {
                        max-width: 520px;
                        margin: 0 auto;
                        background-color: #FFFFFF;
                        border-radius: 16px;
                        overflow: hidden;
                        box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
                    }
                    .header {
                        background-color: #4EBA87;
                        padding: 32px 24px;
                        text-align: center;
                    }
                    .header h1 {
                        color: #FFFFFF;
                        margin: 0;
                        font-size: 32px;
                        font-weight: 700;
                        letter-spacing: -1px;
                    }
                    .header p {
                        color: #E2F4EB;
                        margin: 6px 0 0 0;
                        font-size: 14px;
                    }
                    .content {
                        padding: 32px 28px;
                        text-align: center;
                    }
                    .content h2 {
                        font-size: 20px;
                        color: #0F172A;
                        margin-top: 0;
                    }
                    .content p {
                        font-size: 15px;
                        line-height: 1.6;
                        color: #64748B;
                        margin-bottom: 24px;
                    }
                    .otp-box {
                        background-color: #F8FAFC;
                        border: 1px solid #CBD5E1;
                        border-radius: 12px;
                        padding: 16px 24px;
                        font-size: 32px;
                        font-weight: bold;
                        letter-spacing: 8px;
                        color: #0F172A;
                        display: inline-block;
                        margin: 8px 0 24px 0;
                    }
                    .warning {
                        font-size: 13px;
                        color: #64748B;
                        line-height: 1.5;
                        margin-top: 20px;
                        text-align: center;
                    }
                    .footer {
                        background-color: #F8FAFC;
                        padding: 20px;
                        text-align: center;
                        font-size: 12px;
                        color: #94A3B8;
                        border-top: 1px solid #E2E8F0;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>bookly</h1>
                        <p>Ứng dụng đọc truyện Light Novel hàng đầu</p>
                    </div>
                    <div class="content">
                        <h2>Xác Thực Tài Khoản Của Bạn</h2>
                        <p>Chào bạn, cảm ơn bạn đã đăng ký tài khoản tại <strong>Bookly</strong>. Đây là mã OTP 6 số để kích hoạt tài khoản của bạn:</p>

                        <div class="otp-box">$otpCode</div>

                        <p style="margin-bottom: 12px;">Mã OTP này có hiệu lực trong vòng <strong>5 phút</strong>.</p>

                        <div class="warning">
                            Vui lòng không chia sẻ mã này với bất kỳ ai để đảm bảo an toàn cho tài khoản.
                        </div>
                    </div>
                    <div class="footer">
                        &copy; 2026 Bookly Team. Tất cả các quyền được bảo lưu.
                    </div>
                </div>
            </body>
            </html>
        """.trimIndent()
    }

    suspend fun sendOtpEmail(recipientEmail: String, otpCode: String): Boolean = withContext(Dispatchers.IO) {
        try {
            if (SENDER_EMAIL.isBlank() || SENDER_APP_PASSWORD.isBlank()) {
                Log.w(TAG, "⚠️ SENDER_EMAIL hoặc SENDER_APP_PASSWORD trong local.properties đang bị trống!")
                return@withContext false
            }

            val props = Properties().apply {
                put("mail.smtp.host", "smtp.gmail.com")
                put("mail.smtp.socketFactory.port", "465")
                put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
                put("mail.smtp.auth", "true")
                put("mail.smtp.port", "465")
            }

            val session = Session.getInstance(props, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(SENDER_EMAIL, SENDER_APP_PASSWORD.replace(" ", ""))
                }
            })

            val htmlContent = buildBooklyOtpEmailHtml(otpCode, recipientEmail)

            val mimeMessage = MimeMessage(session).apply {
                setFrom(InternetAddress(SENDER_EMAIL, "Bookly App"))
                addRecipient(Message.RecipientType.TO, InternetAddress(recipientEmail))
                subject = "[Bookly] Mã OTP xác thực tài khoản của bạn: $otpCode"
                setContent(htmlContent, "text/html; charset=utf-8")
            }

            Transport.send(mimeMessage)
            Log.d(TAG, "✅ Đã gửi Email OTP THỰC TẾ thành công tới: $recipientEmail")
            true
        } catch (e: Exception) {
            Log.e(TAG, "❌ Lỗi phát gửi Email SMTP: ${e.message}", e)
            false
        }
    }
}
