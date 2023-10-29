package com.mightyblock.challenge.integrations

import com.fasterxml.jackson.databind.ObjectMapper
import com.mightyblock.challenge.infrastructure.drivens.repositories.nftcocreators.INFTCoCreatorRepository
import com.mightyblock.challenge.infrastructure.drivens.repositories.nfts.INFTRepository
import com.mightyblock.challenge.infrastructure.drivens.repositories.sales.INFTSaleRepository
import com.mightyblock.challenge.infrastructure.drivens.repositories.users.IUserRepository
import com.mightyblock.challenge.infrastructure.drivens.repositorydtos.NFTCoCreatorRepositoryDTO
import com.mightyblock.challenge.infrastructure.drivens.repositorydtos.NFTRepositoryDTO
import com.mightyblock.challenge.infrastructure.drivens.repositorydtos.UserRepositoryDTO
import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.BuyNFTRequestDTO
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import java.util.function.Consumer

@SpringBootTest
@AutoConfigureMockMvc
open class IntegrationTestBase {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var nftRepository: INFTRepository

    @Autowired
    lateinit var nftSaleRepository: INFTSaleRepository

    @Autowired
    lateinit var userRepository: IUserRepository

    @Autowired
    lateinit var nftCoCreatorRepository: INFTCoCreatorRepository

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @AfterEach
    fun cleanup() {
        nftRepository.deleteAll()
        nftSaleRepository.deleteAll()
        userRepository.deleteAll()
        nftCoCreatorRepository.deleteAll()
    }


    protected fun givenRequestBodyWith(buyerId: Int, amount: BigDecimal): String {
        val requestBody = BuyNFTRequestDTO(
            buyerId = buyerId,
            amount = amount
        ).let {
            objectMapper.writeValueAsString(it)
        } ?: throw RuntimeException("Error parsing request body")
        return requestBody
    }

    protected fun whenPerformAPICall(endpoint: MockHttpServletRequestBuilder, requestBody: String) =
        mockMvc.perform(endpoint.contentType(MediaType.APPLICATION_JSON).content(requestBody))

    protected fun thenExpects(result: ResultActions, resultMatchers: List<ResultMatcher?>) {
        resultMatchers.forEach(Consumer { resultMatcher: ResultMatcher? ->
            try {
                result.andExpect(resultMatcher!!)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        })
    }

    protected fun givenUserWith(userId: Int, balance: BigDecimal): UserRepositoryDTO {
        val userSaved = userRepository.save(
            UserRepositoryDTO(
                userId = userId,
                balance = balance
            )
        )

        Assertions.assertNotNull(userSaved)

        return userSaved
    }

    protected fun givenNFTWith(creatorId: Int = 1, ownerId: Int = 1): NFTRepositoryDTO {
        val nftSaved = nftRepository.save(
            NFTRepositoryDTO(
                nftId = UUID.randomUUID(),
                imageUrl = "https://www.example.com/image.png",
                description = "NFT description",
                uploadTime = LocalDateTime.now(),
                creatorId = creatorId,
                ownerId = ownerId
            )
        )

        Assertions.assertNotNull(nftSaved)

        return nftSaved
    }

    protected fun givenNFTCoCreator(nftId: UUID, userId: Int) {
        val nftCoCreatorSaved = nftCoCreatorRepository.save(
            NFTCoCreatorRepositoryDTO(
                nftId = nftId,
                userId = userId
            )
        )
        Assertions.assertNotNull(nftCoCreatorSaved)
    }
}
