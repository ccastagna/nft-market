package com.mightyblock.challenge.domain.services.nfts

import com.mightyblock.challenge.domain.exceptions.NFTNotFoundException
import com.mightyblock.challenge.domain.models.NFT
import com.mightyblock.challenge.domain.models.Page
import com.mightyblock.challenge.domain.models.PageableNFTList
import com.mightyblock.challenge.domain.repositories.INFTCoCreatorRepositoryService
import com.mightyblock.challenge.domain.repositories.INFTRepositoryService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import java.time.LocalDateTime
import java.util.*

internal class NFTServiceTest {

    companion object {
        private const val USER_ONE = 1
        private const val USER_TWO = 2
        private const val USER_THREE = 3
        private const val IMAGE_URL = "example.com/api/nfts/images/mockImageName.jpg"
        private const val NFT_DESCRIPTION = "description"
        private val NFT_ID = UUID.randomUUID()
        private val UPLOAD_TIME = LocalDateTime.now()
        private const val PAGE_ONE = 1
        private const val THREE_PER_PAGE = 3
    }

    private var nftRepositoryService: INFTRepositoryService = mock()
    private var nftCoCreatorRepositoryService: INFTCoCreatorRepositoryService = mock()

    private var nftService: NFTService = NFTService(nftRepositoryService, nftCoCreatorRepositoryService)


    @Test
    fun `Given valid NFT data, when mint, then should create and save an NFT`() {
        // Given
        val creatorId = USER_ONE
        val coCreators = listOf(USER_TWO, USER_THREE)
        val imageUrl = IMAGE_URL
        val description = NFT_DESCRIPTION
        val expectedNFT = NFT(
            creatorId = creatorId,
            coCreators = coCreators,
            imageUrl = imageUrl,
            description = description,
            uploadTime = LocalDateTime.now(),
            ownerId = creatorId
        )
        doReturn(expectedNFT).`when`(nftRepositoryService).saveNFT(org.mockito.kotlin.any())

        // When & Then
        nftService.mint(creatorId, coCreators, imageUrl, description)
            .apply {
                assertEquals(expectedNFT.creatorId, this.creatorId)
                assertEquals(expectedNFT.coCreators, this.coCreators)
                assertEquals(expectedNFT.imageUrl, this.imageUrl)
                assertEquals(expectedNFT.description, this.description)
                assertEquals(expectedNFT.ownerId, this.ownerId)
            }
        verify(nftRepositoryService).saveNFT(org.mockito.kotlin.any())
        coCreators.forEach { verify(nftCoCreatorRepositoryService).save(eq(it), org.mockito.kotlin.any()) }
    }

    @Test
    fun `Given an NFTId of an existent NFT, when getNFTById, then should return the NFT`() {
        // Given
        val coCreators = listOf(USER_THREE)
        val nft = givenValidNFT(coCreators = coCreators)
        doReturn(nft).`when`(nftRepositoryService).getNFTById(NFT_ID)
        doReturn(coCreators).`when`(nftCoCreatorRepositoryService).getCoCreatorsByNFTId(NFT_ID)

        // When
        val result = nftService.getNFTById(nft.id)

        // Then
        assertEquals(nft, result)
        verify(nftRepositoryService).getNFTById(nft.id)
        verify(nftCoCreatorRepositoryService).getCoCreatorsByNFTId(nft.id)
    }

    @Test
    fun `Given an NFTId of a non existent NFT, when getNFTById, then throw NFTNotFoundException`() {
        // Given
        val nftId = UUID.randomUUID()
        doReturn(null).`when`(nftRepositoryService).getNFTById(nftId)

        // When & Then
        assertThrows<NFTNotFoundException> {
            nftService.getNFTById(nftId)
        }.apply {
            assertEquals(message, "NFT $nftId not found.")
        }
        verify(nftRepositoryService).getNFTById(nftId)
    }

    @Test
    fun `Given valid NFT, when updateNFT, then should update and return the updated NFT`() {
        // Given
        val updatedNFT = givenValidNFT()
        doReturn(updatedNFT).`when`(nftRepositoryService).saveNFT(updatedNFT)

        // When
        val result = nftService.updateNFT(updatedNFT)

        // Then
        assertEquals(updatedNFT, result)
        verify(nftRepositoryService).saveNFT(updatedNFT)
    }

    @Test
    fun `Given a valid page and size and there are NFTs, when getALLNFTsPaginated, then should retrieve paginated NFTs`() {
        // Given
        val mockNFTList = listOf(
            givenValidNFT(id = UUID.randomUUID()),
            givenValidNFT(id = UUID.randomUUID())
        )
        val mockPage = Page(
            number = PAGE_ONE,
            size = THREE_PER_PAGE,
            totalElements = mockNFTList.size.toLong(),
            totalPages = (mockNFTList.size.toLong() / THREE_PER_PAGE).toInt()
        )
        val mockPageableNFTList = PageableNFTList(mockNFTList, mockPage)
        doReturn(mockPageableNFTList).`when`(nftRepositoryService).getAllNFTsPaginated(PAGE_ONE, THREE_PER_PAGE)

        // When
        val result = nftService.getAllNFTsPaginated(PAGE_ONE, THREE_PER_PAGE)

        // Then
        assertEquals(mockPageableNFTList, result)
        verify(nftRepositoryService).getAllNFTsPaginated(PAGE_ONE, THREE_PER_PAGE)
    }

    private fun givenValidNFT(id: UUID = NFT_ID, coCreators: List<Int> = listOf()): NFT =
        NFT(
            id = id,
            creatorId = USER_ONE,
            coCreators = coCreators,
            imageUrl = IMAGE_URL,
            description = NFT_DESCRIPTION,
            uploadTime = UPLOAD_TIME,
            ownerId = USER_ONE,
        )
}