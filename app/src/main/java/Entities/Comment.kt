package Entities

class Comment{
    private var _commentId: String = ""
    private var _postId: String = ""
    private var _userId: String = ""
    private var _content: String = ""

    constructor()

    constructor(commentID: String, postID: String, userID: String, content: String){
        this._commentId = commentID
        this._postId = postID
        this._userId = userID
        this._content = content
    }

    var commentID: String
        get() =this._commentId
        set(value) {this._commentId = value}

    var postID: String
        get() = this._postId
        set(value) {this._postId = value}

    var userID: String
        get() = this._userId
        set(value) {this._userId = value}

    var content: String
        get() = this._content
        set(value) {this._content = value}
}
