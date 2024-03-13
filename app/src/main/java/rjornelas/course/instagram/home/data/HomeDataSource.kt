package rjornelas.course.instagram.home.data

import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.Post
import rjornelas.course.instagram.common.model.UserAuth

interface HomeDataSource {
    fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>)
    fun fetchSession(): UserAuth {
        throw UnsupportedOperationException()
    }

    fun putFeed(response: List<Post>) {
        throw UnsupportedOperationException()
    }
}