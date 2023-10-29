package com.mightyblock.challenge.infrastructure.drivers.dtos.requests

import org.springframework.web.multipart.MultipartFile

data class UploadNFTImageRequestDTO(val image: MultipartFile)
