package com.mightyblock.challenge.infrastructure.drivens.repositories.nftcocreators

import com.mightyblock.challenge.domain.repositories.INFTCoCreatorRepositoryService
import com.mightyblock.challenge.infrastructure.drivens.repositorydtos.NFTCoCreatorRepositoryDTO
import java.util.*

class NFTCoCreatorRepositoryService(private val nftCoCreatorRepository: INFTCoCreatorRepository) :
    INFTCoCreatorRepositoryService {
    override fun save(coCreatorId: Int, nftId: UUID) {
        val nftCoCreatorRepositoryDTO = NFTCoCreatorRepositoryDTO(
            userId = coCreatorId,
            nftId = nftId
        )
        nftCoCreatorRepository.save(nftCoCreatorRepositoryDTO)
    }

    override fun getCoCreatorsByNFTId(nftId: UUID): List<Int> {
        return nftCoCreatorRepository.findAllByNftId(nftId).map { it.userId!! }
    }
}