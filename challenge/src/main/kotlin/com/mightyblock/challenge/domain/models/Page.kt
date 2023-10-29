package com.mightyblock.challenge.domain.models

data class Page(
    val number: Int,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int
)
