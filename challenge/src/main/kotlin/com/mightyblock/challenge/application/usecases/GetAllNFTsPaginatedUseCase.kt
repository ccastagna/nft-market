package com.mightyblock.challenge.application.usecases

import com.mightyblock.challenge.application.models.GetAllNFTsPaginatedRequest
import com.mightyblock.challenge.domain.services.nfts.INFTService
import com.mightyblock.challenge.domain.models.PageableNFTList

class GetAllNFTsPaginatedUseCase(private val nftService: INFTService) : IGetAllNFTsPaginatedUseCase {
    override fun execute(request: GetAllNFTsPaginatedRequest): PageableNFTList {
        return nftService.getAllNFTsPaginated(request.page, request.size)
    }
}