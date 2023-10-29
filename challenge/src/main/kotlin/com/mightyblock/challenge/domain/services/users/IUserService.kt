package com.mightyblock.challenge.domain.services.users

import com.mightyblock.challenge.domain.models.User

interface IUserService {
    fun getCreatorById(creatorId: Int): User
    fun getCoCreatorById(coCreatorId: Int): User
    fun getBuyerById(buyerId: Int): User
    fun getOwnerById(ownerId: Int): User
    fun updateUser(user: User): User
}
