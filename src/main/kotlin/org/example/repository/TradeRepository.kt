package org.example.repository

import org.example.entity.TradeEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TradeRepository : JpaRepository<TradeEntity, UUID>
