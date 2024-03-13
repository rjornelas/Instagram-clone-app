package rjornelas.course.instagram.home.data

import android.os.Handler
import android.os.Looper
import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.Database
import rjornelas.course.instagram.common.model.Post

class HomeFakeRemoteDataSource : HomeDataSource {
    override fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val feed = Database.feeds[userUUID]

            callback.onSuccess(feed?.toList() ?: emptyList())
            callback.onComplete()
        }, 2000)
    }
}