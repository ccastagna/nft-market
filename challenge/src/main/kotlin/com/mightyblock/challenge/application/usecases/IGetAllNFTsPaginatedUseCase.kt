package com.mightyblock.challenge.application.usecases

import com.mightyblock.challenge.application.models.GetAllNFTsPaginatedRequest
import com.mightyblock.challenge.domain.models.PageableNFTList

interface IGetAllNFTsPaginatedUseCase {
    fun execute(request: GetAllNFTsPaginatedRequest): PageableNFTList

}
