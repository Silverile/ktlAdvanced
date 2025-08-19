package org.example.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "trades")
data class TradeEntity(
    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    var id: UUID? = null,

    @ManyToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "buy_order_id")
    val buyOrder: OrderEntity,

    @ManyToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "sell_order_id")
    val sellOrder: OrderEntity,


    val price: BigDecimal,
    val quantity: Int,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
