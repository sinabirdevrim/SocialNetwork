package com.firestore.android.repository.model

data class User(
    var name: String?,
    var surname: String?,
    var email: String?,
    var password: String?,
    var photoUrl: String?
) {
    constructor() : this("", "", "", "","")
}