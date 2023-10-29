package com.mightyblock.challenge.infrastructure.drivers.handlers

import org.springframework.http.ResponseEntity

interface IRequestHandler<T> {
    fun handle(request: T): ResponseEntity<Any>
}
