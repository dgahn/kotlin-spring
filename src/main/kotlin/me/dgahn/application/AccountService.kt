package me.dgahn.application

import me.dgahn.domain.Account
import me.dgahn.exception.AccountIdDuplicationException
import me.dgahn.exception.ErrorCode
import me.dgahn.infra.AccountJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(private val accountJpaRepository: AccountJpaRepository) {

    @Transactional
    fun signUp(account: Account): Account {
        val findById = accountJpaRepository.findById(account.id)
        if (findById.isPresent) {
            throw AccountIdDuplicationException(ErrorCode.ERROR_101)
        }
        return accountJpaRepository.save(account)
    }
}
