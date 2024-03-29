package rjornelas.course.instagram.login.presentation

import android.util.Patterns
import rjornelas.course.instagram.R
import rjornelas.course.instagram.common.model.UserAuth
import rjornelas.course.instagram.login.Login
import rjornelas.course.instagram.login.data.LoginCallBack
import rjornelas.course.instagram.login.data.LoginRepository

class LoginPresenter(
    private var view: Login.View?,
    private val repository: LoginRepository
) : Login.Presenter {
    override fun login(email: String, password: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (!isEmailValid) {
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }

        if (isEmailValid) {
            view?.showProgress(true)

            repository.login(email, password, object : LoginCallBack {
                override fun onSuccess() {
                    view?.onUserAuthenticated()
                }

                override fun onFailure(message: String) {
                    view?.onUserUnauthorized(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }
            })
        }

    }

    override fun onDestroy() {
        view = null
    }
}