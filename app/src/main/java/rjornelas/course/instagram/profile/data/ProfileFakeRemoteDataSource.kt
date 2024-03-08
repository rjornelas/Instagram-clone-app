package rjornelas.course.instagram.profile.data

import android.os.Handler
import android.os.Looper
import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.Database
import rjornelas.course.instagram.common.model.Post
import rjornelas.course.instagram.common.model.UserAuth

class ProfileFakeRemoteDataSource : ProfileDataSource {
    override fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull() {
                userUUID == it.uuid
            }

            if (userAuth != null) {
                callback.onSuccess(userAuth)
            } else {
                callback.onFailure("Usuário não encontrado")
            }
            callback.onComplete()
        }, 2000)
    }

    override fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val posts = Database.posts[userUUID]

            callback.onSuccess(posts?.toList() ?: emptyList())
            callback.onComplete()
        }, 2000)
    }
}