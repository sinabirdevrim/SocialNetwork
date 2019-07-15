package com.firestore.android.repository

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class FireStorageManager {

    val storageRef: StorageReference?

    init {
        storageRef = FirebaseStorage.getInstance().reference
    }

    companion object {
        val instance: FireStorageManager? by lazy { FireStorageManager() }
    }

    fun putFile(uri: Uri): UploadTask? {
        val storageReference = storageRef?.child("images/${uri.lastPathSegment}")
        return storageReference?.putFile(uri)
    }
}