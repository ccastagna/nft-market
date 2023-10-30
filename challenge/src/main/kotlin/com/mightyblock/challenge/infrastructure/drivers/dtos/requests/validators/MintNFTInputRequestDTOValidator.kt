package com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators

import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.MintNFTRequestDTO

class MintNFTInputRequestDTOValidator : IInputRequestValidator<MintNFTRequestDTO> {

    override fun validate(request: MintNFTRequestDTO) {
        if (request.creatorId <= 0) throw IllegalArgumentException("Invalid creatorId: ${request.creatorId}.  CreatorId must be greater than 0.")

        request.coCreators.forEach { coCreatorId ->
            if (coCreatorId <= 0) throw IllegalArgumentException("Invalid coCreatorId: $coCreatorId. CoCreatorId must be greater than 0.")
        }

        if (request.description.isBlank()) throw IllegalArgumentException("Description is required.")

        if (request.imageUrl.isBlank()) throw IllegalArgumentException("ImageUrl is required.")
    }
}