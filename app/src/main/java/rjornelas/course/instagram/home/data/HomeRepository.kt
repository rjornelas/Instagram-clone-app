package rjornelas.course.instagram.home.data

import android.net.Uri
import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.Post
import rjornelas.course.instagram.common.model.UserAuth
import rjornelas.course.instagram.register.data.RegisterCallBack
import rjornelas.course.instagram.register.data.RegisterSource
import java.util.UUID

class HomeRepository(
    private val dataSourceFactory: HomeDataSourceFactory
) {

    fun fetchFeed(callback: RequestCallback<List<Post>>){
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userAuth = localDataSource.fetchSession()
        val dataSource = dataSourceFactory.createFromPosts()

        dataSource.fetchFeed(userAuth.uuid, object : RequestCallback<List<Post>>{
            override fun onSuccess(data: List<Post>) {
                localDataSource.putFeed(data)
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
}