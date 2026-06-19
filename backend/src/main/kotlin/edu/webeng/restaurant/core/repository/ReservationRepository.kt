package edu.webeng.restaurant.core.repository

import edu.webeng.restaurant.core.entity.ReservationEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository : JpaRepository<ReservationEntity, Long>

