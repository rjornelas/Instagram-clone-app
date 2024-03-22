package rjornelas.course.instagram.add.data

import android.net.Uri
import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.UserAuth

interface AddDataSource {

    fun createPost(
        userUUID: String,
        uri: Uri,
        caption: String,
        callback: RequestCallback<Boolean>
    ) {
        throw UnsupportedOperationException()
    }

    fun fetchSession(): String {
        throw UnsupportedOperationException()
    }
}