package rjornelas.course.instagram.register.data

import android.os.Handler
import android.os.Looper
import rjornelas.course.instagram.common.model.Database

class FakeRegisterEmailDataSource : RegisterEmailDataSource {
    override fun create(email: String, callback: RegisterEmailCallBack) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull(){
                email == it.email
            }

            if(userAuth == null){
                callback.onSuccess()
            }else{
                callback.onFailure("Usuário já cadastrado")
            }
            callback.onComplete()
        }, 2000)
    }

}