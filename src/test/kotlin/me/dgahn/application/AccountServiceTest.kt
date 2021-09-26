package me.dgahn.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import me.dgahn.exception.AccountIdDuplicationException
import me.dgahn.fixture.getAccount
import me.dgahn.infra.AccountJpaRepository
import org.junit.jupiter.api.Test
import java.util.*

class AccountServiceTest {
    private val accountJpaRepository = mockk<AccountJpaRepository>()
    private val accountService = AccountService(accountJpaRepository)

    @Test
    fun `회원가입을 할 수 있다`() {
        val account = getAccount()
        every { accountJpaRepository.findById(account.id) } returns Optional.empty()
        every { accountJpaRepository.save(account) } returns account
        accountService.signUp(account) shouldBe account
    }

    @Test
    fun `사용자의 ID가 중복되면 AccountIdDuplicationException이 발생한다`() {
        val account = getAccount()
        every { accountJpaRepository.findById(account.id) } returns Optional.of(account)
        shouldThrow<AccountIdDuplicationException> { accountService.signUp(account) }
    }
}
