package rjornelas.course.instagram.add.data

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.Post
import rjornelas.course.instagram.common.model.User
import java.lang.RuntimeException

class FireAddDataSource : AddDataSource {
    override fun createPost(
        userUUID: String,
        uri: Uri,
        caption: String,
        callback: RequestCallback<Boolean>
    ) {
        val uriLastPath = uri.lastPathSegment ?: throw IllegalArgumentException("Invalid img")

        val imgRef = FirebaseStorage.getInstance().reference
            .child("images/")
            .child(userUUID)
            .child(uriLastPath)

        imgRef.putFile(uri)
            .addOnSuccessListener { res ->
                imgRef.downloadUrl
                    .addOnSuccessListener { resDownload ->

                        FirebaseFirestore.getInstance()
                            .collection("/users")
                            .document()
                            .get()
                            .addOnSuccessListener { resMe ->
                                val me = resMe.toObject(User::class.java)

                                val postRef = FirebaseFirestore.getInstance()
                                    .collection("/posts")
                                    .document(userUUID)
                                    .collection("/posts")
                                    .document()

                                val post = Post(
                                    uuid = postRef.id,
                                    photoUrl = resDownload.toString(),
                                    caption = caption,
                                    timestamp = System.currentTimeMillis(),
                                    publisher = me
                                )

                                postRef.set(post)
                                    .addOnSuccessListener { respost ->

                                        FirebaseFirestore.getInstance()
                                            .collection("/feeds")
                                            .document(userUUID)
                                            .collection("posts")
                                            .document(postRef.id)
                                            .set(post)
                                            .addOnSuccessListener { resMyFeed ->

                                                FirebaseFirestore.getInstance()
                                                    .collection("/followers")
                                                    .document(userUUID)
                                                    .collection("followers")
                                                    .get()
                                                    .addOnSuccessListener { resFollowers ->
                                                        val documents = resFollowers.documents

                                                        for (document in documents){
                                                            val followerUUID = document.toObject(String::class.java) ?: throw RuntimeException("Falha ao converter seguidor")

                                                            FirebaseFirestore.getInstance()
                                                                .collection("/feeds")
                                                                .document(followerUUID)
                                                                .collection("posts")
                                                                .document(postRef.path)
                                                                .set(post)
                                                        }

                                                        callback.onSuccess(true)
                                                    }
                                                    .addOnFailureListener { exception ->
                                                        callback.onFailure(exception.message ?: "Falha ao buscar meus seguidores")
                                                    }
                                                    .addOnCompleteListener{
                                                        callback.onComplete()
                                                    }
                                            }

                                    }
                                    .addOnFailureListener { exception ->
                                        callback.onFailure(
                                            exception.message ?: "Falha ao inserir post"
                                        )
                                    }
                            }

                    }
                    .addOnFailureListener { exception ->
                        callback.onFailure(exception.message ?: "Falha ao baixar a foto")
                    }
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Falha ao subir a foto")
            }
    }
}