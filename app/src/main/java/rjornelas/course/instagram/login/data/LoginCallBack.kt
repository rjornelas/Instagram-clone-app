package rjornelas.course.instagram.login.data

import rjornelas.course.instagram.common.model.UserAuth

interface LoginCallBack {

    fun onSuccess()

    fun onFailure(message: String)

    fun onComplete()
}