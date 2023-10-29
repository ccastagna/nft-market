package com.mightyblock.challenge.infrastructure.drivens.repositorydtos

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "nft_sales")
data class NFTSaleRepositoryDTO(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val saleId: Int? = null,

    @Column(name = "nft_id", nullable = false)
    val nftId: UUID? = null,

    @Column(name = "buyer_id", nullable = false)
    val buyerId: Int? = null,

    @Column(nullable = false, precision = 10, scale = 2)
    val price: BigDecimal? = null,

    @Column(name = "sale_time", nullable = false)
    val saleTime: LocalDateTime? = null
)