package com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators

import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.MintNFTRequestDTO

class MintNFTInputRequestDTOValidator : IInputRequestValidator<MintNFTRequestDTO> {

    override fun validate(request: MintNFTRequestDTO) {
        if (request.creatorId <= 0) throw IllegalArgumentException("Invalid creatorId: ${request.creatorId}.")

        request.coCreators.forEach { coCreatorId ->
            if (coCreatorId <= 0) throw IllegalArgumentException("Invalid coCreatorId: $coCreatorId.")
        }

        if (request.description.isBlank()) throw IllegalArgumentException("Description is required.")

        if (request.imageUrl.isBlank()) throw IllegalArgumentException("ImageUrl is required.")
    }
}