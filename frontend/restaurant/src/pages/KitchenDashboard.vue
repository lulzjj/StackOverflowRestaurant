<template>
  <div class="kitchen-container">
    <div class="kitchen-header">
      <h1>👨‍🍳 Kitchen Dashboard</h1>
      <div class="header-stats">
        <div class="stat">
          <span class="stat-label">Total Orders:</span>
          <span class="stat-value">{{ totalOrders }}</span>
        </div>
        <div class="stat">
          <span class="stat-label">Pending:</span>
          <span class="stat-value pending">{{ pendingOrders }}</span>
        </div>
        <div class="stat">
          <span class="stat-label">In Progress:</span>
          <span class="stat-value progress">{{ inProgressOrders }}</span>
        </div>
        <div class="stat">
          <span class="stat-label">Completed:</span>
          <span class="stat-value completed">{{ completedOrders }}</span>
        </div>
      </div>
    </div>

    <div class="counters-grid">
      <div v-for="counter in counters" :key="counter.id" class="counter-card">
        <div class="counter-header">
          <h2>{{ counter.name }}</h2>
          <span class="counter-status" :class="counter.status">{{ counter.status }}</span>
        </div>

        <div class="queue-section">
          <h3>Queue ({{ counter.queue.length }})</h3>

          <div v-if="counter.queue.length === 0" class="empty-queue">
            <p>✨ All caught up!</p>
          </div>

          <div v-else class="queue-items">
            <div
              v-for="(order, index) in counter.queue"
              :key="order.id"
              class="queue-item"
              :class="{ active: index === 0 }"
            >
              <div class="order-number">
                <span v-if="index === 0" class="now-badge">NOW</span>
                <span v-else class="queue-position">#{{ index }}</span>
              </div>

              <div class="order-content">
                <p class="order-id">Order #{{ order.id }}</p>
                <div class="order-items">
                  <span v-for="item in order.items" :key="item" class="item-badge">
                    {{ item }}
                  </span>
                </div>
                <p class="order-time">{{ order.time }}</p>
              </div>

              <div v-if="index === 0" class="order-actions">
                <button @click="completeOrder(counter.id, order.id)" class="btn-complete">
                  ✓ Done
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="kitchen-footer">
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

const counters = ref([
  {
    id: 1,
    name: '🍔 Grill Counter',
    status: 'busy',
    queue: [
      { id: 101, items: ['Burger', 'Steak'], time: '2 min' },
      { id: 102, items: ['Chicken'], time: '5 min' },
      { id: 103, items: ['Burger'], time: '8 min' }
    ]
  },
  {
    id: 2,
    name: '🍝 Pasta Station',
    status: 'busy',
    queue: [
      { id: 104, items: ['Carbonara', 'Bolognese'], time: '3 min' },
      { id: 105, items: ['Pasta Alfredo'], time: '6 min' }
    ]
  },
  {
    id: 3,
    name: '🥗 Salad Bar',
    status: 'idle',
    queue: [{ id: 106, items: ['Caesar Salad', 'Greek Salad'], time: '4 min' }]
  },
  {
    id: 4,
    name: '🍕 Pizza Oven',
    status: 'busy',
    queue: [
      { id: 107, items: ['Margherita', 'Pepperoni'], time: '5 min' },
      { id: 108, items: ['Veggie Pizza'], time: '7 min' },
      { id: 109, items: ['BBQ Pizza'], time: '10 min' }
    ]
  },
  {
    id: 5,
    name: '🍰 Dessert Station',
    status: 'idle',
    queue: [{ id: 110, items: ['Chocolate Cake'], time: '3 min' }]
  },
  {
    id: 6,
    name: '🥤 Beverage Bar',
    status: 'idle',
    queue: [
      { id: 111, items: ['Coffee', 'Juice'], time: '1 min' },
      { id: 112, items: ['Smoothie'], time: '2 min' }
    ]
  }
])

const totalOrders = computed(() => {
  return counters.value.reduce((sum, counter) => sum + counter.queue.length, 0)
})

const pendingOrders = computed(() => {
  return counters.value.reduce((sum, counter) => sum + (counter.queue.length > 1 ? counter.queue.length - 1 : 0), 0)
})

const inProgressOrders = computed(() => {
  return counters.value.filter(counter => counter.queue.length > 0).length
})

const completedOrders = computed(() => {
  return 0 // This would track completed orders from the database
})

const completeOrder = (counterId, orderId) => {
  const counter = counters.value.find(c => c.id === counterId)
  if (counter) {
    const index = counter.queue.findIndex(o => o.id === orderId)
    if (index !== -1) {
      counter.queue.splice(index, 1)
      // Update status
      if (counter.queue.length === 0) {
        counter.status = 'idle'
      }
    }
  }
}
</script>

<style scoped>
.kitchen-container {
  min-height: calc(100vh - 70px);
  background: #ecf0f1;
  padding: 40px 20px;
}

.kitchen-header {
  max-width: 1400px;
  margin: 0 auto 40px;
  background: white;
  border-radius: 8px;
  padding: 30px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border-top: 4px solid #3498db;
}

.kitchen-header h1 {
  color: #1a1f2e;
  font-size: 2.2em;
  margin: 0 0 20px 0;
}

.header-stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
  gap: 20px;
}

.stat {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 15px;
  background: #f8f9fa;
  border-radius: 8px;
  border-left: 4px solid #3498db;
}

.stat-label {
  color: #666;
  font-size: 0.9em;
  margin-bottom: 5px;
}

.stat-value {
  color: #3498db;
  font-size: 1.8em;
  font-weight: bold;
}

.stat-value.pending {
  color: #e74c3c;
}

.stat-value.progress {
  color: #f39c12;
}

.stat-value.completed {
  color: #27ae60;
}

.counters-grid {
  max-width: 1400px;
  margin: 0 auto 40px;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 25px;
}

.counter-card {
  background: white;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  border-top: 4px solid #3498db;
}

.counter-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.15);
}

.counter-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  background: #2c3e50;
  color: white;
}

.counter-header h2 {
  margin: 0;
  font-size: 1.3em;
}

.counter-status {
  padding: 5px 12px;
  border-radius: 20px;
  font-size: 0.85em;
  font-weight: bold;
  background: rgba(255, 255, 255, 0.2);
  border: 1px solid rgba(255, 255, 255, 0.5);
}

.counter-status.busy {
  background: #e74c3c;
  border-color: #e74c3c;
}

.counter-status.idle {
  background: #27ae60;
  border-color: #27ae60;
}

.queue-section {
  padding: 20px;
}

.queue-section h3 {
  color: #1a1f2e;
  font-size: 1.1em;
  margin: 0 0 15px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.empty-queue {
  text-align: center;
  padding: 30px 20px;
  color: #999;
  font-size: 1.1em;
}

.queue-items {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.queue-item {
  display: flex;
  gap: 12px;
  padding: 15px;
  background: #f8f9fa;
  border-radius: 8px;
  border-left: 3px solid #bdc3c7;
  transition: all 0.3s ease;
}

.queue-item.active {
  background: #fff8e1;
  border-left-color: #f39c12;
  box-shadow: 0 3px 10px rgba(243, 156, 18, 0.2);
}

.order-number {
  display: flex;
  align-items: flex-start;
  min-width: 40px;
}

.now-badge {
  background: #e74c3c;
  color: white;
  padding: 4px 8px;
  border-radius: 4px;
  font-size: 0.75em;
  font-weight: bold;
}

.queue-position {
  color: #999;
  font-size: 0.9em;
}

.order-content {
  flex: 1;
  min-width: 0;
}

.order-id {
  color: #1a1f2e;
  font-weight: bold;
  margin: 0 0 8px 0;
  font-size: 0.95em;
}

.order-items {
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
  margin-bottom: 6px;
}

.item-badge {
  background: #3498db;
  color: white;
  padding: 3px 8px;
  border-radius: 12px;
  font-size: 0.8em;
  font-weight: 500;
}

.order-time {
  color: #999;
  font-size: 0.85em;
  margin: 0;
}

.order-actions {
  display: flex;
  gap: 8px;
  align-items: center;
}

.btn-complete {
  background: #27ae60;
  color: white;
  border: none;
  padding: 6px 12px;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
  font-size: 0.85em;
  transition: background 0.3s ease;
  white-space: nowrap;
}

.btn-complete:hover {
  background: #229954;
}

.kitchen-footer {
  max-width: 1400px;
  margin: 0 auto;
  text-align: center;
}

@media (max-width: 1200px) {
  .counters-grid {
    grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  }
}

@media (max-width: 768px) {
  .counters-grid {
    grid-template-columns: 1fr;
  }

  .kitchen-header h1 {
    font-size: 1.8em;
  }

  .header-stats {
    grid-template-columns: repeat(2, 1fr);
  }
}
</style>
