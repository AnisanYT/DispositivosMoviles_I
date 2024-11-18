package Model

import Entities.Comment

class CommentModel {
    private val comments: MutableList<Comment> = mutableListOf()

    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    fun getCommentById(id: String): Comment? {
        return comments.find { it.commentID == id }
    }

    fun getAllComments(): List<Comment> {
        return comments
    }
}
