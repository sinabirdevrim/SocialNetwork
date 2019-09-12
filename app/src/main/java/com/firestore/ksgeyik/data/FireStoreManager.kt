package com.firestore.android.repository

import com.firestore.android.repository.model.Post
import com.firestore.android.repository.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*

class FireStoreManager {

    val db: FirebaseFirestore?

    init {
        db = FirebaseFirestore.getInstance()
    }

    companion object {
        val instance: FireStoreManager? by lazy { FireStoreManager() }
    }

    fun saveUser(user: User): Task<DocumentReference>? {
        return db?.collection("user")?.add(user)
    }

    fun getUser(userId: String): Task<DocumentSnapshot>? {
        return db?.collection("user")?.document(userId)?.get()
    }

    fun login(email: String, password: String): Query? {
       return db?.collection("user")?.whereEqualTo("email", email)?.whereEqualTo("password",password)
    }

    fun savePost(post: Post): Task<DocumentReference>? {
        return db?.collection("post")?.add(post)
    }

    fun getPosts(): Task<QuerySnapshot>? {
        return db?.collection("post")?.get()
    }

    fun getUserByUserNameAndPassWord(email: String, password: String): Task<QuerySnapshot>? {
        return db?.collection("user")?.whereEqualTo("email", email)?.whereEqualTo("password", password)?.get()

    }
}