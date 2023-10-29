package com.mightyblock.challenge.infrastructure.drivens.repositorydtos

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "nft_co_creators")
@IdClass(NFTCoCreatorId::class)
data class NFTCoCreatorRepositoryDTO(
    @Id
    @Column(name = "nft_id", nullable = false)
    val nftId: UUID? = null,

    @Id
    @Column(name = "user_id", nullable = false)
    val userId: Int? = null
)

data class NFTCoCreatorId(val nftId: UUID? = null, val userId: Int? = null) : java.io.Serializable