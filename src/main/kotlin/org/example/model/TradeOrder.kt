package org.example.model

import java.math.BigDecimal
import java.util.UUID


enum class OrderType {
    BUY, SELL
}


data class TradeOrder(
    val id: UUID = UUID.randomUUID(),
    val type: OrderType,
    val quantity: Int,
    val price: BigDecimal,
    var matched: Boolean = false
)