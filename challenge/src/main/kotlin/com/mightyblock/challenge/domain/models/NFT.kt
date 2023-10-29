package com.mightyblock.challenge.domain.models

import java.time.LocalDateTime
import java.util.UUID

data class NFT(
    val id: UUID = UUID.randomUUID(),
    val imageUrl: String,
    val description: String,
    val uploadTime: LocalDateTime,
    val creatorId: Int,
    val coCreators: List<Int> = emptyList(),
    val ownerId: Int
)
