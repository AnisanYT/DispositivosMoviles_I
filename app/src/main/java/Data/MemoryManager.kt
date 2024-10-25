package Data

import Entities.Post
import Entities.User
import Model.PostModel
import Model.UserModel

object ManagerMemory {
    private val userModel = UserModel()
    private val postModel = PostModel()

    fun addUser(user: User) {
        userModel.add(user)
    }

    fun getUser(id: String): User? {
        return userModel.getById(id)
    }

    fun addPost(post: Post) {
        postModel.addPost(post)
    }

    fun getPost(id: String): Post? {
        return postModel.getPostById(id)
    }
}