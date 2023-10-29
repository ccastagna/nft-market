package com.mightyblock.challenge.infrastructure.drivers.dtos.requests.validators

interface IInputRequestValidator<T> {
    fun validate(request: T)
}
