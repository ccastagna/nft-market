package com.mightyblock.challenge.infrastructure.drivens.repositorydtos

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "nfts")
data class NFTRepositoryDTO(
    @Id
    val nftId: UUID? = null,

    @Column(name = "image_url", nullable = false, length = 255)
    val imageUrl: String? = null,

    @Column(nullable = false)
    val description: String? = null,

    @Column(name = "upload_time", nullable = false)
    val uploadTime: LocalDateTime? = null,

    @Column(name = "creator_id", nullable = false)
    val creatorId: Int? = null,

    @Column(name = "owner_id", nullable = false)
    val ownerId: Int? = null,
)