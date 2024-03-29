package rjornelas.course.instagram.home.data

import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.Post

interface HomeDataSource {
    fun logout() {
        throw UnsupportedOperationException()
    }

    fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>)
    fun fetchSession(): String {
        throw UnsupportedOperationException()
    }

    fun putFeed(response: List<Post>?) {
        throw UnsupportedOperationException()
    }
}