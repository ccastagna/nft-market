package com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators

import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.GetNFTImageRequestDTO

class GetNFTImageInputRequestDTOValidator : IInputRequestValidator<GetNFTImageRequestDTO> {

    companion object {
        val ALLOWED_EXTENSIONS = listOf(
            "jpg",
            "jpeg",
            "png"
        )
    }

    override fun validate(request: GetNFTImageRequestDTO) {
        if (request.imageName.isBlank()) {
            throw IllegalArgumentException("Image name is required.")
        }

        val extension = getImageExtension(request.imageName)

        if (!hasImageExtension(extension)) {
            throw IllegalArgumentException("Image extension $extension is not allowed")
        }
    }

    private fun hasImageExtension(extension: String?) = ALLOWED_EXTENSIONS.contains(extension)

    private fun getImageExtension(imageName: String) =
        imageName.substringAfterLast('.', "")
}