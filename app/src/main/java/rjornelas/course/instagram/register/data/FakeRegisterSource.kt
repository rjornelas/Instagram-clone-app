package rjornelas.course.instagram.register.data

import android.os.Handler
import android.os.Looper
import rjornelas.course.instagram.common.model.Database
import rjornelas.course.instagram.common.model.UserAuth
import java.util.UUID

class FakeRegisterSource : RegisterSource {
    override fun create(email: String, callback: RegisterCallBack) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull() {
                email == it.email
            }

            if (userAuth == null) {
                callback.onSuccess()
            } else {
                callback.onFailure("Usuário já cadastrado")
            }
            callback.onComplete()
        }, 2000)
    }

    override fun create(
        name: String,
        email: String,
        password: String,
        confirmedPassword: String,
        callback: RegisterCallBack
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull() { email == it.email }

            if (userAuth != null) {
                callback.onFailure("User already registered")
            }else{
                val newUser = UserAuth(UUID.randomUUID().toString(), name, email, password)

                val created = Database.usersAuth.add(newUser)

                if(created){
                    Database.sessionAuth = newUser
                    callback.onSuccess()
                }else{
                    callback.onFailure("Internal server error")
                }
            }
            callback.onComplete()
        }, 2000)
    }

}