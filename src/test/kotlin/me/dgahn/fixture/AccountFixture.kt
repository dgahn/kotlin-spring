package me.dgahn.fixture

import me.dgahn.domain.Account

fun getAccount() = Account(
    id = "test",
    password = "1234"
)
