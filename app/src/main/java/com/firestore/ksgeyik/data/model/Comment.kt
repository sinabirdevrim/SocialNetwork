package com.firestore.ksgeyik.data.model

import com.firestore.android.repository.model.User

data class Comment(val user: User?, var commentText: String?)