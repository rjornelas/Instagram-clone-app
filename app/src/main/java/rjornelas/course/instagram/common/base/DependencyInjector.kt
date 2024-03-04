package rjornelas.course.instagram.common.base

import rjornelas.course.instagram.login.data.FakeDataSource
import rjornelas.course.instagram.login.data.LoginRepository
import rjornelas.course.instagram.register.data.FakeRegisterEmailDataSource
import rjornelas.course.instagram.register.data.RegisterEmailRepository

object DependencyInjector {
    fun loginRepository() : LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository() : RegisterEmailRepository{
        return RegisterEmailRepository(FakeRegisterEmailDataSource())
    }
}