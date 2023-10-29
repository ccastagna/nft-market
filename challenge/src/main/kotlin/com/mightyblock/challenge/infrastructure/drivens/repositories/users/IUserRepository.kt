package com.mightyblock.challenge.infrastructure.drivens.repositories.users

import com.mightyblock.challenge.infrastructure.drivens.repositorydtos.UserRepositoryDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IUserRepository : JpaRepository<UserRepositoryDTO, Int>
