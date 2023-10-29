package com.mightyblock.challenge.domain.services.images

import com.mightyblock.challenge.domain.exceptions.ImageNotFoundException
import com.mightyblock.challenge.domain.exceptions.InvalidImageUrlException
import com.mightyblock.challenge.domain.repositories.IImageRepositoryService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.web.multipart.MultipartFile

internal class ImageServiceTest {

    private val mockImageBaseUrl = "/api/nfts/images/"
    private val mockImageName = "mockImageName.jpg"

    private val imageRepositoryService: IImageRepositoryService = mock()

    private val imageService = ImageService(imageRepositoryService)

    @Test
    fun `Given a valid image, when saveImage, then return the image url`() {
        // Given
        val mockFile: MultipartFile = mock()
        val mockImageUrl = mockImageBaseUrl + mockImageName
        doReturn(mockImageUrl).`when`(imageRepositoryService).uploadImage(mockFile)

        // When
        val result = imageService.saveImage(mockFile)

        // Then
        assertEquals(mockImageUrl, result)
    }

    @Test
    fun `Given a valid image name, when getImage, then return the image bytes`() {
        // Given
        val imageName = mockImageName
        val mockBytes = mockImageName.toByteArray()
        doReturn(mockBytes).`when`(imageRepositoryService).getImage(imageName)

        // When
        val result = imageService.getImage(imageName)

        // Then
        assertArrayEquals(mockBytes, result)
    }

    @Test
    fun `Given a valid image URL, when checkImageIsLoadedOrThrow, then should not throw exception`() {
        // Given
        val validImageUrl = mockImageBaseUrl + mockImageName
        val imageName = mockImageName
        doReturn(mockImageName.toByteArray()).`when`(imageRepositoryService).getImage(imageName)

        // When & Then
        assertDoesNotThrow { imageService.checkImageIsLoadedOrThrow(validImageUrl) }
    }

    @Test
    fun `Given an invalid image URL, when checkImageIsLoadedOrThrow, then should throw InvalidImageURLException`() {
        // Given
        val invalidImageUrl: String = mockImageName

        // When & Then
        assertThrows<InvalidImageUrlException> {
            imageService.checkImageIsLoadedOrThrow(invalidImageUrl)
        }.apply {
            assertEquals(message, "Invalid image url: $invalidImageUrl")
        }
    }

    @Test
    fun `Given a valid image URL but image is not found, when checkImageIsLoadedOrThrow, then should throw ImageNotFoundException`() {
        // Given
        val validImageUrl = mockImageBaseUrl + mockImageName
        val imageName = mockImageName
        doThrow(RuntimeException()).`when`(imageRepositoryService).getImage(imageName)

        // When & Then
        assertThrows<ImageNotFoundException> {
            imageService.checkImageIsLoadedOrThrow(validImageUrl)
        }.apply {
            assertEquals(message, "Image $validImageUrl not found")
        }
    }
}