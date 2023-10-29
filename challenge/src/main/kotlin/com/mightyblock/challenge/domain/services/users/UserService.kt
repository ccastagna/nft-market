package com.mightyblock.challenge.domain.services.users

import com.mightyblock.challenge.domain.exceptions.BuyerNotFoundException
import com.mightyblock.challenge.domain.exceptions.CoCreatorNotFoundException
import com.mightyblock.challenge.domain.exceptions.CreatorNotFoundException
import com.mightyblock.challenge.domain.exceptions.OwnerNotFoundException
import com.mightyblock.challenge.domain.models.User
import com.mightyblock.challenge.domain.repositories.IUserRepositoryService

class UserService(private val userRepositoryService: IUserRepositoryService) : IUserService {
    override fun getCreatorById(creatorId: Int): User =
        userRepositoryService.getUserById(creatorId) ?: throw CreatorNotFoundException(creatorId)

    override fun getCoCreatorById(coCreatorId: Int): User =
        userRepositoryService.getUserById(coCreatorId) ?: throw CoCreatorNotFoundException(coCreatorId)

    override fun getBuyerById(buyerId: Int): User =
        userRepositoryService.getUserById(buyerId) ?: throw BuyerNotFoundException(buyerId)

    override fun getOwnerById(ownerId: Int): User =
        userRepositoryService.getUserById(ownerId) ?: throw OwnerNotFoundException(ownerId)

    override fun updateUser(user: User): User =
        userRepositoryService.updateUser(user)
}