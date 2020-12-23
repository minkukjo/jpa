package me.harry.jpa.shop.presentation

import me.harry.jpa.shop.application.OrderService
import me.harry.jpa.shop.infrastructure.aop.log.LogExecutionTime
import me.harry.jpa.shop.presentation.dto.OrderDTO
import me.harry.jpa.shop.presentation.response.OrderResponse
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
@RequestMapping("/api/order")
class OrderController(
        val orderService: OrderService
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody orderDTO: OrderDTO) {
        orderService.create(orderDTO.toOrder())
    }

    @LogExecutionTime
    @GetMapping("/{id}")
    fun read(@PathVariable("id") id: Long): OrderResponse {
        val post = orderService.read(id)
        return OrderResponse(post)
    }

    @PutMapping
    fun update() {

    }

    @DeleteMapping
    fun delete() {

    }
}