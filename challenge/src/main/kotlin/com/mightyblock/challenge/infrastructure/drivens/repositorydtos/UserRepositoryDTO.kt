package com.mightyblock.challenge.infrastructure.drivens.repositorydtos

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "users")
class UserRepositoryDTO(
    @Id
    val userId: Int? = null,

    @Column(nullable = false, precision = 10, scale = 2)
    val balance: BigDecimal? = null,
)
