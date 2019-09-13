package com.firestore.android.repository.model

data class Post(
    var contentTitle: String,
    var contentText: String,
    var type: Int,
    var contentPhotoUrl: String?,
    var user: User?,
    var isAnonymous: Boolean?
) {
    constructor() : this("", "", 0, "", null, false)
}