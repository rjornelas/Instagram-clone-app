package rjornelas.course.instagram.register.data

import android.net.Uri
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
            } else {
                val newUser = UserAuth(UUID.randomUUID().toString(), name, email, password, null)

                val created = Database.usersAuth.add(newUser)

                if (created) {
                    Database.sessionAuth = newUser
                    Database.followers[newUser.uuid] = hashSetOf()
                    Database.feeds[newUser.uuid] = hashSetOf()
                    callback.onSuccess()
                } else {
                    callback.onFailure("Internal server error")
                }
            }
            callback.onComplete()
        }, 2000)
    }

    override fun updateUser(photoUri: Uri, callback: RegisterCallBack) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.sessionAuth

            if (userAuth == null) {
                callback.onFailure("User not found")
            } else {
                val index = Database.usersAuth.indexOf(Database.sessionAuth)
                Database.usersAuth[index] = Database.sessionAuth!!.copy(photoUri = photoUri)
                Database.sessionAuth = Database.usersAuth[index]
                callback.onSuccess()
            }
            callback.onComplete()
        }, 2000)
    }

}