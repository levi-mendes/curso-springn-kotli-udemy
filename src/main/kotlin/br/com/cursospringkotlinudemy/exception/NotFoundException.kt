package br.com.cursospringkotlinudemy.exception

class NotFoundException(
    override val message: String,
    val errorCode: String
): Exception()