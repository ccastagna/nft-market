package com.mightyblock.challenge.domain.exceptions

data class CoCreatorNotFoundException(val coCreatorId: Int) :
    DomainClientException("Co-creator with id $coCreatorId not found")
