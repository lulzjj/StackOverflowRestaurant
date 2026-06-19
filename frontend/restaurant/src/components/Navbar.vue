<template>
  <nav class="navbar">
    <div class="nav-container">
      <div class="nav-brand">
        <router-link to="/" class="brand-link">
          <span class="brand-icon">🍽️</span>
          <span class="brand-text">Stack Overflow Restaurant</span>
        </router-link>
      </div>

      <button class="nav-toggle" @click="menuOpen = !menuOpen" :class="{ active: menuOpen }">
        <span></span>
        <span></span>
        <span></span>
      </button>

      <ul class="nav-menu" :class="{ active: menuOpen }">
        <li class="nav-item">
          <router-link
            to="/reservations"
            class="nav-link"
            @click="menuOpen = false"
            :class="{ active: $route.path === '/reservations' }"
          >
            📅 Reservations
          </router-link>
        </li>
        <li class="nav-item">
          <router-link
            to="/orders"
            class="nav-link"
            @click="menuOpen = false"
            :class="{ active: $route.path === '/orders' }"
          >
            🍴 Orders
          </router-link>
        </li>
        <li class="nav-item">
          <router-link
            to="/kitchen"
            class="nav-link"
            @click="menuOpen = false"
            :class="{ active: $route.path === '/kitchen' }"
          >
            👨‍🍳 Kitchen Dashboard
          </router-link>
        </li>
        <li class="nav-item">
          <router-link
            to="/reports"
            class="nav-link"
            @click="menuOpen = false"
            :class="{ active: $route.path === '/reports' }"
          >
            📊 Reports
          </router-link>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const menuOpen = ref(false)
</script>

<style scoped>
.navbar {
  background: #1a1f2e;
  padding: 1rem 0;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
  position: sticky;
  top: 0;
  z-index: 1000;
  border-bottom: 2px solid #3498db;
}

.nav-container {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

.nav-brand {
  flex: 1;
}

.brand-link {
  display: flex;
  align-items: center;
  gap: 10px;
  text-decoration: none;
  color: white;
  font-weight: bold;
  font-size: 1.2em;
  transition: opacity 0.3s ease;
}

.brand-link:hover {
  opacity: 0.8;
}

.brand-icon {
  font-size: 1.5em;
}

.brand-text {
  display: none;
}

@media (min-width: 768px) {
  .brand-text {
    display: inline;
  }
}

.nav-menu {
  display: flex;
  list-style: none;
  gap: 10px;
  margin: 0;
  padding: 0;
}

.nav-item {
  margin: 0;
}

.nav-link {
  display: block;
  color: #ecf0f1;
  text-decoration: none;
  padding: 10px 15px;
  border-radius: 5px;
  transition: background 0.3s ease, color 0.3s ease;
  font-weight: 500;
}

.nav-link:hover {
  background: #2c3e50;
  color: #3498db;
}

.nav-link.active {
  background: #3498db;
  color: white;
  border-bottom: none;
}

.nav-toggle {
  display: none;
  flex-direction: column;
  background: none;
  border: none;
  cursor: pointer;
  gap: 5px;
}

.nav-toggle span {
  width: 25px;
  height: 3px;
  background: #ecf0f1;
  border-radius: 2px;
  transition: all 0.3s ease;
}

.nav-toggle.active span:nth-child(1) {
  transform: rotate(45deg) translate(8px, 8px);
}

.nav-toggle.active span:nth-child(2) {
  opacity: 0;
}

.nav-toggle.active span:nth-child(3) {
  transform: rotate(-45deg) translate(8px, -8px);
}

@media (max-width: 768px) {
  .nav-toggle {
    display: flex;
  }

  .nav-menu {
    position: absolute;
    top: 60px;
    left: 0;
    right: 0;
    background: #1a1f2e;
    flex-direction: column;
    padding: 20px;
    gap: 0;
    max-height: 0;
    overflow: hidden;
    transition: max-height 0.3s ease;
    border-bottom: 2px solid #3498db;
  }

  .nav-menu.active {
    max-height: 500px;
  }

  .nav-item {
    border-bottom: 1px solid #2c3e50;
  }

  .nav-item:last-child {
    border-bottom: none;
  }

  .nav-link {
    padding: 12px 0;
    display: block;
  }

  .nav-link.active {
    border-bottom: none;
    background: #3498db;
  }
}
</style>
