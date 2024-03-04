package rjornelas.course.instagram.common.base

import rjornelas.course.instagram.login.data.FakeDataSource
import rjornelas.course.instagram.login.data.LoginRepository
import rjornelas.course.instagram.register.data.FakeRegisterSource
import rjornelas.course.instagram.register.data.RegisterRepository

object DependencyInjector {
    fun loginRepository() : LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository() : RegisterRepository{
        return RegisterRepository(FakeRegisterSource())
    }
}