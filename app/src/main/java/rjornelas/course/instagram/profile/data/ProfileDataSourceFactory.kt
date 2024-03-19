package rjornelas.course.instagram.profile.data

import rjornelas.course.instagram.common.base.Cache
import rjornelas.course.instagram.common.model.Post
import rjornelas.course.instagram.common.model.UserAuth

class ProfileDataSourceFactory(
    private val profileCache: Cache<UserAuth>,
    private val postsCache: Cache<List<Post>>
) {

    fun createLocalDataSource(): ProfileDataSource{
        return ProfileLocalDataSource(profileCache, postsCache)
    }

    fun createFromUser(): ProfileDataSource{
        if(profileCache.isCached()){
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return ProfileFakeRemoteDataSource()
    }

    fun createFromPosts(): ProfileDataSource{
        if (postsCache.isCached()){
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return ProfileFakeRemoteDataSource()
    }
}