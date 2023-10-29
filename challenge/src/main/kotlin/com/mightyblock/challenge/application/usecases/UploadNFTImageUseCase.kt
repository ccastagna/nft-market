package com.mightyblock.challenge.application.usecases

import com.mightyblock.challenge.domain.services.images.IImageService
import org.springframework.web.multipart.MultipartFile

class UploadNFTImageUseCase(private val imageService: IImageService) : IUploadNFTImageUseCase {
    override fun execute(image: MultipartFile): String {
        return imageService.saveImage(image)
    }
}