package com.mightyblock.challenge.domain.repositories

import org.springframework.web.multipart.MultipartFile

interface IImageRepositoryService {
    fun uploadImage(image: MultipartFile): String

}
