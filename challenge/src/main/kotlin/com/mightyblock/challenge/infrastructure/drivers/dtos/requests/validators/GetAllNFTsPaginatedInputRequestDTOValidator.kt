package com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators

import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.GetAllNFTsPaginatedRequestDTO

class GetAllNFTsPaginatedInputRequestDTOValidator: IInputRequestValidator<GetAllNFTsPaginatedRequestDTO>  {
    override fun validate(request: GetAllNFTsPaginatedRequestDTO) {

        if (request.page <= 0) throw IllegalArgumentException("Invalid page: ${request.page}.")

        if (request.size <= 0) throw IllegalArgumentException("Invalid size: ${request.size}.")
    }
}