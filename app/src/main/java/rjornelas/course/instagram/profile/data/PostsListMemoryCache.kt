package rjornelas.course.instagram.profile.data

import rjornelas.course.instagram.common.model.Post

object PostsListMemoryCache : ProfileCache<List<Post>> {

    private var posts: List<Post>? = null

    override fun isCached(): Boolean {
        return posts != null
    }

    override fun get(key: String): List<Post>? {
        return posts
    }

    override fun put(data: List<Post>) {
        posts = data
    }
}