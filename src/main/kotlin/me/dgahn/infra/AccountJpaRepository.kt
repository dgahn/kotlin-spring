package me.dgahn.infra

import me.dgahn.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountJpaRepository : JpaRepository<Account, String>
