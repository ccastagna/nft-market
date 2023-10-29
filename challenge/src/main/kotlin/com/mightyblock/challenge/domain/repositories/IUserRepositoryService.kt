package com.mightyblock.challenge.domain.repositories

import com.mightyblock.challenge.domain.models.User

interface IUserRepositoryService {
    fun getUserById(userId: Int): User?
    fun updateUser(user: User): User
}
