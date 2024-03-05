package rjornelas.course.instagram.register.data

import android.net.Uri

class RegisterRepository(
    private val dataSource: RegisterSource
) {
    fun create(email: String, callback: RegisterCallBack) {
        dataSource.create(email, callback)
    }

    fun create(name: String, email: String, password: String, confirmedPassword: String, callback: RegisterCallBack) {
        dataSource.create(name, email, password, confirmedPassword, callback)
    }

    fun updateUser(photoUri: Uri, callback: RegisterCallBack){
        dataSource.updateUser(photoUri, callback)
    }
}