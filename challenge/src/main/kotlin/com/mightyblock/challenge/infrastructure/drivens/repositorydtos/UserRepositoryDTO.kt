package com.mightyblock.challenge.infrastructure.drivens.repositorydtos

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "users")
class UserRepositoryDTO(
    @Id
    val userId: Int? = null,

    @Column(nullable = false, precision = 10, scale = 2)
    val balance: BigDecimal? = null,
)
