package rjornelas.course.instagram.profile.data

import android.net.Uri
import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.Post
import rjornelas.course.instagram.common.model.UserAuth
import rjornelas.course.instagram.register.data.RegisterCallBack
import rjornelas.course.instagram.register.data.RegisterSource
import java.util.UUID

class ProfileRepository(
    private val dataSource: ProfileDataSource
) {
    fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>){
        dataSource.fetchUserProfile(userUUID, callback)
    }

    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>){
        dataSource.fetchUserPosts(userUUID, callback)
    }
}