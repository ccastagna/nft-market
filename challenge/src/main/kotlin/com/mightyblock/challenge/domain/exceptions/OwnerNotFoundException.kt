package com.mightyblock.challenge.domain.exceptions

data class OwnerNotFoundException(val ownerId: Int) :
    DomainClientException("Owner with id $ownerId not found")
