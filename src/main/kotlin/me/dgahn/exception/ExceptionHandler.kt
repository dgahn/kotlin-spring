package me.dgahn.exception

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
private val logger = KotlinLogging.logger { }

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(AccountIdDuplicationException::class)
    fun handleIdDuplicationException(e: AccountIdDuplicationException): ResponseEntity<ErrorResponseDto> {
        logger.error { "ID DUPLICATION EXCEPTION: ${e.message}" }
        return ResponseEntity(ErrorResponseDto(e.errorCode), HttpStatus.BAD_REQUEST)
    }
}
