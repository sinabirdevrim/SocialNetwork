package com.firestore.android.repository

import com.google.firebase.auth.FirebaseAuth

class FireAuthManager {

    val auth: FirebaseAuth?

    init {
        auth = FirebaseAuth.getInstance()
    }

    companion object {
        val instance: FireAuthManager? by lazy { FireAuthManager() }
    }


}