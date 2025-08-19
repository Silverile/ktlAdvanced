package org.example.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    var id: UUID? = null,

    @Enumerated(EnumType.STRING)
    var type: OrderType,

    var quantity: Int,

    var price: BigDecimal,

    var matched: Boolean = false
)


enum class OrderType {
    BUY, SELL
}