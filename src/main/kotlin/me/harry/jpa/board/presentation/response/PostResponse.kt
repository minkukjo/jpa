package me.harry.jpa.board.presentation.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import me.harry.jpa.board.domain.Post
import me.harry.jpa.board.presentation.response.AbstractResponse

@JsonInclude(JsonInclude.Include.NON_NULL)
class PostResponse(post: Post) : OkResponse() {
    @JsonProperty("id")
    val id: Long? = post.id

    @JsonProperty("title")
    val title: String = post.title
}