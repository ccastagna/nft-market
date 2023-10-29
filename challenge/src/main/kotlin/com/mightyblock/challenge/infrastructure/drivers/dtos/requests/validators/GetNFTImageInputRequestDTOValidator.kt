package com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators

import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.GetNFTImageRequestDTO

class GetNFTImageInputRequestDTOValidator : IInputRequestValidator<GetNFTImageRequestDTO> {

    override fun validate(request: GetNFTImageRequestDTO) {
        if (request.imageName.isBlank()) {
            throw IllegalArgumentException("Image name is required.")
        }
    }
}