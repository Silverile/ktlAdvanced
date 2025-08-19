package org.example.repository
import org.example.entity.OrderType

import org.example.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigDecimal
import java.util.*

interface OrderRepository : JpaRepository<OrderEntity, UUID> {
    fun findFirstByMatchedFalseAndTypeNotAndPriceAndQuantity(
        type: OrderType,
        price: BigDecimal,
        quantity: Int
    ): OrderEntity?
}
