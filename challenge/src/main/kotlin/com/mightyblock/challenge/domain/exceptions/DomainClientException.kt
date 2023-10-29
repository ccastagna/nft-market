package com.mightyblock.challenge.domain.exceptions

open class DomainClientException(override val message: String?) : Exception(message)