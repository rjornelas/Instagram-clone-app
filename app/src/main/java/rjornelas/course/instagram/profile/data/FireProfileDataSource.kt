package rjornelas.course.instagram.profile.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.Post
import rjornelas.course.instagram.common.model.User
import rjornelas.course.instagram.common.model.UserAuth

class FireProfileDataSource : ProfileDataSource {
    override fun fetchUserProfile(
        userUUID: String,
        callback: RequestCallback<Pair<User, Boolean?>>
    ) {
        FirebaseFirestore.getInstance()
            .collection("/users")
            .document(userUUID)
            .get()
            .addOnSuccessListener { res ->
                val user = res.toObject(User::class.java)
                when (user) {
                    null -> {
                        callback.onFailure("Falha ao converter usuário")
                    }

                    else -> {
                        if (user.uuid == FirebaseAuth.getInstance().uid) {
                            callback.onSuccess(Pair(user, null))
                        } else {
                            FirebaseFirestore.getInstance()
                                .collection("/followers")
                                .document(FirebaseAuth.getInstance().uid!!)
                                .collection("followers")
                                .whereEqualTo("uuid", userUUID)
                                .get()
                                .addOnSuccessListener { response ->
                                    callback.onSuccess(Pair(user, !response.isEmpty))
                                }
                                .addOnFailureListener { exception ->
                                    callback.onFailure(
                                        exception.message ?: "Falha ao buscar usuário"
                                    )
                                }
                                .addOnCompleteListener {
                                    callback.onComplete()
                                }
                        }
                    }
                }
            }.addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Falha ao buscar usuário")
            }
    }

    override fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {
        FirebaseFirestore.getInstance()
            .collection("posts")
            .document(userUUID)
            .collection("posts")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { res ->
                val documents = res.documents
                val posts = mutableListOf<Post>()
                for(document in documents){
                    val post = document.toObject(Post::class.java)
                    post?.let {
                        posts.add(it)
                    }
                }
                callback.onSuccess(posts)
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Falha ao buscar posts")
            }
            .addOnCompleteListener{
                callback.onComplete()
            }
    }

    override fun followUser(
        userUUID: String,
        isFollow: Boolean,
        callback: RequestCallback<Boolean>
    ) {

    }
}