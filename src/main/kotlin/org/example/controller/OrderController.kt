package org.example.controller

import org.example.entity.OrderEntity
import org.example.entity.TradeEntity
import org.example.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    fun placeOrder(@RequestBody order: OrderEntity): ResponseEntity<OrderEntity> {
        val placedOrder = orderService.placeOrder(order)
        return ResponseEntity.ok(placedOrder)
    }

    @GetMapping
    fun getOrders(): List<OrderEntity> = orderService.getAllOrders()

    @GetMapping("/trades")
    fun getTrades(): List<TradeEntity> = orderService.getAllTrades()
}
