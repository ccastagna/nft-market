package com.mightyblock.challenge.domain.exceptions

class ImageNotFoundException(imageUrl: String) : DomainClientException("Image not found: $imageUrl") {

}
