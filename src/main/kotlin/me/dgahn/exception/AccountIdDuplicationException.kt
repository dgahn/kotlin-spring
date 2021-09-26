package me.dgahn.exception

class AccountIdDuplicationException(val errorCode: ErrorCode) : RuntimeException()
