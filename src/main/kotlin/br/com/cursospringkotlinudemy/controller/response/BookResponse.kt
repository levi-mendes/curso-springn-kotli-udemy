package br.com.cursospringkotlinudemy.controller.response

import br.com.cursospringkotlinudemy.BookStatus
import br.com.cursospringkotlinudemy.model.CustomerModel
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var customer: CustomerModel? = null,
    var status: BookStatus? = null
)