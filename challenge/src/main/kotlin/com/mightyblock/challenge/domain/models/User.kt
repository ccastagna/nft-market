package com.mightyblock.challenge.domain.models

import java.math.BigDecimal

data class User(
    val userId: Int,
    val balance: BigDecimal
)