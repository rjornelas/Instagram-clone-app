package rjornelas.course.instagram.register

import androidx.annotation.StringRes
import rjornelas.course.instagram.common.base.BasePresenter
import rjornelas.course.instagram.common.base.BaseView

interface RegisterEmail {

    interface Presenter : BasePresenter{}
    interface View : BaseView<Presenter>{
        fun displayEmailFailure(@StringRes emailError: Int?)
    }
}