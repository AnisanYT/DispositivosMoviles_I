package Entities

import android.graphics.Bitmap

class User{
    private var _id: String = ""
    private var _password: String = ""
    private var _email: String = ""
    private var _username: String = ""
    private var _profileImage: Bitmap? = null

    constructor()

    constructor(id: String, password: String, email: String, userName: String, profileIMG: Bitmap?){
        this._id = id
        this._password = password
        this._email = email
        this._username = userName
        this._profileImage = profileIMG
    }

    var image: Bitmap?
        get() = this._profileImage
        set(value) {this._profileImage = value}

    var id: String
        get() =this._id
        set(value) {this._id = value}

    var password: String
        get() = this._password
        set(value) {this._password = value}

    var email: String
        get() = this._email
        set(value) {this._email = value}

    var username: String
        get() = this._username
        set(value) {this._username = value}
}



