package com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators

import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.UploadNFTImageRequestDTO

class UploadNFTImageInputRequestDTOValidator : IInputRequestValidator<UploadNFTImageRequestDTO> {

    companion object {
        val ALLOWED_CONTENT_TYPES = listOf(
            "image/jpeg",
            "image/png"
        )

        val ALLOWED_EXTENSIONS = listOf(
            "jpg",
            "jpeg",
            "png"
        )
    }

    override fun validate(request: UploadNFTImageRequestDTO) {

        val contentType = request.image.contentType

        if (!hasImageContentType(contentType)) {
            throw IllegalArgumentException("Image content type $contentType is not allowed")
        }

        val extension = getImageExtension(request)

        if (!hasImageExtension(extension)) {
            throw IllegalArgumentException("Image extension $extension is not allowed")
        }

    }

    private fun hasImageExtension(extension: String?) = ALLOWED_EXTENSIONS.contains(extension)

    private fun hasImageContentType(contentType: String?) =
        ALLOWED_CONTENT_TYPES.contains(contentType)

    private fun getImageExtension(request: UploadNFTImageRequestDTO) =
        request.image.originalFilename?.substringAfterLast('.', "")
}