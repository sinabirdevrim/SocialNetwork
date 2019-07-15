package com.firestore.android.repository.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class User : BaseObservable {

    var name: String? = null
    var surname: String? = null
    var email: String? = null
    var password: String? = null
    @get:Bindable
    var photoUrl: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.photoUrl)
        }


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
