package br.com.cursospringkotlinudemy.exception

class BadRequestException(
    override val message: String,
    val errorCode: String
): Exception()