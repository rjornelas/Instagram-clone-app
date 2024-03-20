package rjornelas.course.instagram.register.data

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import rjornelas.course.instagram.common.model.User

class FireRegisterSource : RegisterSource {
    override fun create(email: String, callback: RegisterCallBack) {

        FirebaseFirestore.getInstance()
            .collection("/users")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    callback.onSuccess()
                } else {
                    callback.onFailure("Usuário já cadastrado")
                }
            }.addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Internal server error")
            }.addOnCompleteListener {
                callback.onComplete()
            }
    }

    override fun create(
        name: String,
        email: String,
        password: String,
        confirmedPassword: String,
        callback: RegisterCallBack
    ) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid
                if (uid == null) {
                    callback.onFailure("Internal server error")
                } else {
                    FirebaseFirestore.getInstance()
                        .collection("/users")
                        .document(uid)
                        .set(
                            hashMapOf(
                                "name" to name,
                                "email" to email,
                                "followers" to 0,
                                "following" to 0,
                                "postCount" to 0,
                                "uuid" to uid,
                                "photoUrl" to null,
                            )
                        ).addOnSuccessListener {
                            callback.onSuccess()
                        }.addOnFailureListener { exception ->
                            callback.onFailure(exception.message ?: "Internal server error")
                        }.addOnCompleteListener {
                            callback.onComplete()
                        }
                }
            }.addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Internal server error")
            }
    }

    override fun updateUser(photoUri: Uri, callback: RegisterCallBack) {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null || photoUri.lastPathSegment == null) {
            callback.onFailure("Usuário não encontrado")
            return
        }

        val storageRef = FirebaseStorage.getInstance().reference
        val imgRef = storageRef.child("images/")
            .child(uid)
            .child(photoUri.lastPathSegment!!)

        imgRef.putFile(photoUri)
            .addOnSuccessListener { result ->
                imgRef.downloadUrl
                    .addOnCompleteListener { res ->
                        val userRef =
                            FirebaseFirestore.getInstance().collection("/users").document(uid)

                        userRef.get()
                            .addOnSuccessListener { document ->
                                val user = document.toObject(User::class.java)
                                val newUser = user?.copy(photoUrl = res.toString())

                                if (newUser != null) {
                                    userRef.set(newUser)
                                        .addOnSuccessListener {
                                            callback.onSuccess()
                                        }
                                        .addOnFailureListener { exception ->
                                            callback.onFailure(
                                                exception.message ?: "Falha ao atualizar a foto"
                                            )
                                        }
                                        .addOnCompleteListener {
                                            callback.onComplete()
                                        }
                                }
                            }
                    }
            }.addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Falha ao subir a foto")
            }
    }

}