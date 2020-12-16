package me.harry.jpa.board.presentation.dto

import com.fasterxml.jackson.annotation.JsonProperty
import me.harry.jpa.board.domain.Post

class PostDTO(
        @JsonProperty(value = "title")
        private val title: String
) {
    fun toPost(): Post {
        return Post(title = title)
    }
}