package edu.webeng.restaurant.core.entity

import jakarta.persistence.*

@Entity
@Table(name = "order_item")
data class OrderItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    val order: OrderEntity,
    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    val menuItem: MenuItemEntity,
    val quantity: Int
)

