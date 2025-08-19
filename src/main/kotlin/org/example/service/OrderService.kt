package org.example.service

import org.example.entity.OrderEntity
import org.example.entity.OrderType
import org.example.entity.TradeEntity
import org.example.repository.OrderRepository
import org.example.repository.TradeRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val tradeRepository: TradeRepository,
    private val tradeService: TradeService
) {

    fun placeOrder(order: OrderEntity): OrderEntity {
        // Try to match the order
        val oppositeType = if (order.type == OrderType.BUY) OrderType.SELL else OrderType.BUY

        val potentialMatches = orderRepository.findAll()
            .filter { it.type == oppositeType && !it.matched && it.price == order.price && it.quantity == order.quantity }

        if (potentialMatches.isNotEmpty()) {
            val matchedOrder = potentialMatches.first()
            tradeService.createTrade(
                buyOrder = if (order.type == OrderType.BUY) order else matchedOrder,
                sellOrder = if (order.type == OrderType.SELL) order else matchedOrder
            )
        }

        return orderRepository.save(order)
    }


    fun getAllOrders(): List<OrderEntity> = orderRepository.findAll()

    fun getAllTrades(): List<TradeEntity> = tradeRepository.findAll()

    private fun matchOrders() {
        val buyOrders = orderRepository.findAll().filter { it.type == OrderType.BUY && !it.matched }
        val sellOrders = orderRepository.findAll().filter { it.type == OrderType.SELL && !it.matched }

        for (buy in buyOrders) {
            val match = sellOrders.find { sell ->
                !sell.matched && sell.price <= buy.price && sell.quantity == buy.quantity
            }

            if (match != null) {
                // Create Trade
                val trade = TradeEntity(
                    buyOrder = buy,
                    sellOrder = match,
                    price = match.price, // Assume seller's price wins
                    quantity = match.quantity
                )

                tradeRepository.save(trade)

                // Update matched flags
                buy.matched = true
                match.matched = true

                orderRepository.saveAll(listOf(buy, match))
            }
        }
    }
}
