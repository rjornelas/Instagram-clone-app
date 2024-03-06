package rjornelas.course.instagram.splash

import rjornelas.course.instagram.common.base.BasePresenter
import rjornelas.course.instagram.common.base.BaseView

class Splash {
    interface Presenter : BasePresenter{
        fun authenticated()
    }

    interface View : BaseView<Presenter>{
        fun goToMainScreen()
        fun goToLoginScreen()
    }
}