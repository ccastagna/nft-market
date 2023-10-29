package com.mightyblock.challenge.infrastructure.drivers.controllers

import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.GetNFTImageRequestDTO
import com.mightyblock.challenge.infrastructure.drivers.dtos.requests.UploadNFTImageRequestDTO
import com.mightyblock.challenge.infrastructure.drivers.handlers.IRequestHandler
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths

@RestController
@RequestMapping("/api/nfts/images")
class NFTImageController(
    private val uploadNFTImageHandler: IRequestHandler<UploadNFTImageRequestDTO>,
    private val getNFTImageHandler: IRequestHandler<GetNFTImageRequestDTO>,

    ) {

    @PostMapping
    fun uploadNFTImage(
        request: HttpServletRequest?,
        @RequestPart(value = "image") image: MultipartFile
    ): ResponseEntity<Any> {
        val uploadNFTImageRequestDTO = UploadNFTImageRequestDTO(image)
        return uploadNFTImageHandler.handle(uploadNFTImageRequestDTO)
    }

    @GetMapping("/{imageName}")
    fun getImage(@PathVariable imageName: String): ResponseEntity<Any> {
        val getNFTImageRequestDTO = GetNFTImageRequestDTO(imageName)
        return getNFTImageHandler.handle(getNFTImageRequestDTO)
    }
}
