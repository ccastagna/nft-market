package com.mightyblock.challenge.infrastructure.drivers.controllers

import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.GetAllNFTsPaginatedRequestDTO
import com.mightyblock.challenge.infrastructure.drivers.handlers.IRequestHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/nfts")
class GetAllNFTsPaginatedController(private val getAllNFTsPaginatedHandler: IRequestHandler<GetAllNFTsPaginatedRequestDTO>) {

    @GetMapping
    fun getAllNFTsPaginated(@RequestParam(defaultValue = "0") page: Int, @RequestParam(defaultValue = "3") size: Int): ResponseEntity<Any> {
        val getAllNFTsPaginatedRequestDTO = GetAllNFTsPaginatedRequestDTO(page, size)
        return getAllNFTsPaginatedHandler.handle(getAllNFTsPaginatedRequestDTO)
    }
}