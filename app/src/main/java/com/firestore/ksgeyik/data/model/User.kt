package com.firestore.android.repository.model

import androidx.databinding.BaseObservable

class User : BaseObservable {

    var name: String? = null
    var surname: String? = null
    var email: String? = null
    var password: String? = null
    var photoUrl: String? = null

    constructor() {
    }

    constructor(name: String?) {
        this.name = name
    }

    constructor(name: String?, surname: String?, email: String?, password: String?, photoUrl: String) {
        this.name = name
        this.surname = surname
        this.email = email
        this.password = password
        this.photoUrl = photoUrl
    }
}
