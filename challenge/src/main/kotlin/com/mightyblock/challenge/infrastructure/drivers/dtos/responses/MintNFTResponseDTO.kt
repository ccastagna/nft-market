package com.mightyblock.challenge.infrastructure.drivers.dtos.responses

import java.util.*

data class MintNFTResponseDTO(
    val nftId: UUID,
    val imageUrl: String,
    val description: String,
)
