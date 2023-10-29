package com.mightyblock.challenge.application.usecases

interface IGetNFTImageUseCase {
    fun execute(imageName: String): ByteArray
}
