package br.com.cursospringkotlinudemy.enums

enum class Errors(
    val code: String,
    val message: String
) {

    ML0001("ML-0001", "Book [%s] not exists"),

    ML102("ML-102", "Cannot update book with status [%s]")
}