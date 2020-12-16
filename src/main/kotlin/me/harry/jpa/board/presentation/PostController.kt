package me.harry.jpa.board.presentation

import me.harry.jpa.board.application.PostService
import me.harry.jpa.board.presentation.dto.PostDTO
import me.harry.jpa.board.presentation.response.PostResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
class PostController(
        val postService: PostService
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createPost(@RequestBody postDTO: PostDTO) {
        postService.create(postDTO.toPost())
    }

    @GetMapping("/{id}")
    fun readPost(@PathVariable("id") id: Long): PostResponse {
        val post = postService.read(id)
        return PostResponse(post)
    }

    @PutMapping
    fun updatePost() {

    }

    @DeleteMapping
    fun deletePost() {

    }
}