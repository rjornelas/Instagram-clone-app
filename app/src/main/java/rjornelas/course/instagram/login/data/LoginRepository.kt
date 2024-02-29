package rjornelas.course.instagram.login.data

class LoginRepository(
    private val dataSource: LoginDataSource
) {
    fun login(email: String, password: String, callback: LoginCallBack) {
        dataSource.login(email, password, callback)
    }
}