package com.mightyblock.challenge.infrastructure.drivers.dtos.requests

import java.math.BigDecimal
import java.util.*

data class BuyNFTRequestDTO(val buyerId: Int, val amount: BigDecimal, var nftId: UUID? = null)
