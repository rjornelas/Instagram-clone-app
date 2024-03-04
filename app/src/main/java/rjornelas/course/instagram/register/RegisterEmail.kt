package rjornelas.course.instagram.register

import androidx.annotation.StringRes
import rjornelas.course.instagram.common.base.BasePresenter
import rjornelas.course.instagram.common.base.BaseView

interface RegisterEmail {

    interface Presenter : BasePresenter{
        fun create(email: String)
    }
    interface View : BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun onEmailFailure(message: String)
        fun goToNameAndPasswordScreen(email: String)
        fun displayEmailFailure(@StringRes emailError: Int?)
    }
}