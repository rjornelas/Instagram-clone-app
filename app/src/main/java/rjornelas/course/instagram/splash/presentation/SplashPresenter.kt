package rjornelas.course.instagram.splash.presentation

import rjornelas.course.instagram.splash.Splash
import rjornelas.course.instagram.splash.data.SplashCallback
import rjornelas.course.instagram.splash.data.SplashRepository

class SplashPresenter(
    private var view: Splash.View?,
    private val repository: SplashRepository
) : Splash.Presenter {
    override fun authenticated() {
        repository.session(object : SplashCallback{
            override fun onSuccess() {
                view?.goToMainScreen()
            }

            override fun onFailure() {
                view?.goToLoginScreen()
            }

        })
    }

    override fun onDestroy() {
        view = null
    }
}