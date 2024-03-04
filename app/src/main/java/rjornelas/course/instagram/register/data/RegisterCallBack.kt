package rjornelas.course.instagram.register.data

interface RegisterCallBack {

    fun onSuccess()

    fun onFailure(message: String)

    fun onComplete()
}