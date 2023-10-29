package com.mightyblock.challenge.domain.exceptions

data class CreatorNotFoundException(val creatorId: Int) :
    DomainClientException("Creator with id $creatorId not found")
