package com.mightyblock.challenge.infrastructure.drivens.repositories.users

import com.mightyblock.challenge.domain.models.User
import com.mightyblock.challenge.domain.repositories.IUserRepositoryService
import com.mightyblock.challenge.infrastructure.drivens.repositorydtos.UserRepositoryDTO

class UserRepositoryService(private val userRepository: IUserRepository) : IUserRepositoryService {
    override fun getUserById(userId: Int): User? = userRepository.findById(userId).let { optionalUser ->
        if (optionalUser.isPresent) {
            User(
                userId = optionalUser.get().userId!!,
                balance = optionalUser.get().balance!!,
            )
        } else null
    }

    override fun updateUser(user: User): User {
        val userRepositoryDTO = UserRepositoryDTO(
            userId = user.userId,
            balance = user.balance,
        )
        return userRepository.save(userRepositoryDTO).let { savedUser ->
            User(
                userId = savedUser.userId!!,
                balance = savedUser.balance!!,
            )
        }
    }
}