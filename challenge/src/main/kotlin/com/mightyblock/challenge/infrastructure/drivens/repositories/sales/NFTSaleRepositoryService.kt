package com.mightyblock.challenge.infrastructure.drivens.repositories.sales

import com.mightyblock.challenge.domain.models.NFTSale
import com.mightyblock.challenge.domain.repositories.INFTSaleRepositoryService
import com.mightyblock.challenge.infrastructure.drivens.repositorydtos.NFTSaleRepositoryDTO

class NFTSaleRepositoryService(private val nftSaleRepository: INFTSaleRepository) : INFTSaleRepositoryService {
    override fun save(nftSale: NFTSale): NFTSale {
        val nftSaleRepositoryDTO = NFTSaleRepositoryDTO(
            nftId = nftSale.nftUpdated.id,
            buyerId = nftSale.buyerUpdated.userId,
            price = nftSale.price,
            saleTime = nftSale.time
        )

        return nftSaleRepository.save(nftSaleRepositoryDTO).let {
            nftSale.copy(id = it.saleId!!)
        }
    }
}