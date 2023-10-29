package com.mightyblock.challenge.domain.models

import java.math.BigDecimal

data class NFTSaleRequirements(
    val buyer: User,
    val owner: User,
    val creators: List<User>,
    val nft: NFT,
    val price: BigDecimal
)
