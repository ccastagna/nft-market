package com.mightyblock.challenge.domain.services.sales

import com.mightyblock.challenge.domain.models.NFT
import com.mightyblock.challenge.domain.models.NFTSale
import com.mightyblock.challenge.domain.models.NFTSaleRequirements
import com.mightyblock.challenge.domain.models.User
import java.math.BigDecimal

interface INFTSaleService {
    fun validateBuyerBudget(buyer: User, amount: BigDecimal)
    fun validateBuyerIsNotNFTOwner(buyer: User, nft: NFT)
    fun executeSaleTransaction(nftSaleRequirements: NFTSaleRequirements): NFTSale

}
