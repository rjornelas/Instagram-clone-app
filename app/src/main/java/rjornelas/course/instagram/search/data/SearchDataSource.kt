package rjornelas.course.instagram.search.data

import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.UserAuth

interface SearchDataSource {
    fun fetchUsers(name: String, callback: RequestCallback<List<UserAuth>>)
}