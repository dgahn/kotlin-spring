package me.dgahn.exception

enum class ErrorCode(
    val code: String,
    val message: String
) {
    ERROR_101("ERROR_101", "존재하는 아이디입니다.");
}
