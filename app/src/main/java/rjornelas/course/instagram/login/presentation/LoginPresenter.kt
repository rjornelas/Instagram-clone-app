package rjornelas.course.instagram.login.presentation

import android.util.Patterns
import rjornelas.course.instagram.R
import rjornelas.course.instagram.login.Login

class LoginPresenter(
    private var view: Login.View?
) : Login.Presenter {
    override fun login(email: String, password: String) {

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }

        if (password.length < 8) {
            view?.displayPassFailure(R.string.invalid_password)
        } else {
            view?.displayPassFailure(null)
        }
    }

    override fun onDestroy() {
        view = null
    }
}