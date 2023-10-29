package com.mightyblock.challenge.infrastructure.drivers.controllers

import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.MintNFTRequestDTO
import com.mightyblock.challenge.infrastructure.drivers.handlers.IRequestHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/nfts")
class MintNFTController(private val mintNFTHandler: IRequestHandler<MintNFTRequestDTO>) {

    @PostMapping
    fun mintNFT(@RequestBody mintNFTRequestDTO: MintNFTRequestDTO): ResponseEntity<Any> {
        return mintNFTHandler.handle(mintNFTRequestDTO)
    }
}