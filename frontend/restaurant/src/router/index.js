import { createRouter, createWebHistory } from 'vue-router'
import Welcome from '../pages/Welcome.vue'
import Reservations from '../pages/Reservations.vue'
import Orders from '../pages/Orders.vue'
import KitchenDashboard from '../pages/KitchenDashboard.vue'
import DailyReports from '../pages/DailyReports.vue'

const routes = [
  {
    path: '/',
    name: 'Welcome',
    component: Welcome
  },
  {
    path: '/reservations',
    name: 'Reservations',
    component: Reservations
  },
  {
    path: '/orders',
    name: 'Orders',
    component: Orders
  },
  {
    path: '/kitchen',
    name: 'KitchenDashboard',
    component: KitchenDashboard
  },
  {
    path: '/reports',
    name: 'DailyReports',
    component: DailyReports
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
