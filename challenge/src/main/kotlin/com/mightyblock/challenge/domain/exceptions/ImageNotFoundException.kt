package com.mightyblock.challenge.domain.exceptions

class ImageNotFoundException(imageUrl: String) : DomainClientException("Image $imageUrl not found") {

}
