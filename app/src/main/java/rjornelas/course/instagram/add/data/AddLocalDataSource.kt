package rjornelas.course.instagram.add.data

import rjornelas.course.instagram.common.model.Database
import rjornelas.course.instagram.common.model.UserAuth
import java.lang.RuntimeException

class AddLocalDataSource : AddDataSource {

    override fun fetchSession(): UserAuth {
        return Database.sessionAuth ?: throw RuntimeException("Usuário não logado!")
    }
}