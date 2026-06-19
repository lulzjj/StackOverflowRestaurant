package edu.webeng.restaurant.model

import java.time.LocalDate
import java.time.LocalTime
import java.math.BigDecimal

data class Customer(
	val id: Long? = null,
	val name: String,
	val email: String
)

data class Reservation(
	val id: Long? = null,
	val customerId: Long,
	val reservationDate: LocalDate,
	val reservationTime: LocalTime,
	val guestCount: Int,
	val specialRequests: String? = null
)

data class MenuItem(
	val id: Long? = null,
	val name: String,
	val price: BigDecimal,
	val kitchenStation: String
)

data class Order(
	val id: Long? = null,
	val reservationId: Long,
	val price: BigDecimal
)

data class OrderItem(
	val id: Long? = null,
	val orderId: Long,
	val menuItemId: Long,
	val quantity: Int
)
