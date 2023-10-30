package com.mightyblock.challenge.domain.services.images

import com.mightyblock.challenge.domain.exceptions.ImageNotFoundException
import com.mightyblock.challenge.domain.exceptions.ImageNotPreloadedException
import com.mightyblock.challenge.domain.exceptions.InvalidImageUrlException
import com.mightyblock.challenge.domain.repositories.IImageRepositoryService
import org.springframework.web.multipart.MultipartFile

class ImageService(private val imageRepositoryService: IImageRepositoryService) : IImageService {

    companion object {
        const val IMAGE_URL_REGEX = """.*/api/nfts/images/([^/]+)$"""
    }

    override fun saveImage(image: MultipartFile): String {
        return imageRepositoryService.uploadImage(image)
    }

    override fun getImage(imageName: String): ByteArray {
        return try {
            imageRepositoryService.getImage(imageName)
        } catch (throwable: Throwable) {
            throw ImageNotFoundException(imageName)
        }
    }

    override fun checkImageIsLoadedOrThrow(imageUrl: String) {
        val imageName = extractImageName(imageUrl) ?: throw InvalidImageUrlException(imageUrl)
        try {
            getImage(imageName)
        } catch (ex: ImageNotFoundException) {
            throw ImageNotPreloadedException(imageUrl)
        }
    }

    private fun extractImageName(imageUrl: String): String? {
        val regex = IMAGE_URL_REGEX.toRegex()
        val matchResult = regex.find(imageUrl)
        return matchResult?.groups?.get(1)?.value
    }

}