package edu.webeng.restaurant.controller

import edu.webeng.restaurant.core.mapper.toDto
import edu.webeng.restaurant.core.mapper.toEntity
import edu.webeng.restaurant.core.repository.CustomerRepository
import edu.webeng.restaurant.core.repository.ReservationRepository
import edu.webeng.restaurant.model.Reservation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/reservations")
class ReservationController(
    private val customerRepository: CustomerRepository,
    private val reservationRepository: ReservationRepository
) {
    @PostMapping
    fun createReservation(@RequestBody reservation: Reservation): ResponseEntity<Reservation> {
        val customerOpt = customerRepository.findById(reservation.customerId)
        if (customerOpt.isEmpty) {
            return ResponseEntity.badRequest().build()
        }

        val customerEntity = customerOpt.get()
        val entity = reservation.toEntity(customerEntity)
        val saved = reservationRepository.save(entity)
        return ResponseEntity.ok(saved.toDto())
    }
}