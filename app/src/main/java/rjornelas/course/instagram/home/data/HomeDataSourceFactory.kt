package rjornelas.course.instagram.home.data

import rjornelas.course.instagram.common.base.Cache
import rjornelas.course.instagram.common.model.Post

class HomeDataSourceFactory(
    private val feedCache: Cache<List<Post>>
) {

    fun createLocalDataSource(): HomeDataSource {
        return HomeLocalDataSource(feedCache)
    }

    fun createRemoteDataSource(): HomeDataSource{
        return FireHomeDataSource()
    }

    fun createFromPosts(): HomeDataSource {
        if (feedCache.isCached()) {
            return HomeLocalDataSource(feedCache)
        }
        return FireHomeDataSource()
    }
}