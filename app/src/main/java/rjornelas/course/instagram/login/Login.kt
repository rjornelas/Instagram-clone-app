package rjornelas.course.instagram.login

import rjornelas.course.instagram.common.base.BaseView
import rjornelas.course.instagram.common.base.BasePresenter

interface Login {

    interface Presenter : BasePresenter {
        fun login(email: String, password: String)
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPassFailure(passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(message: String)
    }
}