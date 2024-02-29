package rjornelas.course.instagram.login.data

interface LoginCallBack {

    fun onSuccess()

    fun onFailure(message: String)

    fun onComplete()
}