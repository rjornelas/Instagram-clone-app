package rjornelas.course.instagram.register

import androidx.annotation.StringRes
import rjornelas.course.instagram.common.base.BasePresenter
import rjornelas.course.instagram.common.base.BaseView

interface RegisterNameAndPassword {

    interface Presenter : BasePresenter{
        fun create(email: String, name: String, password: String, confirmationPassword: String)
    }
    interface View : BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun onCreateSuccess(name: String)
        fun onCreateFailure(message: String)
        fun displayNameFailure(@StringRes nameError: Int?)
        fun displayPasswordFailure(@StringRes passwordError: Int?)
    }
}