package com.firestore.android.repository

import javax.inject.Singleton

@Singleton
class DataManager {

    fun getFireStorageManager(): FireStorageManager? {
        return FireStorageManager.instance
    }

    fun getFireStoreManager(): FireStoreManager? {
        return FireStoreManager.instance
    }

    fun getFireStoreAuth(): FireAuthManager? {
        return FireAuthManager.instance
    }
}