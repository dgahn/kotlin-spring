package me.dgahn.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class SignUpRequestDto(
    val id: String,
    val password: String
) {
    fun toJson() = Json.encodeToString(this)
}
