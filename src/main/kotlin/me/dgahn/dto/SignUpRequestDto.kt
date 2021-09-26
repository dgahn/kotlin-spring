package me.dgahn.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.dgahn.domain.Account

@Serializable
data class SignUpRequestDto(
    val id: String,
    val password: String
) {

    fun toJson() = Json.encodeToString(this)

    fun toAccount(): Account = Account(
        id = id,
        password = password
    )
}
