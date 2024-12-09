package Data

import Entities.Comment
import Entities.Post
import Entities.User
import Model.CommentModel
import Model.PostModel
import Model.UserModel_withOut_SQLite

object ManagerMemory {
    private val userModel = UserModel_withOut_SQLite()
    private val postModel = PostModel()
    private val commentModel = CommentModel()

    fun addUser(user: User) {
        userModel.add(user)
    }

    fun getUser(id: String): User? {
        return userModel.getById(id)
    }

    fun getUserByEmail(email: String): User? {
        return userModel.getByEmail(email)
    }

    fun addPost(post: Post) {
        postModel.addPost(post)
    }

    fun getPost(id: String): Post? {
        return postModel.getPostById(id)
    }

    fun addComment(comment: Comment) {
        commentModel.addComment(comment)
    }
    fun getComment(id: String): Comment? {
        return commentModel.getCommentById(id)
    }
}