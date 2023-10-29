package com.mightyblock.challenge.infrastructure.drivens.repositories.nftcocreators

import com.mightyblock.challenge.infrastructure.drivens.repositorydtos.NFTCoCreatorRepositoryDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface INFTCoCreatorRepository : JpaRepository<NFTCoCreatorRepositoryDTO, Int> {
    fun findAllByNftId(nftId: UUID): List<NFTCoCreatorRepositoryDTO>
}