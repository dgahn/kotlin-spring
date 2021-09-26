package me.dgahn.exception

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class ErrorResponseDto(
    val code: String,
    val message: String,
    val trace: String
) {
    constructor(errorCode: ErrorCode, trace: String = "") : this(
        code = errorCode.code,
        message = errorCode.message,
        trace = trace
    )

    fun toJson() = Json.encodeToString(this)
}
