package rjornelas.course.instagram.search

import rjornelas.course.instagram.common.base.BasePresenter
import rjornelas.course.instagram.common.base.BaseView
import rjornelas.course.instagram.common.model.User
import rjornelas.course.instagram.common.model.UserAuth

interface Search {
    interface Presenter : BasePresenter {
        fun fetchUsers(name: String)
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayFullUsers(users: List<User>)
        fun displayEmptyUsers()
    }

}