package com.mightyblock.challenge.domain.exceptions

data class BuyerNotFoundException(val buyerId: Int) :
    DomainClientException("Buyer with id $buyerId not found")
