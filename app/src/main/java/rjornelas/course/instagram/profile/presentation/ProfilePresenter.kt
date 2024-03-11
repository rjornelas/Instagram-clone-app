package rjornelas.course.instagram.profile.presentation

import android.app.DownloadManager.Request
import android.util.Patterns
import rjornelas.course.instagram.R
import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.Database
import rjornelas.course.instagram.common.model.Post
import rjornelas.course.instagram.common.model.UserAuth
import rjornelas.course.instagram.profile.Profile
import rjornelas.course.instagram.profile.data.ProfileRepository
import rjornelas.course.instagram.register.RegisterEmail
import rjornelas.course.instagram.register.data.RegisterCallBack
import rjornelas.course.instagram.register.data.RegisterRepository
import java.lang.RuntimeException

class ProfilePresenter(
    private var view: Profile.View?,
    private val repository: ProfileRepository
) : Profile.Presenter {

    override fun fetchUserProfile() {
        view?.showProgress(true)
        val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("user not found")
        repository.fetchUserProfile(object : RequestCallback<UserAuth>{
            override fun onSuccess(data: UserAuth) {
                view?.displayUserProfile(data)
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {}

        })
    }

    override fun fetchUserPosts() {
        val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("user not found")
        repository.fetchUserPosts(userUUID, object : RequestCallback<List<Post>>{
            override fun onSuccess(data: List<Post>) {
                if(data.isEmpty()){
                    view?.displayEmptyPosts()
                }else{
                    view?.displayFullPosts(data)
                }
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }

        })
    }

    override fun onDestroy() {
        view = null
    }
}