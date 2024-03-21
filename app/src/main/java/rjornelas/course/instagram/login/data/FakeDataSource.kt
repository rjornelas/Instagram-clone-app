package rjornelas.course.instagram.login.data

import android.os.Handler
import android.os.Looper
import rjornelas.course.instagram.common.model.Database

class FakeDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallBack) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull {
                email == it.email
            }

            if(userAuth == null){
                callback.onFailure("Usuario não encontrado")
            }else if(userAuth.password != password){
                callback.onFailure("Incorrect password")
            }else{
                callback.onSuccess()
            }
            callback.onComplete()
        }, 2000)
    }
}