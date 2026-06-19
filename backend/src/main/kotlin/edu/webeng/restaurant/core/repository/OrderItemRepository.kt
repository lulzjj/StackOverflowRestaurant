package edu.webeng.restaurant.core.repository

import edu.webeng.restaurant.core.entity.OrderItemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository : JpaRepository<OrderItemEntity, Long>

