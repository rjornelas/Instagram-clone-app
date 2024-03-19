package rjornelas.course.instagram.post

import android.net.Uri
import rjornelas.course.instagram.common.base.BasePresenter
import rjornelas.course.instagram.common.base.BaseView

interface Post {

    interface Presenter : BasePresenter{
        fun fetchPictures()
    }

    interface View : BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayEmptyPictures()
        fun displayFullPictures(posts: List<Uri>)
        fun displayRequestFailure(message: String)
    }
}