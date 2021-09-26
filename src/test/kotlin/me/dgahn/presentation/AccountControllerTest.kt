package me.dgahn.presentation

import me.dgahn.dto.SignUpRequestDto
import me.dgahn.dto.SignUpResponseDto
import me.dgahn.exception.ErrorCode
import me.dgahn.exception.ErrorResponseDto
import me.dgahn.infra.AccountJpaRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

@SpringBootTest
class AccountControllerTest(
    @Autowired private val accountJpaRepository: AccountJpaRepository
) : SpringMockMvcTestSupport() {

    @AfterEach
    fun afterEach() {
        accountJpaRepository.deleteAll()
    }

    @Test
    fun `회원가입을 할 수 있다`() {
        mockMvcPostTest(
            uri = "/accounts",
            requestContent = SignUpRequestDto("id", "password").toJson(),
            responseContent = SignUpResponseDto("id").toJson(),
        )
    }

    @Test
    fun `회원가입시 아이디가 중복되면 ERROR_CODE 101로 응답한다`() {
        mockMvcPostTest(
            uri = "/accounts",
            requestContent = SignUpRequestDto("id", "password").toJson(),
            responseContent = SignUpResponseDto("id").toJson(),
        )
        mockMvcPostTest(
            uri = "/accounts",
            requestContent = SignUpRequestDto("id", "password").toJson(),
            responseContent = ErrorResponseDto(ErrorCode.ERROR_101).toJson(),
            status = HttpStatus.BAD_REQUEST
        )
    }
}
