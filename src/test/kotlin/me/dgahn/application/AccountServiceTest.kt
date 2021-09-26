package me.dgahn.application

import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import me.dgahn.fixture.getAccount
import me.dgahn.infra.AccountJpaRepository
import org.junit.jupiter.api.Test

class AccountServiceTest {
    private val accountJpaRepository = mockk<AccountJpaRepository>()
    private val accountService = AccountService(accountJpaRepository)

    @Test
    fun `회원가입을 할 수 있다`() {
        val account = getAccount()
        every { accountJpaRepository.save(account) } returns account
        accountService.signUp(account) shouldBe account
    }
}
