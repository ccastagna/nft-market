package com.mightyblock.challenge.domain.services.nfts

import com.mightyblock.challenge.domain.models.NFT
import com.mightyblock.challenge.domain.models.PageableNFTList
import java.util.*

interface INFTService {
    fun mint(creatorId: Int, coCreators: List<Int>, imageUrl: String, description: String): NFT
    fun getAllNFTsPaginated(page: Int, size: Int): PageableNFTList
    fun getNFTById(nftId: UUID): NFT
    fun updateNFT(nftUpdated: NFT): NFT
}
