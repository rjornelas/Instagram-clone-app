package rjornelas.course.instagram.login.data

import rjornelas.course.instagram.common.model.UserAuth

interface LoginCallBack {

    fun onSuccess(userAuth: UserAuth)

    fun onFailure(message: String)

    fun onComplete()
}