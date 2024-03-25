package rjornelas.course.instagram.home.presentation

import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.Post
import rjornelas.course.instagram.home.Home
import rjornelas.course.instagram.home.data.HomeRepository

class HomePresenter(
    private var view: Home.View?,
    private val repository: HomeRepository
) : Home.Presenter {

    override fun fetchFeed() {
        view?.showProgress(true)
        repository.fetchFeed(object : RequestCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if (data.isEmpty()) {
                    view?.displayEmptyPosts()
                } else {
                    view?.displayFullPosts(data)
                }
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }

        })
    }

    override fun clear() {
        repository.clearCache()
    }

    override fun logout() {
        repository.logout()
    }

    override fun onDestroy() {
        view = null
    }
}