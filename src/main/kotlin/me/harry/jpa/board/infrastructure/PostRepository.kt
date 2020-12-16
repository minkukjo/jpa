package me.harry.jpa.board.infrastructure

import me.harry.jpa.board.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
}