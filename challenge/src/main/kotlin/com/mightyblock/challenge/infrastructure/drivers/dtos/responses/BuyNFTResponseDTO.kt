package com.mightyblock.challenge.infrastructure.drivers.dtos.responses

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class BuyNFTResponseDTO(
    val id: Int,
    val buyer: User,
    val oldOwner: User,
    val creators: List<User>,
    val nft: NFT,
    val price: BigDecimal,
    val time: LocalDateTime
) {
    data class User(val id: Int, val balance: BigDecimal)
    data class NFT(val id: UUID, val ownerId: Int)
}
