package com.mightyblock.challenge.infrastructure.drivens.repositories.images

import com.mightyblock.challenge.domain.repositories.IImageRepositoryService
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class ImageRepositoryService : IImageRepositoryService {
    override fun uploadImage(image: MultipartFile): String {
        val uploadsDir = File("/uploads")
        if (!uploadsDir.exists()) {
            uploadsDir.mkdir()
        }
        val outputPath = Paths.get(uploadsDir.absolutePath + "/" + image.originalFilename)
        Files.write(outputPath, image.bytes)
        val imageUrl = "localhost:8080/api/nfts/images/${image.originalFilename}"
        println("Image url: $imageUrl")
        return imageUrl
    }
}
