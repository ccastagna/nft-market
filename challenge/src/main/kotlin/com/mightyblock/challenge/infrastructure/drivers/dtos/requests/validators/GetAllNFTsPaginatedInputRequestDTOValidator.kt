package com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators

import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.GetAllNFTsPaginatedRequestDTO

class GetAllNFTsPaginatedInputRequestDTOValidator : IInputRequestValidator<GetAllNFTsPaginatedRequestDTO> {
    override fun validate(request: GetAllNFTsPaginatedRequestDTO) {

        if (request.page <= 0) throw IllegalArgumentException("Invalid page: ${request.page}. Page must be greater than 0.")

        if (request.size <= 0) throw IllegalArgumentException("Invalid size: ${request.size}. Size must be greater than 0.")
    }
}