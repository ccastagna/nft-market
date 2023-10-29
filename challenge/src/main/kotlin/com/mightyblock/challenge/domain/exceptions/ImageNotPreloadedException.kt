package com.mightyblock.challenge.domain.exceptions

class ImageNotPreloadedException(imageUrl: String) : DomainClientException("Image not preloaded: $imageUrl") {

}
