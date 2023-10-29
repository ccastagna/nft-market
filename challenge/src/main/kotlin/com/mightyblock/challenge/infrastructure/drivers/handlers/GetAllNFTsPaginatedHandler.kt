package com.mightyblock.challenge.infrastructure.drivers.handlers

import com.mightyblock.challenge.application.models.GetAllNFTsPaginatedRequest
import com.mightyblock.challenge.application.usecases.IGetAllNFTsPaginatedUseCase
import com.mightyblock.challenge.domain.models.PageableNFTList
import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.GetAllNFTsPaginatedRequestDTO
import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators.IInputRequestValidator
import com.mightyblock.challenge.infrastructure.drivers.dtos.responses.GetAllNFTsPaginatedResponseDTO
import org.springframework.http.ResponseEntity

class GetAllNFTsPaginatedHandler(
    private val getAllNFTsPaginatedRequestDTOValidator: IInputRequestValidator<GetAllNFTsPaginatedRequestDTO>,
    private val getAllNFTsPaginatedUseCase: IGetAllNFTsPaginatedUseCase
) : IRequestHandler<GetAllNFTsPaginatedRequestDTO> {
    override fun handle(requestDTO: GetAllNFTsPaginatedRequestDTO): ResponseEntity<Any> {

        getAllNFTsPaginatedRequestDTOValidator.validate(requestDTO)

        val request = GetAllNFTsPaginatedRequest(
            page = requestDTO.page,
            size = requestDTO.size
        )

        val pageableNFTList: PageableNFTList = getAllNFTsPaginatedUseCase.execute(request)

        val getAllNFTsPaginatedResponseDTO = GetAllNFTsPaginatedResponseDTO(
            nftList = pageableNFTList.nftList.map { nft ->
                GetAllNFTsPaginatedResponseDTO.NFT(
                    nftId = nft.id,
                    imageUrl = nft.imageUrl,
                    description = nft.description
                )
            }
        )

        return ResponseEntity.ok().body(getAllNFTsPaginatedResponseDTO)
    }
}