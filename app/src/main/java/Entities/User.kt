package Entities

class User{
    private var _id: String = ""
    private var _name: String = ""
    private var _email: String = ""
    private var _username: String = ""

    constructor()

    constructor(id: String, name: String, email: String, userName: String){
        this._id = id
        this._name = name
        this._email = email
        this._username = userName
    }

    var id: String
        get() =this._id
        set(value) {this._id = value}

    var name: String
        get() = this._name
        set(value) {this._name = value}

    var email: String
        get() = this.email
        set(value) {this._email = value}

    var username: String
        get() = this._username
        set(value) {this._username = value}
}



