package rjornelas.course.instagram.search.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import rjornelas.course.instagram.common.base.RequestCallback
import rjornelas.course.instagram.common.model.User
import rjornelas.course.instagram.common.model.UserAuth

class FireSearchDataSource : SearchDataSource {
    override fun fetchUsers(name: String, callback: RequestCallback<List<User>>) {
        FirebaseFirestore.getInstance()
            .collection("/users")
            .whereGreaterThanOrEqualTo("name", name)
            .whereLessThanOrEqualTo("name", name + "\uf8ff")
            .get()
            .addOnSuccessListener { res ->
                val documents = res.documents
                val users = mutableListOf<User>()
                for (document in documents){
                    val user = document.toObject(User::class.java)
                    if(user != null && user.uuid != FirebaseAuth.getInstance().uid){
                        users.add(user)
                    }
                }
                callback.onSuccess(users)
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Falha ao buscar usuário")
            }
            .addOnCompleteListener{
                callback.onComplete()
            }
    }
}