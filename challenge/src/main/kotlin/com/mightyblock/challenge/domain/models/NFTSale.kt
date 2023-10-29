package com.mightyblock.challenge.domain.models

import java.math.BigDecimal
import java.time.LocalDateTime

data class NFTSale(
    val id: Int = -1,
    val buyerUpdated: User,
    val oldOwnerUpdated: User,
    val creatorsUpdated: List<User>,
    val nftUpdated: NFT,
    val price: BigDecimal,
    val time: LocalDateTime
)
