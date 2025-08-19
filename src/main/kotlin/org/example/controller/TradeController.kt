package org.example.controller

import org.example.entity.TradeEntity
import org.example.repository.TradeRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/trades")
class TradeController(private val tradeRepository: TradeRepository) {

    @GetMapping
    fun getAllTrades(): List<TradeEntity> = tradeRepository.findAll()
}
