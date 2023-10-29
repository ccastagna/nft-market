package com.mightyblock.challenge.integrations

import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class BuyNFTIntegrationTests : IntegrationTestBase() {


    companion object {
        private const val BUY_NFT_TEMPLATE_ENDPOINT = "/api/nfts/{nftId}/buy"
        private const val USER_TWO = 2
        private const val USER_ONE = 1
        private const val ZERO_USER = 0
        private const val NEGATIVE_USER = -1
        private val ONE_DOLLAR = BigDecimal.ONE
        private val NEGATIVE_PRICE = BigDecimal(-1)
        private val NFT_ID = UUID.randomUUID().toString()

    }

    @Test
    fun `Given null nftId, when perform buy nft api call, then a 500 status is returned`() {
        // Given
        val requestBody = givenRequestBodyWith(USER_TWO, ONE_DOLLAR)

        // When
        val response = whenPerformAPICall(post(BUY_NFT_TEMPLATE_ENDPOINT, " "), requestBody)

        // Then
        thenExpects(
            response, listOf(
                MockMvcResultMatchers.status().isInternalServerError
            )
        )
    }

    @Test
    fun `Given zero buyerId, when perform buy nft api call, then a 400 status is returned`() {
        // Given
        val requestBody = givenRequestBodyWith(ZERO_USER, ONE_DOLLAR)

        // When
        val response = whenPerformAPICall(post(BUY_NFT_TEMPLATE_ENDPOINT, NFT_ID), requestBody)

        // Then
        thenExpects(
            response, listOf(
                MockMvcResultMatchers.status().isBadRequest,
                MockMvcResultMatchers.jsonPath("$").value("Invalid buyerId: $ZERO_USER.")
            )
        )
    }

    @Test
    fun `Given negative buyerId, when perform buy nft api call, then a 400 status is returned`() {
        // Given
        val requestBody = givenRequestBodyWith(NEGATIVE_USER, ONE_DOLLAR)

        // When
        val response = whenPerformAPICall(post(BUY_NFT_TEMPLATE_ENDPOINT, NFT_ID), requestBody)

        // Then
        thenExpects(
            response, listOf(
                MockMvcResultMatchers.status().isBadRequest,
                MockMvcResultMatchers.jsonPath("$").value("Invalid buyerId: $NEGATIVE_USER.")
            )
        )
    }

    @Test
    fun `Given zero amount, when perform buy nft api call, then a 400 status is returned`() {
        // Given
        val requestBody = givenRequestBodyWith(USER_TWO, BigDecimal.ZERO)

        // When
        val response = whenPerformAPICall(post(BUY_NFT_TEMPLATE_ENDPOINT, NFT_ID), requestBody)

        // Then
        thenExpects(
            response, listOf(
                MockMvcResultMatchers.status().isBadRequest,
                MockMvcResultMatchers.jsonPath("$").value("Invalid NFT price: ${BigDecimal.ZERO}.")
            )
        )
    }

    @Test
    fun `Given negative amount, when perform buy nft api call, then a 400 status is returned`() {
        // Given
        val requestBody = givenRequestBodyWith(USER_TWO, NEGATIVE_PRICE)

        // When
        val response = whenPerformAPICall(post(BUY_NFT_TEMPLATE_ENDPOINT, NFT_ID), requestBody)

        // Then
        thenExpects(
            response, listOf(
                MockMvcResultMatchers.status().isBadRequest,
                MockMvcResultMatchers.jsonPath("$").value("Invalid NFT price: $NEGATIVE_PRICE.")
            )
        )
    }

    @Test
    fun `Given a buyerId not present in the database, when perform buy nft api call, then a 422 status is returned`() {
        // Given
        val requestBody = givenRequestBodyWith(USER_TWO, ONE_DOLLAR)

        // When
        val response = whenPerformAPICall(post(BUY_NFT_TEMPLATE_ENDPOINT, NFT_ID), requestBody)

        // Then
        thenExpects(
            response, listOf(
                MockMvcResultMatchers.status().isUnprocessableEntity,
                MockMvcResultMatchers.jsonPath("$")
                    .value("Buyer with id $USER_TWO not found")
            )
        )
    }

    @Test
    fun `Given a buyerId with zero balance, when perform buy nft api call, then a 422 status is returned`() {
        // Given
        val requestBody = givenRequestBodyWith(USER_TWO, ONE_DOLLAR)
        givenUserWith(USER_TWO, BigDecimal.ZERO)

        // When
        val response = whenPerformAPICall(post(BUY_NFT_TEMPLATE_ENDPOINT, NFT_ID), requestBody)

        // Then
        thenExpects(
            response, listOf(
                MockMvcResultMatchers.status().isUnprocessableEntity,
                MockMvcResultMatchers.jsonPath("$")
                    .value(
                        "Buyer $USER_TWO does not have enough balance to buy the NFT. Balance: ${
                            BigDecimal.ZERO.setScale(
                                2
                            )
                        }, NFT price: $ONE_DOLLAR"
                    )
            )
        )
    }

    @Test
    fun `Given an NFTId not present in the database, when perform buy nft api call, then a 404 status is returned`() {
        // Given
        val requestBody = givenRequestBodyWith(USER_TWO, ONE_DOLLAR)
        givenUserWith(USER_TWO, BigDecimal.TEN)

        // When
        val response = whenPerformAPICall(post(BUY_NFT_TEMPLATE_ENDPOINT, NFT_ID), requestBody)

        // Then
        thenExpects(
            response, listOf(
                MockMvcResultMatchers.status().isNotFound,
                MockMvcResultMatchers.jsonPath("$")
                    .value("NFT $NFT_ID not found.")
            )
        )
    }

    @Test
    fun `Given a buyer who is the NFT owner, when perform buy nft api call, then a 422 status is returned`() {
        // Given
        val requestBody = givenRequestBodyWith(USER_TWO, ONE_DOLLAR)
        val buyer = givenUserWith(USER_TWO, BigDecimal.TEN)
        val nft = givenNFTWith(ownerId = buyer.userId!!)

        // When
        val response = whenPerformAPICall(post(BUY_NFT_TEMPLATE_ENDPOINT, nft.nftId), requestBody)

        // Then
        thenExpects(
            response, listOf(
                MockMvcResultMatchers.status().isUnprocessableEntity,
                MockMvcResultMatchers.jsonPath("$")
                    .value("Buyer $USER_TWO is the owner of the NFT ${nft.nftId}")
            )
        )
    }

    @Test
    fun `Given a buyer who is also the creator, when perform buy nft api call, then the buyer receives payment and 200 status is returned`() {
        // Given
        val requestBody = givenRequestBodyWith(USER_TWO, ONE_DOLLAR)
        val buyer = givenUserWith(USER_TWO, BigDecimal.TEN)
        val owner = givenUserWith(USER_ONE, BigDecimal.TEN)
        val nft = givenNFTWith(creatorId = buyer.userId!!, ownerId = owner.userId!!)

        // When
        val response = whenPerformAPICall(post(BUY_NFT_TEMPLATE_ENDPOINT, nft.nftId), requestBody)

        // Then
        thenExpects(
            response, listOf(
                MockMvcResultMatchers.status().isOk,
                MockMvcResultMatchers.jsonPath("$.buyer.id").value(buyer.userId),
                MockMvcResultMatchers.jsonPath("$.oldOwner.balance")
                    .value(BigDecimal(10.8).setScale(1, RoundingMode.HALF_UP)),
                MockMvcResultMatchers.jsonPath("$.oldOwner.id").value(owner.userId),
                MockMvcResultMatchers.jsonPath("$.buyer.balance")
                    .value(BigDecimal(9.2).setScale(1, RoundingMode.HALF_UP)),
                MockMvcResultMatchers.jsonPath("$.nft.ownerId").value(buyer.userId),
            )
        )
    }

    @Test
    fun `Given an owner who is also the creator, when perform buy nft api call, then the owner receives payment twice and 200 status is returned`() {
        // Given
        val requestBody = givenRequestBodyWith(USER_TWO, ONE_DOLLAR)
        val buyer = givenUserWith(USER_TWO, BigDecimal.TEN)
        val owner = givenUserWith(USER_ONE, BigDecimal.TEN)
        val nft = givenNFTWith(creatorId = owner.userId!!, ownerId = owner.userId!!)

        // When
        val response = whenPerformAPICall(post(BUY_NFT_TEMPLATE_ENDPOINT, nft.nftId), requestBody)

        // Then
        thenExpects(
            response, listOf(
                MockMvcResultMatchers.status().isOk,
                MockMvcResultMatchers.jsonPath("$.buyer.id").value(buyer.userId),
                MockMvcResultMatchers.jsonPath("$.oldOwner.balance")
                    .value(BigDecimal(11).setScale(1, RoundingMode.HALF_UP)),
                MockMvcResultMatchers.jsonPath("$.oldOwner.id").value(owner.userId),
                MockMvcResultMatchers.jsonPath("$.buyer.balance")
                    .value(BigDecimal(9.0).setScale(1, RoundingMode.HALF_UP)),
                MockMvcResultMatchers.jsonPath("$.nft.ownerId").value(buyer.userId),
            )
        )
    }

    @Test
    fun `Given a buyer as co-creator and owner as creator, when perform buy nft api call, then buyer gets paid, owner gets paid twice and return 200`() {
        // Given
        val requestBody = givenRequestBodyWith(USER_TWO, ONE_DOLLAR)
        val buyer = givenUserWith(USER_TWO, BigDecimal.TEN)
        val owner = givenUserWith(USER_ONE, BigDecimal.TEN)
        val nft = givenNFTWith(creatorId = owner.userId!!, ownerId = owner.userId!!)
        givenNFTCoCreator(nft.nftId!!, buyer.userId!!)

        // When
        val response = whenPerformAPICall(post(BUY_NFT_TEMPLATE_ENDPOINT, nft.nftId), requestBody)

        // Then
        thenExpects(
            response, listOf(
                MockMvcResultMatchers.status().isOk,
                MockMvcResultMatchers.jsonPath("$.buyer.id").value(buyer.userId),
                MockMvcResultMatchers.jsonPath("$.oldOwner.balance")
                    .value(BigDecimal(10.9).setScale(1, RoundingMode.HALF_UP)),
                MockMvcResultMatchers.jsonPath("$.oldOwner.id").value(owner.userId),
                MockMvcResultMatchers.jsonPath("$.buyer.balance")
                    .value(BigDecimal(9.1).setScale(1, RoundingMode.HALF_UP)),
                MockMvcResultMatchers.jsonPath("$.nft.ownerId").value(buyer.userId),
            )
        )
    }
}