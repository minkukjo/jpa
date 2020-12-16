package me.harry.jpa.board.presentation.response

import me.harry.jpa.board.domain.Post
import me.harry.jpa.board.presentation.response.AbstractResponse

class PostResponse(post: Post) : OkResponse() {
    val id: Long? = post.id
    val title: String = post.title
}