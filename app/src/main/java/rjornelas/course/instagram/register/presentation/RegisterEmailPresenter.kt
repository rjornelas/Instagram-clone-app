package rjornelas.course.instagram.register.presentation

import android.util.Patterns
import rjornelas.course.instagram.R
import rjornelas.course.instagram.common.model.UserAuth
import rjornelas.course.instagram.login.Login
import rjornelas.course.instagram.login.data.LoginCallBack
import rjornelas.course.instagram.login.data.LoginRepository
import rjornelas.course.instagram.register.RegisterEmail
import rjornelas.course.instagram.register.data.RegisterEmailCallBack
import rjornelas.course.instagram.register.data.RegisterEmailRepository

class RegisterEmailPresenter(
    private var view: RegisterEmail.View?,
    private val repository: RegisterEmailRepository
) : RegisterEmail.Presenter {

    override fun create(email: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (!isEmailValid) {
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }

        if (isEmailValid) {
            view?.showProgress(true)

            repository.create(email, object : RegisterEmailCallBack {
                override fun onSuccess() {
                    view?.goToNameAndPasswordScreen(email)
                }

                override fun onFailure(message: String) {
                    view?.onEmailFailure(message)
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