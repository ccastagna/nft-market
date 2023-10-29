package com.mightyblock.challenge.application.models

data class MintNFTRequest(
    val creatorId: Int,
    val description: String,
    val imageUrl: String,
    val coCreators: List<Int>
)