package com.mightyblock.challenge.domain.repositories

import com.mightyblock.challenge.domain.models.NFTSale

interface INFTSaleRepositoryService {
    fun save(nftSale: NFTSale): NFTSale

}
