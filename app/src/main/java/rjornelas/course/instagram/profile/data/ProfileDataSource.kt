package rjornelas.course.instagram.profile.data

import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.Post
import rjornelas.course.instagram.common.model.UserAuth
import java.lang.UnsupportedOperationException

interface ProfileDataSource {

    fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>)
    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>)
    fun fetchSession(): UserAuth {throw UnsupportedOperationException()}
    fun putUser(response: UserAuth){throw UnsupportedOperationException()}
    fun putPosts(response: List<Post>?){throw UnsupportedOperationException()}
}