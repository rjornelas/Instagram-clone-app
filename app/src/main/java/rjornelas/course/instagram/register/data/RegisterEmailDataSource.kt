package rjornelas.course.instagram.register.data

interface RegisterEmailDataSource {

    fun create(email: String, callback: RegisterEmailCallBack)
}