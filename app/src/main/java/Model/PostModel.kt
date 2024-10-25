package Model
import Entities.Post
class PostModel {
    private val posts: MutableList<Post> = mutableListOf()

    fun addPost(post: Post) {
        posts.add(post)
    }

    fun getPostById(id: String): Post? {
        return posts.find { it.postid == id }
    }

    fun getAllPosts(): List<Post> {
        return posts
    }
}
