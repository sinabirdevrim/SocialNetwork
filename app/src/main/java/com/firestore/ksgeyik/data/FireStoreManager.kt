package com.firestore.android.repository

import com.firestore.android.repository.model.Post
import com.firestore.android.repository.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

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

    fun savePost(post: Post): Task<DocumentReference>? {
        return db?.collection("post")?.add(post)
    }

    fun getPosts(): Task<QuerySnapshot>? {
        return db?.collection("post")?.get()
    }
}