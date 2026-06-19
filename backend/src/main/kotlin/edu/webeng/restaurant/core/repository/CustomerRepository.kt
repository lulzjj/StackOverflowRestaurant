package edu.webeng.restaurant.core.repository

import edu.webeng.restaurant.core.entity.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<CustomerEntity, Long>

