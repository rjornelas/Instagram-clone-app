package rjornelas.course.instagram.profile.data

import rjornelas.course.instagram.common.base.Cache
import rjornelas.course.instagram.common.model.User
import rjornelas.course.instagram.common.model.UserAuth

object ProfileMemoryCache : Cache<Pair<User, Boolean?>> {
    private var userAuth: Pair<User, Boolean?>? = null
    override fun isCached(): Boolean {
        return userAuth != null
    }

    override fun get(key: String): Pair<User, Boolean?>? {
        if(userAuth?.first?.uuid == key){
            return userAuth
        }
        return null
    }

    override fun put(data: Pair<User, Boolean?>?) {
        userAuth = data
    }
}