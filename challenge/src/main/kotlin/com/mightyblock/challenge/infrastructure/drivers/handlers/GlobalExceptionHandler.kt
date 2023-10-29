package com.mightyblock.challenge.infrastructure.drivers.handlers

import com.mightyblock.challenge.domain.exceptions.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<String> {
        logError(ex)
        return ResponseEntity.badRequest().body(ex.message)
    }

    @ExceptionHandler(InvalidImageUrlException::class)
    fun handleInvalidImageUrlException(ex: InvalidImageUrlException): ResponseEntity<String> {
        logError(ex)
        return ResponseEntity.badRequest().body(ex.message)
    }

    @ExceptionHandler(
        value = [
            CoCreatorNotFoundException::class,
            CreatorNotFoundException::class,
            ImageNotPreloadedException::class,
            BuyerIsNFTOwnerException::class,
            BuyerNotFoundException::class,
            NotEnoughBalanceException::class,
            OwnerNotFoundException::class,
        ]
    )
    fun handleUnprocessableEntityExceptions(ex: DomainClientException): ResponseEntity<String> {
        logError(ex)
        return ResponseEntity.unprocessableEntity().body(ex.message)
    }

    @ExceptionHandler(value = [ImageNotFoundException::class, NFTNotFoundException::class])
    fun handleNotFoundExceptions(ex: DomainClientException): ResponseEntity<String> {
        logError(ex)
        return ResponseEntity.status(404).body(ex.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleDefaultException(ex: Exception): ResponseEntity<String> {
        logError(ex)
        return ResponseEntity.status(500).body("Internal server error")
    }

    private fun logError(ex: Exception) {
        println("ERROR | message: ${ex.message} - cause: ${ex.cause} - stacktrace: ${ex.printStackTrace()}")
    }
}