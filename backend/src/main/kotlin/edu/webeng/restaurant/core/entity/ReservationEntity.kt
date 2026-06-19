package edu.webeng.restaurant.core.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "RESERVATION")
data class ReservationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    val customer: CustomerEntity,
    val reservationDate: LocalDate,
    val reservationTime: LocalTime,
    val guestCount: Int,
    val specialRequests: String? = null
)