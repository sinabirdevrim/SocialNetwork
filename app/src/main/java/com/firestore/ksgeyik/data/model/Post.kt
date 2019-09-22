package com.firestore.ksgeyik.data.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
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
) : BaseObservable() {
    constructor() : this("", "", "", 0, "", null, false, null, null)

    var _likeList: MutableList<User>?
        @Bindable get() = likeList
        set(value) {
            likeList = value
            notifyPropertyChanged(BR._likeList)
        }

}
