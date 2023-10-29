package com.mightyblock.challenge.infrastructure.drivens.repositories.sales

import com.mightyblock.challenge.infrastructure.drivens.repositorydtos.NFTSaleRepositoryDTO
import org.springframework.data.jpa.repository.JpaRepository

interface INFTSaleRepository: JpaRepository<NFTSaleRepositoryDTO, Int> {
}