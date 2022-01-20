package br.com.cursospringkotlinudemy.controller.response

data class FieldErrorResponse(
    val message: String,
    val field: String
)