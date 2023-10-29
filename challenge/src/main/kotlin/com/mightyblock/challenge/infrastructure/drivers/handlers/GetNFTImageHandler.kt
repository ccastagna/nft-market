package com.mightyblock.challenge.infrastructure.drivers.handlers

import com.mightyblock.challenge.application.usecases.IGetNFTImageUseCase
import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.GetNFTImageRequestDTO
import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators.IInputRequestValidator
import org.apache.tika.Tika
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

class GetNFTImageHandler(
    private val getNFTImageRequestDTOValidator: IInputRequestValidator<GetNFTImageRequestDTO>,
    private val getNFTImageUseCase: IGetNFTImageUseCase
) : IRequestHandler<GetNFTImageRequestDTO> {
    override fun handle(request: GetNFTImageRequestDTO): ResponseEntity<Any> {

        getNFTImageRequestDTOValidator.validate(request)

        val image: ByteArray = getNFTImageUseCase.execute(request.imageName)

        val detectedType: String = Tika().detect(image)

        val mediaType = MediaType.parseMediaType(detectedType)

        return ResponseEntity.ok().contentType(mediaType).body(image)

    }
}