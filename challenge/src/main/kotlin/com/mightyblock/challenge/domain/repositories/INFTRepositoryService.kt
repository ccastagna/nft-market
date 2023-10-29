package com.mightyblock.challenge.domain.repositories

import com.mightyblock.challenge.domain.models.NFT
import com.mightyblock.challenge.domain.models.PageableNFTList
import java.util.*

interface INFTRepositoryService {
    fun saveNFT(nft: NFT): NFT
    fun getAllNFTsPaginated(page: Int, size: Int): PageableNFTList
    fun getNFTById(nftId: UUID): NFT?

}
