package me.harry.jpa.shop.presentation

import me.harry.jpa.shop.application.OrderService
import me.harry.jpa.shop.presentation.dto.OrderDTO
import me.harry.jpa.shop.presentation.response.OrderResponse
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.beans.factory.annotation.Qualifier
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
        val orderService: OrderService,
        @Qualifier("client") val esClient: RestHighLevelClient
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Throws(Throwable::class)
    fun create(@RequestBody orderDTO: OrderDTO) {
        val order = orderService.create(orderDTO.toOrder())
//        val map = hashMapOf<String, Any>()
//        map["orderStatus"] = order.orderStatus
//        map["orderDate"] = order.orderDate
//        val indexRequest = IndexRequest("orders").id("2").source(map)
//        val indexResponse: IndexResponse = esClient.index(indexRequest, RequestOptions.DEFAULT)
//        println(indexResponse)
    }

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