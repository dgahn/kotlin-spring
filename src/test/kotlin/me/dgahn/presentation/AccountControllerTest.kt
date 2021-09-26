package me.dgahn.presentation

import me.dgahn.dto.SignUpRequestDto
import me.dgahn.dto.SignUpResponseDto
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AccountControllerTest : SpringMockMvcTestSupport() {

    @Test
    fun `회원가입을 할 수 있다`() {
        mockMvcPostTest(
            uri = "/accounts",
            requestContent = SignUpRequestDto("id", "password").toJson(),
            responseContent = SignUpResponseDto("id").toJson(),
        )
    }
}
