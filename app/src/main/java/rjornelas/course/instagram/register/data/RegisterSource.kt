package rjornelas.course.instagram.register.data

import android.net.Uri

interface RegisterSource {

    fun create(email: String, callback: RegisterCallBack)
    fun create(name: String, email: String, password: String, confirmedPassword: String, callback: RegisterCallBack)
    fun updateUser(photoUri: Uri, callback: RegisterCallBack)
}