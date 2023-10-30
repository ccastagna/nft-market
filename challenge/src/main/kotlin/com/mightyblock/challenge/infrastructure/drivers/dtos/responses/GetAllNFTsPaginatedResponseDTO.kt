package com.mightyblock.challenge.infrastructure.drivers.dtos.responses

import java.util.*

data class GetAllNFTsPaginatedResponseDTO(val nftList: List<NFT>, val page: Int, val size: Int, val totalElements: Long, val totalPages: Int) {
    data class NFT(val nftId: UUID, val imageUrl: String, val description: String)
}
