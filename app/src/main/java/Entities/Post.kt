package Entities

class Post{
    private var _postId: String = ""
    private var _userId: String = ""
    private var _content: String = ""
    private var _createAt: String = ""

    constructor()

    constructor(postId: String, userID: String, content: String, createAt: String){
        this._postId = postId
        this._userId = userID
        this._content = content
        this._createAt = createAt
    }

    var postid: String
        get() =this._postId
        set(value) {this._postId = value}

    var userid: String
        get() = this._userId
        set(value) {this._userId = value}

    var content: String
        get() = this._content
        set(value) {this._content = value}

    var createat: String
        get() = this._createAt
        set(value) {this._createAt = value}
}
