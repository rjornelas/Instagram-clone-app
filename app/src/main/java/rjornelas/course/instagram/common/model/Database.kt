package rjornelas.course.instagram.common.model

import android.net.Uri
import java.io.File
import java.util.UUID

object Database {

    val usersAuth = mutableListOf<UserAuth>()
    val posts = hashMapOf<String, MutableSet<Post>>()
    val feeds = hashMapOf<String, MutableSet<Post>>()
    val followers = hashMapOf<String, Set<String>>()

    var sessionAuth: UserAuth? = null

    init {
        val userA = UserAuth(
            UUID.randomUUID().toString(),
            "userA",
            "userA@gmail.com",
            "12345678",
            Uri.fromFile(
                File("/storage/emulated/0/Pictures/.thumbnails/1000000036.jpg")
            )
        )
        val userB = UserAuth(
            UUID.randomUUID().toString(),
            "userA",
            "userB@gmail.com",
            "87654321",
            Uri.fromFile(File("/storage/emulated/0/Pictures/.thumbnails/1000000036.jpg"))
        )

        usersAuth.add(userA)
        usersAuth.add(userB)

        followers[userA.uuid] = hashSetOf()
        posts[userA.uuid] = hashSetOf()
        feeds[userA.uuid] = hashSetOf()

        followers[userB.uuid] = hashSetOf()
        posts[userB.uuid] = hashSetOf()
        feeds[userB.uuid] = hashSetOf()

        sessionAuth = usersAuth.first()

    }
}