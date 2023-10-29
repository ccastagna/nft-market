package com.mightyblock.challenge.infrastructure.drivers.handlers

import com.mightyblock.challenge.application.models.BuyNFTRequest
import com.mightyblock.challenge.application.usecases.IBuyNFTUseCase
import com.mightyblock.challenge.domain.models.NFTSale
import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.BuyNFTRequestDTO
import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators.IInputRequestValidator
import com.mightyblock.challenge.infrastructure.drivers.dtos.responses.BuyNFTResponseDTO
import org.springframework.http.ResponseEntity


class BuyNFTHandler(
    private val buyNFTRequestDTOValidator: IInputRequestValidator<BuyNFTRequestDTO>,
    private val buyNFTUseCase: IBuyNFTUseCase
) : IRequestHandler<BuyNFTRequestDTO> {
    override fun handle(requestDTO: BuyNFTRequestDTO): ResponseEntity<Any> {

        buyNFTRequestDTOValidator.validate(requestDTO)

        val request = BuyNFTRequest(
            nftId = requestDTO.nftId!!,
            buyerId = requestDTO.buyerId,
            price = requestDTO.amount
        )

        val nftSale: NFTSale = buyNFTUseCase.execute(request)

        val buyNFTResponseDTO = BuyNFTResponseDTO(
            id = nftSale.id,
            nft = BuyNFTResponseDTO.NFT(
                id = nftSale.nftUpdated.id,
                ownerId = nftSale.nftUpdated.ownerId
            ),
            buyer = BuyNFTResponseDTO.User(
                id = nftSale.buyerUpdated.userId,
                balance = nftSale.buyerUpdated.balance
            ),
            oldOwner = BuyNFTResponseDTO.User(
                id = nftSale.oldOwnerUpdated.userId,
                balance = nftSale.oldOwnerUpdated.balance
            ),
            creators = nftSale.creatorsUpdated.map { creator ->
                BuyNFTResponseDTO.User(
                    id = creator.userId,
                    balance = creator.balance
                )
            },
            price = nftSale.price,
            time = nftSale.time
        )

        return ResponseEntity.ok().body(buyNFTResponseDTO)
    }
}