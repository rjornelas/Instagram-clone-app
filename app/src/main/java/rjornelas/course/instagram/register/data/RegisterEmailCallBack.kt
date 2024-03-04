package rjornelas.course.instagram.register.data

import rjornelas.course.instagram.common.model.UserAuth

interface RegisterEmailCallBack {

    fun onSuccess()

    fun onFailure(message: String)

    fun onComplete()
}