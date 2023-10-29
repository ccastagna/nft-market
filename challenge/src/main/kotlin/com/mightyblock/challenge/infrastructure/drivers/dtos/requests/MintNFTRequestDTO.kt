package com.mightyblock.challenge.infrastructure.drivers.dtos.requests

data class MintNFTRequestDTO(
    val creatorId: Int,
    val description: String,
    val imageUrl: String,
    val coCreators: List<Int>
)
