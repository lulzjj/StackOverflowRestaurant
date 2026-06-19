package edu.webeng.restaurant.core.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "menu_item")
data class MenuItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val price: BigDecimal,
    val kitchenStation: String
)

