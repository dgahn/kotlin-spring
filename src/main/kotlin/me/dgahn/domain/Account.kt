package me.dgahn.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Account(
    @Id
    val id: String,
    val password: String
)
