package rjornelas.course.instagram.search.presentation

import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.User
import rjornelas.course.instagram.common.model.UserAuth
import rjornelas.course.instagram.search.Search
import rjornelas.course.instagram.search.data.SearchRepository

class SearchPresenter(
    private var view: Search.View?,
    private val repository: SearchRepository
) : Search.Presenter {

    override fun fetchUsers(name: String) {
        view?.showProgress(true)
        repository.fetchUsers(name, object : RequestCallback<List<User>> {
            override fun onSuccess(data: List<User>) {
                if(data.isEmpty()){
                    view?.displayEmptyUsers()
                }else{
                    view?.displayFullUsers(data)
                }
            }

            override fun onFailure(message: String) {
                view?.displayEmptyUsers()
            }

            override fun onComplete() {
                view?.showProgress(false)
            }

        })
    }

    override fun onDestroy() {
        view = null
    }
}