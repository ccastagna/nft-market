package com.mightyblock.challenge.application.usecases

import org.springframework.web.multipart.MultipartFile

interface IUploadNFTImageUseCase {
    fun execute(image: MultipartFile): String

}
