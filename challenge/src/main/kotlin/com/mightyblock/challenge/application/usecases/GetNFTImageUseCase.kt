package com.mightyblock.challenge.application.usecases

import com.mightyblock.challenge.domain.services.images.IImageService

class GetNFTImageUseCase(private val imageService: IImageService) : IGetNFTImageUseCase {

    override fun execute(imageName: String): ByteArray {
        return imageService.getImage(imageName)
    }
}