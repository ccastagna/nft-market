package com.mightyblock.challenge.domain.services.sales

import com.mightyblock.challenge.domain.exceptions.BuyerIsNFTOwnerException
import com.mightyblock.challenge.domain.exceptions.NotEnoughBalanceException
import com.mightyblock.challenge.domain.models.NFT
import com.mightyblock.challenge.domain.models.NFTSale
import com.mightyblock.challenge.domain.models.NFTSaleRequirements
import com.mightyblock.challenge.domain.models.User
import com.mightyblock.challenge.domain.repositories.INFTSaleRepositoryService
import java.math.BigDecimal
import java.time.LocalDateTime

class NFTSaleService(private val nftSaleRepositoryService: INFTSaleRepositoryService) : INFTSaleService {

    companion object {
        private val OWNER_PERCENTAGE = BigDecimal(0.8)
        private val CREATORS_PERCENTAGE = BigDecimal(0.2)

        fun creatorPercentage(numberOfCreators: Int) = CREATORS_PERCENTAGE / numberOfCreators.toBigDecimal()
    }

    override fun validateBuyerBudget(buyer: User, amount: BigDecimal) {
        if (buyer.balance < amount) {
            throw NotEnoughBalanceException(buyer.userId, buyer.balance, amount)
        }
    }

    override fun validateBuyerIsNotNFTOwner(buyer: User, nft: NFT) {
        if (buyer.userId == nft.ownerId) {
            throw BuyerIsNFTOwnerException(buyer.userId, nft.id)
        }
    }

    override fun executeSaleTransaction(nftSaleRequirements: NFTSaleRequirements): NFTSale {
        val buyer: User = nftSaleRequirements.buyer
        val creators: List<User> = nftSaleRequirements.creators
        val owner: User = nftSaleRequirements.owner
        val nft: NFT = nftSaleRequirements.nft
        val price: BigDecimal = nftSaleRequirements.price

        //balance operations
        val buyerUpdated: User = buyer.copy(balance = collectNFTPriceFromBuyer(buyer, price))

        var oldOwnerUpdated: User = owner.copy(balance = payOwnerShare(owner, price))

        val creatorsUpdated: List<User> = creators.map { creator ->
            if (creatorIsOldOwner(creator, owner)) {
                val oldOwner = creator.copy(balance = payCreatorShare(oldOwnerUpdated, price, creators.size))
                oldOwnerUpdated = oldOwner
                oldOwner
            } else {
                creator.copy(balance = payCreatorShare(creator, price, creators.size))
            }
        }

        //update nft ownership
        val nftUpdated: NFT = nft.copy(ownerId = buyer.userId)

        val nftSale = NFTSale(
            buyerUpdated = buyerUpdated,
            creatorsUpdated = creatorsUpdated,
            oldOwnerUpdated = oldOwnerUpdated,
            nftUpdated = nftUpdated,
            price = price,
            time = LocalDateTime.now()
        )

        return nftSaleRepositoryService.save(nftSale)
    }

    private fun creatorIsOldOwner(creator: User, owner: User): Boolean = creator.userId == owner.userId

    private fun payCreatorShare(
        creator: User,
        price: BigDecimal,
        numberOfCreators: Int
    ) = creator.balance + (price * creatorPercentage(numberOfCreators))

    private fun collectNFTPriceFromBuyer(
        buyer: User,
        price: BigDecimal
    ) = buyer.balance - price

    private fun payOwnerShare(
        owner: User,
        price: BigDecimal
    ) = owner.balance + (price * OWNER_PERCENTAGE)

}