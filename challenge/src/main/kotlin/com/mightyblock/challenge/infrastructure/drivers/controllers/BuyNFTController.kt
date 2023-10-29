package com.mightyblock.challenge.infrastructure.drivers.controllers

import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.BuyNFTRequestDTO
import com.mightyblock.challenge.infrastructure.drivers.handlers.IRequestHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/nfts")
class BuyNFTController( private val buyNFTHandler: IRequestHandler<BuyNFTRequestDTO>) {

    @PostMapping("/{nftId}/buy")
    fun buyNFT(@PathVariable nftId: UUID, @RequestBody buyNFTRequestDTO: BuyNFTRequestDTO): ResponseEntity<Any> {
        buyNFTRequestDTO.nftId = nftId
        return buyNFTHandler.handle(buyNFTRequestDTO)
    }
}