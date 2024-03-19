package rjornelas.course.instagram.profile.data

import android.net.Uri
import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.Post
import rjornelas.course.instagram.common.model.UserAuth
import rjornelas.course.instagram.register.data.RegisterCallBack
import rjornelas.course.instagram.register.data.RegisterSource
import java.util.UUID

class ProfileRepository(
    private val dataSourceFactory: ProfileDataSourceFactory
) {
    fun fetchUserProfile(callback: RequestCallback<UserAuth>){
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userAuth = localDataSource.fetchSession()

        val dataSource = dataSourceFactory.createFromUser()
        dataSource.fetchUserProfile(userAuth.uuid, object : RequestCallback<UserAuth>{
            override fun onSuccess(data: UserAuth) {
                localDataSource.putUser(data)
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }

        })
    }

    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>){
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userAuth = localDataSource.fetchSession()
        val dataSource = dataSourceFactory.createFromPosts()

        dataSource.fetchUserPosts(userAuth.uuid, object : RequestCallback<List<Post>>{
            override fun onSuccess(data: List<Post>) {
                localDataSource.putPosts(data)
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }

        })
    }

    fun clearCache(){
        val localDataSource = dataSourceFactory.createLocalDataSource()
        localDataSource.putPosts(null)
    }
}