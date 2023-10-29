package com.mightyblock.challenge.domain.repositories

interface IImageRepository {
    fun saveImage(image: ByteArray): String
    fun getImage(imageName: String): ByteArray
}
