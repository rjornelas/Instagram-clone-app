package rjornelas.course.instagram.register.presentation

import android.net.Uri
import rjornelas.course.instagram.R
import rjornelas.course.instagram.register.RegisterNameAndPassword
import rjornelas.course.instagram.register.RegisterPhoto
import rjornelas.course.instagram.register.data.RegisterCallBack
import rjornelas.course.instagram.register.data.RegisterRepository

class RegisterPhotoPresenter(
    private var view: RegisterPhoto.View?,
    private val repository: RegisterRepository
) : RegisterPhoto.Presenter {

    override fun updateUser(photoUri: Uri) {
        view?.showProgress(true)

        repository.updateUser(photoUri, object : RegisterCallBack{
            override fun onSuccess() {
                view?.onUpdateSuccess()
            }

            override fun onFailure(message: String) {
                view?.onUpdateFailure(message)
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