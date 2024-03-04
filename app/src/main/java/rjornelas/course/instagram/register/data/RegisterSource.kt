package rjornelas.course.instagram.register.data

interface RegisterSource {

    fun create(email: String, callback: RegisterCallBack)
    fun create(name: String, email: String, password: String, confirmedPassword: String, callback: RegisterCallBack)

}