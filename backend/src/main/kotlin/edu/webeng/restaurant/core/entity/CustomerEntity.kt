package edu.webeng.restaurant.core.entity

import jakarta.persistence.*

@Entity
@Table(name = "CUSTOMER")
data class CustomerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val email: String,
    @OneToMany(mappedBy = "customer", cascade = [CascadeType.ALL], orphanRemoval = true)
    val reservations: List<ReservationEntity> = emptyList()
)