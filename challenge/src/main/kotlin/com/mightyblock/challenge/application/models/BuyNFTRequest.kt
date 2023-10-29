package com.mightyblock.challenge.application.models

import java.math.BigDecimal
import java.util.*

data class BuyNFTRequest(val nftId: UUID, val buyerId: Int, val price: BigDecimal)
