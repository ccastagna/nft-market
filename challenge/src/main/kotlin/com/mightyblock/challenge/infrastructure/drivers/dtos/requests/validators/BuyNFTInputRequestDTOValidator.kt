package com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators

import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.BuyNFTRequestDTO
import java.math.BigDecimal

class BuyNFTInputRequestDTOValidator : IInputRequestValidator<BuyNFTRequestDTO> {

    override fun validate(request: BuyNFTRequestDTO) {
        if (request.buyerId <= 0) throw IllegalArgumentException("Invalid buyerId: ${request.buyerId}.")

        if (request.nftId == null) throw IllegalArgumentException("NFTId is required.")

        if (request.amount <= BigDecimal.ZERO) throw IllegalArgumentException("Invalid NFT price: ${request.amount}.")
    }
}