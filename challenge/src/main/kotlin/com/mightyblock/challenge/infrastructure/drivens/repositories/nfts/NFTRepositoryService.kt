package com.mightyblock.challenge.infrastructure.drivens.repositories.nfts

import com.mightyblock.challenge.domain.models.NFT
import com.mightyblock.challenge.domain.models.PageableNFTList
import com.mightyblock.challenge.domain.repositories.INFTRepositoryService
import com.mightyblock.challenge.infrastructure.drivens.repositorydtos.NFTRepositoryDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import java.util.*

class NFTRepositoryService(private val nftRepository: INFTRepository) : INFTRepositoryService {

    override fun saveNFT(nft: NFT): NFT {

        val nftRepositoryDTO = NFTRepositoryDTO(
            nftId = nft.id,
            creatorId = nft.creatorId,
            imageUrl = nft.imageUrl,
            description = nft.description,
            uploadTime = nft.uploadTime,
            ownerId = nft.ownerId,
        )
        return nftRepository.save(nftRepositoryDTO).let { nft }
    }

    override fun getAllNFTsPaginated(page: Int, size: Int): PageableNFTList {
        val sort: Sort = Sort.by(Sort.Order.desc("uploadTime"))
        // Pageable is 0-indexed
        val pageable: Pageable = PageRequest.of(page - 1, size, sort)

        val nftPage: Page<NFTRepositoryDTO> = nftRepository.findAll(pageable)

        return PageableNFTList(
            nftList = nftPage.map { nftRepositoryDTO ->
                NFT(
                    id = nftRepositoryDTO.nftId!!,
                    creatorId = nftRepositoryDTO.creatorId!!,
                    imageUrl = nftRepositoryDTO.imageUrl!!,
                    description = nftRepositoryDTO.description!!,
                    uploadTime = nftRepositoryDTO.uploadTime!!,
                    ownerId = nftRepositoryDTO.ownerId!!
                )
            }.toList(),
            page = com.mightyblock.challenge.domain.models.Page(
                number = nftPage.number + 1, // Pageable is 0-indexed
                size = nftPage.size,
                totalPages = nftPage.totalPages,
                totalElements = nftPage.totalElements
            )
        )
    }

    override fun getNFTById(nftId: UUID): NFT? {
        return nftRepository.findById(nftId).let { optionalNFT ->
            if (optionalNFT.isPresent) {
                NFT(
                    id = optionalNFT.get().nftId!!,
                    creatorId = optionalNFT.get().creatorId!!,
                    imageUrl = optionalNFT.get().imageUrl!!,
                    description = optionalNFT.get().description!!,
                    uploadTime = optionalNFT.get().uploadTime!!,
                    ownerId = optionalNFT.get().ownerId!!
                )
            } else null
        }
    }
}