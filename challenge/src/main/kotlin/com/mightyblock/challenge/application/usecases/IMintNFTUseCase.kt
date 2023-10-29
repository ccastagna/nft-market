package com.mightyblock.challenge.application.usecases

import com.mightyblock.challenge.application.models.MintNFTRequest
import com.mightyblock.challenge.domain.models.NFT

interface IMintNFTUseCase {
    fun execute(request: MintNFTRequest): NFT
}
