package com.mightyblock.challenge.domain.exceptions

class InvalidImageUrlException(imageUrl: String) : DomainClientException("Invalid image url: $imageUrl")
