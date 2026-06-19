package edu.webeng.restaurant.core.repository

import edu.webeng.restaurant.core.entity.MenuItemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MenuItemRepository : JpaRepository<MenuItemEntity, Long>

