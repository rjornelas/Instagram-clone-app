package rjornelas.course.instagram.add

import android.net.Uri
import rjornelas.course.instagram.common.base.BasePresenter
import rjornelas.course.instagram.common.base.BaseView

interface Add {

    interface Presenter : BasePresenter{
        fun createPost(uri: Uri, caption: String)
    }

    interface View : BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayRequestSuccess()
        fun displayRequestFailure(message: String)
    }
}