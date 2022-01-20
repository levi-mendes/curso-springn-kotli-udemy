package br.com.cursospringkotlinudemy.enums

enum class Errors(
    val code: String,
    val message: String
) {

    ML201("ML-201", "Customer [%s] doesnt exists"),

    ML001("ML-001", "Invalid Argument"),

    ML101("ML-101", "Book [%s] not exists"),
    ML102("ML-102", "Cannot update book with status [%s]")
}