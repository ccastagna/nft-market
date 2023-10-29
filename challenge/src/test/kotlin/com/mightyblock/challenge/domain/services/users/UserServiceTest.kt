package com.mightyblock.challenge.domain.services.users

import com.mightyblock.challenge.domain.exceptions.BuyerNotFoundException
import com.mightyblock.challenge.domain.exceptions.CoCreatorNotFoundException
import com.mightyblock.challenge.domain.exceptions.CreatorNotFoundException
import com.mightyblock.challenge.domain.exceptions.OwnerNotFoundException
import com.mightyblock.challenge.domain.models.User
import com.mightyblock.challenge.infrastructure.drivens.repositories.users.UserRepositoryService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import java.math.BigDecimal

internal class UserServiceTest {

    private val userIdMock = 1

    private val userMock = User(
        userId = userIdMock,
        balance = BigDecimal(100),
    )

    private val userRepositoryService: UserRepositoryService = mock()

    private val userService = UserService(userRepositoryService)

    @Test
    fun `Given non existent userId, when getCreatorById, then throw CreatorNotfoundException`() {
        //Given
        mockGetUserIdReturnNull()

        // When & Then
        assertThrows<CreatorNotFoundException> {
            userService.getCreatorById(userIdMock)
        }.apply {
            Assertions.assertEquals(
                "Creator with id $userIdMock not found", message
            )
        }
    }

    @Test
    fun `Given existent userId, when getCreatorById, then return user`() {
        // Given
        mockGetUserIdReturnUser()

        // When & Then
        userService.getCreatorById(userIdMock).apply {
            Assertions.assertEquals(
                userIdMock, userId
            )
        }
    }

    @Test
    fun `Given non existent userId, when getCoCreatorById, then throw CoCreatorNotfoundException`() {
        // Given
        mockGetUserIdReturnNull()

        // When & Then
        assertThrows<CoCreatorNotFoundException> {
            userService.getCoCreatorById(userIdMock)
        }.apply {
            Assertions.assertEquals(
                "Co-creator with id $userIdMock not found", message
            )
        }
    }

    @Test
    fun `Given existent userId, when getCoCreatorById, then return user`() {
        // Given
        mockGetUserIdReturnUser()

        // When & Then
        userService.getCoCreatorById(userIdMock).apply {
            Assertions.assertEquals(
                userIdMock, userId
            )
        }
    }

    @Test
    fun `Given non existent userId, when getBuyerById, then throw BuyerNotfoundException`() {
        // Given
        mockGetUserIdReturnNull()

        // When & Then
        assertThrows<BuyerNotFoundException> {
            userService.getBuyerById(userIdMock)
        }.apply {
            Assertions.assertEquals(
                "Buyer with id $userIdMock not found", message
            )
        }
    }

    @Test
    fun `Given existent userId, when getBuyerById, then return user`() {
        // Given
        mockGetUserIdReturnUser()

        // When & Then
        userService.getBuyerById(userIdMock).apply {
            Assertions.assertEquals(
                userIdMock, userId
            )
        }
    }

    @Test
    fun `Given non existent userId, when getOwnerById, then throw OwnerNotFoundException`() {
        // Given
        mockGetUserIdReturnNull()

        // When & Then
        assertThrows<OwnerNotFoundException> {
            userService.getOwnerById(userIdMock)
        }.apply {
            Assertions.assertEquals(
                "Owner with id $userIdMock not found", message
            )
        }
    }

    @Test
    fun `Given existent userId, when getOwnerById, then return user`() {
        // Given
        mockGetUserIdReturnUser()

        // When & Then
        userService.getOwnerById(userIdMock).apply {
            Assertions.assertEquals(
                userIdMock, userId
            )
        }
    }

    @Test
    fun `Given a valid user, when updateUser, then return user updated`() {
        // Given
        doReturn(userMock).`when`(userRepositoryService).updateUser(userMock)

        // When & Then
        userService.updateUser(userMock).apply {
            Assertions.assertEquals(
                userIdMock, userId
            )
        }
    }

    private fun mockGetUserIdReturnNull() {
        doReturn(null).`when`(userRepositoryService).getUserById(anyInt())
    }

    private fun mockGetUserIdReturnUser() {
        doReturn(userMock).`when`(userRepositoryService).getUserById(anyInt())
    }
}