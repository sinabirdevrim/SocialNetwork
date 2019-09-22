package com.firestore.ksgeyik.data.model

import com.firestore.android.repository.model.User

data class Post(
    var id: String,
    var contentTitle: String,
    var contentText: String,
    var type: Int,
    var contentPhotoUrl: String?,
    var user: User?,
    var isAnonymous: Boolean?,
    var likeList: MutableList<User>? = null,
    var commentList: MutableList<Comment>?
) {
    constructor() : this("", "", "", 0, "", null, false, null, null)
}
