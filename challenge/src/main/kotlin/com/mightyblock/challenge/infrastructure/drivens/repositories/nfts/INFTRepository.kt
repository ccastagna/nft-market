package com.mightyblock.challenge.infrastructure.drivens.repositories.nfts

import com.mightyblock.challenge.infrastructure.drivens.repositorydtos.NFTRepositoryDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface INFTRepository : JpaRepository<NFTRepositoryDTO, UUID>
