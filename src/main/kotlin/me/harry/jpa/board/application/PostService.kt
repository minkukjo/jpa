package me.harry.jpa.board.application

import me.harry.jpa.board.presentation.exception.NotFoundException
import me.harry.jpa.board.domain.Post
import me.harry.jpa.board.infrastructure.PostRepository
import me.harry.jpa.board.presentation.exception.ResponseException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.Throws

@Service
class PostService(
        val postRepository: PostRepository
) {

    @Transactional
    fun create(post: Post) {
        postRepository.save(post)
    }

    @Transactional(readOnly = true)
    // Throws를 붙이고 떼고 차이를 직접 보여드릴 것
    @Throws(ResponseException::class)
    fun read(id: Long): Post {
        return postRepository.findByIdOrNull(id) ?: throw NotFoundException.POST.get()
    }

    fun update() {

    }

    fun delete() {

    }
}