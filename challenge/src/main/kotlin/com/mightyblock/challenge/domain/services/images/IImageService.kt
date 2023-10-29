package com.mightyblock.challenge.domain.services.images

import org.springframework.web.multipart.MultipartFile

interface IImageService {
    fun saveImage(image: MultipartFile): String
    fun getImage(imageUrl: String): ByteArray
    fun checkImageIsLoadedOrThrow(imageUrl: String)
}
