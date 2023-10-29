package com.mightyblock.challenge.infrastructure.drivers.dtos.responses

import java.util.*

data class GetAllNFTsPaginatedResponseDTO(val nftList: Any) {
    data class NFT(val nftId: UUID, val imageUrl: String, val description: String)
}
