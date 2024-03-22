package rjornelas.course.instagram.add.data

import com.google.firebase.auth.FirebaseAuth
import rjornelas.course.instagram.common.model.Database
import rjornelas.course.instagram.common.model.UserAuth
import java.lang.RuntimeException

class AddLocalDataSource : AddDataSource {

    override fun fetchSession(): String {
        return FirebaseAuth.getInstance().uid ?: throw RuntimeException("Usuário não logado!")
    }
}