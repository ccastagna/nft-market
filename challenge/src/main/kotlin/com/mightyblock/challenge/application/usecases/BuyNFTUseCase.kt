package com.mightyblock.challenge.application.usecases

import com.mightyblock.challenge.application.models.BuyNFTRequest
import com.mightyblock.challenge.domain.models.NFT
import com.mightyblock.challenge.domain.models.NFTSale
import com.mightyblock.challenge.domain.models.NFTSaleRequirements
import com.mightyblock.challenge.domain.models.User
import com.mightyblock.challenge.domain.services.nfts.INFTService
import com.mightyblock.challenge.domain.services.sales.INFTSaleService
import com.mightyblock.challenge.domain.services.users.IUserService

class BuyNFTUseCase(
    private val userService: IUserService,
    private val nftSaleService: INFTSaleService,
    private val nftService: INFTService
) : IBuyNFTUseCase {
    override fun execute(request: BuyNFTRequest): NFTSale {
// TODO: block resources: buyer balance and nft
// TODO: get and update resources in parallel or batch
        val nftSaleRequirements: NFTSaleRequirements = getNFTSaleRequirements(request)

        val nftSale: NFTSale = nftSaleService.executeSaleTransaction(nftSaleRequirements)

        nftService.updateNFT(nftSale.nftUpdated)

        updateUsers(nftSale)

        return nftSale
    }

    private fun getNFTSaleRequirements(request: BuyNFTRequest): NFTSaleRequirements {
        val buyer: User = userService.getBuyerById(request.buyerId)

        nftSaleService.validateBuyerBudget(buyer, request.price)

        val nft: NFT = nftService.getNFTById(request.nftId)

        nftSaleService.validateBuyerIsNotNFTOwner(buyer, nft)

        val creator: User = userService.getCreatorById(nft.creatorId)

        val owner: User = if (creator.userId == nft.ownerId) {
            creator
        } else {
            userService.getOwnerById(nft.ownerId)
        }

        val coCreators: List<User> = nft.coCreators
            .map { coCreatorId ->
                userService.getCoCreatorById(coCreatorId)
            }

        return NFTSaleRequirements(
            buyer = buyer,
            creators = listOf(creator) + coCreators,
            owner = owner,
            nft = nft,
            price = request.price
        )
    }

    private fun updateUsers(nftSale: NFTSale) {
        userService.updateUser(nftSale.buyerUpdated)

        nftSale.creatorsUpdated.forEach { creator ->
            userService.updateUser(creator)
        }

        if (nftSale.nftUpdated.creatorId != nftSale.oldOwnerUpdated.userId) {
            userService.updateUser(nftSale.oldOwnerUpdated)
        }
    }
}