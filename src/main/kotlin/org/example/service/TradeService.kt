package org.example.service

import org.example.entity.OrderEntity
import org.example.entity.TradeEntity
import org.example.repository.OrderRepository
import org.example.repository.TradeRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime

@Service
class TradeService(
    private val tradeRepository: TradeRepository,
    private val orderRepository: OrderRepository
) {
    fun createTrade(buyOrder: OrderEntity, sellOrder: OrderEntity) {
        val quantity = minOf(buyOrder.quantity, sellOrder.quantity)
        val price = sellOrder.price

        val trade = TradeEntity(
            buyOrder = buyOrder,
            sellOrder = sellOrder,
            quantity = quantity,
            price = price,
            timestamp = LocalDateTime.now()
        )

        // Mark orders as matched
        buyOrder.matched = true
        sellOrder.matched = true

        // Save everything
        tradeRepository.save(trade)
        orderRepository.saveAll(listOf(buyOrder, sellOrder))
    }
}
