package me.dgahn.application

import me.dgahn.domain.Account
import me.dgahn.infra.AccountJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(private val accountJpaRepository: AccountJpaRepository) {

    @Transactional
    fun signUp(account: Account): Account {
        return accountJpaRepository.save(account)
    }
}
