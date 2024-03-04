package rjornelas.course.instagram.register.presentation

import rjornelas.course.instagram.R
import rjornelas.course.instagram.register.RegisterNameAndPassword
import rjornelas.course.instagram.register.data.RegisterCallBack
import rjornelas.course.instagram.register.data.RegisterRepository

class RegisterNameAndPasswordPresenter(
    private var view: RegisterNameAndPassword.View?,
    private val repository: RegisterRepository
) : RegisterNameAndPassword.Presenter {

    override fun create(
        email: String,
        name: String,
        password: String,
        confirmationPassword: String) {

        val isNameValid = name.length > 2
        val isPasswordValid = password.length >= 8
        val isConfirmationPasswordValid = password == confirmationPassword

        if(!isNameValid){
            view?.displayNameFailure(R.string.invalid_name)
        }else{
            view?.displayNameFailure(null)
        }



        if (!isConfirmationPasswordValid) {
            view?.displayPasswordFailure(R.string.password_not_equal)
        } else {
            if (!isPasswordValid) {
                view?.displayPasswordFailure(R.string.invalid_password)
            } else {
                view?.displayPasswordFailure(null)
            }
        }

        if (isNameValid && isPasswordValid &&  isConfirmationPasswordValid) {
            view?.showProgress(true)

            repository.create(name, email, password, confirmationPassword, object : RegisterCallBack {
                override fun onSuccess() {
                    view?.onCreateSuccess(name)
                }

                override fun onFailure(message: String) {
                    view?.onCreateFailure(message)
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