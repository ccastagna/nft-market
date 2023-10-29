package com.mightyblock.challenge.infrastructure.drivers.handlers

import com.mightyblock.challenge.application.usecases.IMintNFTUseCase
import com.mightyblock.challenge.application.models.MintNFTRequest
import com.mightyblock.challenge.domain.models.NFT
import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.MintNFTRequestDTO
import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators.IInputRequestValidator
import com.mightyblock.challenge.infrastructure.drivers.dtos.responses.MintNFTResponseDTO
import org.owasp.encoder.Encode
import org.springframework.http.ResponseEntity


class MintNFTHandler(
    private val mintNFTRequestDTOValidator: IInputRequestValidator<MintNFTRequestDTO>,
    private val mintNFTUseCase: IMintNFTUseCase
) : IRequestHandler<MintNFTRequestDTO> {
    override fun handle(requestDTO: MintNFTRequestDTO): ResponseEntity<Any> {

        mintNFTRequestDTOValidator.validate(requestDTO)

        val request = MintNFTRequest(
            creatorId = requestDTO.creatorId,
            coCreators = requestDTO.coCreators,
            description = Encode.forHtmlContent(requestDTO.description),
            imageUrl = requestDTO.imageUrl
        )

        val mintedNFT: NFT = mintNFTUseCase.execute(request)

        val mintNFTResponseDTO = MintNFTResponseDTO(
            nftId = mintedNFT.id,
            imageUrl = mintedNFT.imageUrl,
            description = mintedNFT.description
        )

        return ResponseEntity.ok().body(mintNFTResponseDTO)
    }
}