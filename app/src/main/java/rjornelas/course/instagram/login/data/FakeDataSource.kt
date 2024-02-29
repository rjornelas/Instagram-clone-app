package rjornelas.course.instagram.login.data

import android.os.Handler
import android.os.Looper

class FakeDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallBack) {
        Handler(Looper.getMainLooper()).postDelayed({
            if (email == "abc@gmail.com" && password == "12345678") {
                callback.onSuccess()
            } else {
                callback.onFailure("User not found")
            }
            callback.onComplete()
        }, 2000)
    }
}