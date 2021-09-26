package me.dgahn.presentation

import me.dgahn.dto.SignUpRequestDto
import me.dgahn.dto.SignUpResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountController {

    @PostMapping
    fun signUp(@RequestBody request: SignUpRequestDto): ResponseEntity<SignUpResponseDto> {
        return ResponseEntity(SignUpResponseDto(request.id), HttpStatus.CREATED)
    }
}
