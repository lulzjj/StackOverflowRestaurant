package edu.webeng.restaurant.core.mapper

import edu.webeng.restaurant.core.entity.*
import edu.webeng.restaurant.model.*

/*
 * Mapper functions to convert between DTOs (model package) and JPA entities.
 * Some entity conversions require existing related entities (e.g., Reservation needs CustomerEntity).
 */

fun Customer.toEntity(): CustomerEntity = CustomerEntity(
    id = this.id,
    name = this.name,
    email = this.email
)

fun CustomerEntity.toDto(): Customer = Customer(
    id = this.id,
    name = this.name,
    email = this.email
)

fun MenuItem.toEntity(): MenuItemEntity = MenuItemEntity(
    id = this.id,
    name = this.name,
    price = this.price,
    kitchenStation = this.kitchenStation
)

fun MenuItemEntity.toDto(): MenuItem = MenuItem(
    id = this.id,
    name = this.name,
    price = this.price,
    kitchenStation = this.kitchenStation
)

fun Reservation.toEntity(customer: CustomerEntity): ReservationEntity = ReservationEntity(
    id = this.id,
    customer = customer,
    reservationDate = this.reservationDate,
    reservationTime = this.reservationTime,
    guestCount = this.guestCount,
    specialRequests = this.specialRequests
)

fun ReservationEntity.toDto(): Reservation = Reservation(
    id = this.id,
    customerId = this.customer.id ?: throw IllegalStateException("Customer id missing"),
    reservationDate = this.reservationDate,
    reservationTime = this.reservationTime,
    guestCount = this.guestCount,
    specialRequests = this.specialRequests
)

fun Order.toEntity(reservation: ReservationEntity): OrderEntity = OrderEntity(
    id = this.id,
    reservation = reservation,
    price = this.price
)

fun OrderEntity.toDto(): Order = Order(
    id = this.id,
    reservationId = this.reservation.id ?: throw IllegalStateException("Reservation id missing"),
    price = this.price
)

fun OrderItem.toEntity(order: OrderEntity, menuItem: MenuItemEntity): OrderItemEntity = OrderItemEntity(
    id = this.id,
    order = order,
    menuItem = menuItem,
    quantity = this.quantity
)

fun OrderItemEntity.toDto(): OrderItem = OrderItem(
    id = this.id,
    orderId = this.order.id ?: throw IllegalStateException("Order id missing"),
    menuItemId = this.menuItem.id ?: throw IllegalStateException("MenuItem id missing"),
    quantity = this.quantity
)

