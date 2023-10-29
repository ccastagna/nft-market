package com.mightyblock.challenge.infrastructure.drivers.handlers

import com.mightyblock.challenge.application.usecases.IUploadNFTImageUseCase
import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.UploadNFTImageRequestDTO
import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators.IInputRequestValidator
import com.mightyblock.challenge.infrastructure.drivers.dtos.responses.UploadNFTImageResponseDTO
import org.springframework.http.ResponseEntity

class UploadNFTImageHandler(
    private val uploadNFTImageRequestDTOValidator: IInputRequestValidator<UploadNFTImageRequestDTO>,
    private val uploadNFTImageUseCase: IUploadNFTImageUseCase
) : IRequestHandler<UploadNFTImageRequestDTO> {
    override fun handle(request: UploadNFTImageRequestDTO): ResponseEntity<Any> {

        uploadNFTImageRequestDTOValidator.validate(request)

        val imageUrl: String = uploadNFTImageUseCase.execute(request.image)

        val uploadNFTImageResponseDTO = UploadNFTImageResponseDTO(
            imageUrl = imageUrl
        )

        return ResponseEntity.ok().body(uploadNFTImageResponseDTO)

    }
}