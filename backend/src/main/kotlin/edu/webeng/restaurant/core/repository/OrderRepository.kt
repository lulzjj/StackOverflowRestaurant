package edu.webeng.restaurant.core.repository

import edu.webeng.restaurant.core.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<OrderEntity, Long>

