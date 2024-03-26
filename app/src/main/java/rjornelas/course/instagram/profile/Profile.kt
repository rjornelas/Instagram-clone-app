package rjornelas.course.instagram.profile

import rjornelas.course.instagram.common.base.BasePresenter
import rjornelas.course.instagram.common.base.BaseView
import rjornelas.course.instagram.common.model.Post
import rjornelas.course.instagram.common.model.User
import rjornelas.course.instagram.common.model.UserAuth

interface Profile {

    interface Presenter : BasePresenter{
        fun fetchUserProfile(uuid: String?)
        fun fetchUserPosts(uuid: String?)
        fun followUser(uuid: String?, follow: Boolean)
        fun clear()
    }

    interface View : BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayUserProfile(user: Pair<User, Boolean?>)
        fun displayRequestFailure(message: String)
        fun displayEmptyPosts()
        fun displayFullPosts(posts: List<Post>)
        fun followUpdated()
    }
}