package me.dgahn.infra

import io.kotest.matchers.shouldBe
import me.dgahn.fixture.getAccount
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class AccountJpaRepositoryTest(
    @Autowired private val accountJpaRepository: AccountJpaRepository
) {

    @Test
    fun `사용자를 저장할 수 있다`() {
        val account = getAccount()
        accountJpaRepository.save(account)
        val findAccount = accountJpaRepository.findById(account.id)
        findAccount.get() shouldBe account
    }
}
