package rjornelas.course.instagram.common.base

import rjornelas.course.instagram.login.data.FakeDataSource
import rjornelas.course.instagram.login.data.LoginRepository

object DependencyInjector {
    fun loginRepository() : LoginRepository {
        return LoginRepository(FakeDataSource())
    }
}