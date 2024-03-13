package rjornelas.course.instagram.home

import rjornelas.course.instagram.common.base.BasePresenter
import rjornelas.course.instagram.common.base.BaseView
import rjornelas.course.instagram.common.model.Post

interface Home {

    interface Presenter : BasePresenter{
        fun fetchFeed()
    }

    interface View : BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayRequestFailure(message: String)
        fun displayEmptyPosts()
        fun displayFullPosts(posts: List<Post>)
    }
}