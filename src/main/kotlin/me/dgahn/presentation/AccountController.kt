package me.dgahn.presentation

import me.dgahn.application.AccountService
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
class AccountController(
    private val accountService: AccountService
) {

    @PostMapping
    fun signUp(@RequestBody request: SignUpRequestDto): ResponseEntity<SignUpResponseDto> {
        val account = accountService.signUp(request.toAccount())
        return ResponseEntity(SignUpResponseDto(account.id), HttpStatus.CREATED)
    }
}
