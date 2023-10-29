package com.mightyblock.challenge.domain.repositories

import java.util.*

interface INFTCoCreatorRepositoryService {
    fun save(coCreator: Int, savedNFT: UUID)
    fun getCoCreatorsByNFTId(nftId: UUID): List<Int>
}
