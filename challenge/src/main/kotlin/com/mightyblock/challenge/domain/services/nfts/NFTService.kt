package com.mightyblock.challenge.domain.services.nfts

import com.mightyblock.challenge.domain.exceptions.NFTNotFoundException
import com.mightyblock.challenge.domain.models.NFT
import com.mightyblock.challenge.domain.models.PageableNFTList
import com.mightyblock.challenge.domain.repositories.INFTCoCreatorRepositoryService
import com.mightyblock.challenge.domain.repositories.INFTRepositoryService
import java.time.LocalDateTime
import java.util.*

class NFTService(
    private val nftRepositoryService: INFTRepositoryService,
    private val nftCoCreatorRepositoryService: INFTCoCreatorRepositoryService
) : INFTService {
    override fun mint(creatorId: Int, coCreators: List<Int>, imageUrl: String, description: String): NFT {
        val nft = NFT(
            creatorId = creatorId,
            coCreators = coCreators,
            imageUrl = imageUrl,
            description = description,
            uploadTime = LocalDateTime.now(),
            ownerId = creatorId
        )

        val savedNFT = nftRepositoryService.saveNFT(nft)

        coCreators.forEach { coCreatorId ->
            nftCoCreatorRepositoryService.save(coCreatorId, savedNFT.id)
        }

        return savedNFT
    }

    override fun getAllNFTsPaginated(page: Int, size: Int): PageableNFTList {
        return nftRepositoryService.getAllNFTsPaginated(page, size)
    }

    override fun getNFTById(nftId: UUID): NFT {
        val nft = nftRepositoryService.getNFTById(nftId) ?: throw NFTNotFoundException(nftId)

        val coCreators: List<Int> = nftCoCreatorRepositoryService.getCoCreatorsByNFTId(nftId)

        return nft.copy(coCreators = coCreators)
    }

    override fun updateNFT(nftUpdated: NFT): NFT {
        return nftRepositoryService.saveNFT(nftUpdated)
    }
}