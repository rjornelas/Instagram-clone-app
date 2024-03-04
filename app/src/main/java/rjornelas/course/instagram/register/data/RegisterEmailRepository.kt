package rjornelas.course.instagram.register.data

class RegisterEmailRepository(
    private val dataSource: RegisterEmailDataSource
) {
    fun create(email: String, callback: RegisterEmailCallBack) {
        dataSource.create(email, callback)
    }
}