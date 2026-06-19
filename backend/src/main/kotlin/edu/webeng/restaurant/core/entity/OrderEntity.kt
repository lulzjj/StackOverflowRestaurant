package edu.webeng.restaurant.core.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "\"order\"")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    val reservation: ReservationEntity,
    val price: BigDecimal
)

