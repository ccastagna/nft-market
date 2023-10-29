package com.mightyblock.challenge.application.usecases

import com.mightyblock.challenge.application.models.BuyNFTRequest
import com.mightyblock.challenge.domain.models.NFTSale

interface IBuyNFTUseCase {
    fun execute(request: BuyNFTRequest): NFTSale
}
