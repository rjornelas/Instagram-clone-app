package rjornelas.course.instagram.login.data

interface LoginDataSource {

    fun login(email: String, password: String, callback: LoginCallBack)
}