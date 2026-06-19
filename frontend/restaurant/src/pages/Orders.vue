<template>
  <div class="orders-container">
    <div class="orders-content">
      <div class="header">
        <router-link to="/" class="back-btn">← Back</router-link>
        <h1>🍴 Order Meals</h1>
      </div>

      <form @submit.prevent="submitOrder" class="order-form">
        <div class="menu-section">
          <h2>Select Your Meals</h2>
          <div class="menu-items">
            <div v-for="item in menuItems" :key="item.id" class="menu-item">
              <label class="item-label">
                <div class="item-info">
                  <h3>{{ item.name }}</h3>
                  <p class="item-description">{{ item.description }}</p>
                  <p class="item-price">${{ item.price.toFixed(2) }}</p>
                </div>
                <div class="item-controls">
                  <input
                    v-model.number="item.quantity"
                    type="number"
                    min="0"
                    class="quantity-input"
                  />
                  <span class="quantity-unit">×</span>
                </div>
              </label>
            </div>
          </div>
        </div>

        <div class="form-section">
          <div class="form-group">
            <label for="email">Email *</label>
            <input
              v-model="form.email"
              type="email"
              id="email"
              placeholder="Enter your email"
              required
            />
          </div>

          <div class="form-group">
            <label for="name">Name *</label>
            <input
              v-model="form.name"
              type="text"
              id="name"
              placeholder="Enter your name"
              required
            />
          </div>

          <div class="form-group">
            <label for="phone">Phone Number</label>
            <input
              v-model="form.phone"
              type="tel"
              id="phone"
              placeholder="Enter your phone number (optional)"
            />
          </div>

          <div class="form-group">
            <label for="deliveryType">Delivery Type *</label>
            <select v-model="form.deliveryType" id="deliveryType" required>
              <option value="">Select delivery type...</option>
              <option value="takeaway">Take Away</option>
              <option value="dine-in">Dine In (with reservation)</option>
            </select>
          </div>

          <div v-if="form.deliveryType === 'dine-in'" class="form-group">
            <label for="reservationDate">Preferred Date *</label>
            <input
              v-model="form.reservationDate"
              type="date"
              id="reservationDate"
              required
            />
          </div>

          <div class="form-group">
            <label for="comments">Special Instructions</label>
            <textarea
              v-model="form.comments"
              id="comments"
              placeholder="Any special instructions? (optional)"
              rows="3"
            ></textarea>
          </div>
        </div>

        <div class="order-summary">
          <h3>Order Summary</h3>
          <div class="summary-items">
            <div v-for="item in selectedItems" :key="item.id" class="summary-item">
              <span>{{ item.quantity }}× {{ item.name }}</span>
              <span class="summary-price">${{ (item.quantity * item.price).toFixed(2) }}</span>
            </div>
          </div>
          <div class="summary-total">
            <span>Total:</span>
            <span class="total-price">${{ totalPrice.toFixed(2) }}</span>
          </div>
        </div>

        <button type="submit" class="submit-btn" :disabled="isLoading || selectedItems.length === 0">
          {{ isLoading ? 'Submitting...' : 'Place Order' }}
        </button>
      </form>

      <div v-if="successMessage" class="success-message">
        ✓ {{ successMessage }}
      </div>

      <div v-if="errorMessage" class="error-message">
        ✗ {{ errorMessage }}
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import axios from 'axios'

const menuItems = ref([
  {
    id: 1,
    name: 'Classic Burger',
    description: 'Juicy beef burger with lettuce, tomato, and special sauce',
    price: 12.99,
    quantity: 0
  },
  {
    id: 2,
    name: 'Grilled Salmon',
    description: 'Fresh salmon fillet with seasonal vegetables',
    price: 18.99,
    quantity: 0
  },
  {
    id: 3,
    name: 'Pasta Carbonara',
    description: 'Creamy pasta with bacon and parmesan cheese',
    price: 14.99,
    quantity: 0
  },
  {
    id: 4,
    name: 'Chicken Caesar Salad',
    description: 'Grilled chicken with fresh romaine lettuce and croutons',
    price: 13.99,
    quantity: 0
  },
  {
    id: 5,
    name: 'Margherita Pizza',
    description: 'Fresh mozzarella, tomato, and basil on crispy crust',
    price: 11.99,
    quantity: 0
  },
  {
    id: 6,
    name: 'Chocolate Cake',
    description: 'Rich and moist chocolate cake with frosting',
    price: 6.99,
    quantity: 0
  },
  {
    id: 7,
    name: 'Water',
    description: 'Fresh bottled water',
    price: 2.99,
    quantity: 0
  },
  {
    id: 8,
    name: 'Juice',
    description: 'Fresh mixed fruit juice',
    price: 4.99,
    quantity: 0
  }
])

const form = ref({
  email: '',
  name: '',
  phone: '',
  deliveryType: '',
  reservationDate: '',
  comments: ''
})

const isLoading = ref(false)
const successMessage = ref('')
const errorMessage = ref('')

const selectedItems = computed(() => {
  return menuItems.value.filter(item => item.quantity > 0)
})

const totalPrice = computed(() => {
  return selectedItems.value.reduce((total, item) => total + (item.quantity * item.price), 0)
})

const submitOrder = async () => {
  if (selectedItems.value.length === 0) {
    errorMessage.value = 'Please select at least one item'
    return
  }

  isLoading.value = true
  successMessage.value = ''
  errorMessage.value = ''

  try {
    const orderItems = selectedItems.value.map(item => ({
      id: item.id,
      name: item.name,
      quantity: item.quantity,
      price: item.price
    }))

    const orderData = {
      customer: {
        name: form.value.name,
        email: form.value.email,
        phone: form.value.phone || null
      },
      items: orderItems,
      deliveryType: form.value.deliveryType,
      reservationDate: form.value.deliveryType === 'dine-in' ? form.value.reservationDate : null,
      specialInstructions: form.value.comments,
      totalPrice: totalPrice.value
    }

    // Submit order to backend
    const response = await axios.post('http://localhost:3000/api/orders', orderData)

    successMessage.value = 'Order placed successfully! You will receive a confirmation email.'
    
    // Reset form
    menuItems.value.forEach(item => item.quantity = 0)
    form.value = {
      email: '',
      name: '',
      phone: '',
      deliveryType: '',
      reservationDate: '',
      comments: ''
    }
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Failed to place order. Please try again.'
    console.error('Order error:', error)
  } finally {
    isLoading.value = false
  }
}
</script>

<style scoped>
.orders-container {
  min-height: calc(100vh - 70px);
  background: #ecf0f1;
  padding: 40px 20px;
}

.orders-content {
  max-width: 700px;
  margin: 0 auto;
  background: white;
  border-radius: 8px;
  padding: 40px;
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
  border-top: 4px solid #3498db;
}

.header {
  display: flex;
  align-items: center;
  gap: 15px;
  margin-bottom: 30px;
  position: relative;
}

.back-btn {
  position: absolute;
  left: 0;
  color: #3498db;
  text-decoration: none;
  font-weight: bold;
  font-size: 1.1em;
  transition: color 0.3s ease;
}

.back-btn:hover {
  color: #2980b9;
}

h1 {
  color: #1a1f2e;
  font-size: 1.8em;
  margin: 0;
  text-align: center;
  flex: 1;
}

h2 {
  color: #1a1f2e;
  font-size: 1.3em;
  margin-bottom: 20px;
}

.order-form {
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.menu-section {
  border: 2px solid #ecf0f1;
  border-radius: 8px;
  padding: 20px;
}

.menu-items {
  display: grid;
  grid-template-columns: 1fr;
  gap: 15px;
}

.menu-item {
  border: 1px solid #bdc3c7;
  border-radius: 6px;
  padding: 15px;
  transition: border-color 0.3s ease, background 0.3s ease;
}

.menu-item:hover {
  border-color: #3498db;
  background: #f8f9fa;
}

.item-label {
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;
  gap: 15px;
}

.item-info {
  flex: 1;
  text-align: left;
}

.item-info h3 {
  color: #1a1f2e;
  margin: 0 0 5px 0;
  font-size: 1.1em;
}

.item-description {
  color: #666;
  font-size: 0.9em;
  margin: 5px 0;
}

.item-price {
  color: #3498db;
  font-weight: bold;
  font-size: 1.1em;
  margin: 5px 0 0 0;
}

.item-controls {
  display: flex;
  align-items: center;
  gap: 8px;
}

.quantity-input {
  width: 60px;
  padding: 8px;
  border: 1px solid #bdc3c7;
  border-radius: 5px;
  font-size: 1em;
  text-align: center;
}

.quantity-unit {
  color: #999;
  font-size: 1.2em;
}

.form-section {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

label {
  color: #1a1f2e;
  font-weight: 600;
  margin-bottom: 8px;
  font-size: 0.95em;
}

input,
select,
textarea {
  padding: 12px;
  border: 2px solid #bdc3c7;
  border-radius: 6px;
  font-size: 1em;
  font-family: inherit;
  transition: border-color 0.3s ease;
}

input:focus,
select:focus,
textarea:focus {
  outline: none;
  border-color: #3498db;
}

select {
  cursor: pointer;
}

textarea {
  resize: vertical;
}

.order-summary {
  background: #f8f9fa;
  border: 2px solid #3498db;
  border-radius: 8px;
  padding: 20px;
}

.order-summary h3 {
  color: #1a1f2e;
  margin-top: 0;
}

.summary-items {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-bottom: 15px;
  padding-bottom: 15px;
  border-bottom: 1px solid #bdc3c7;
}

.summary-item {
  display: flex;
  justify-content: space-between;
  color: #555;
}

.summary-price {
  color: #3498db;
  font-weight: bold;
}

.summary-total {
  display: flex;
  justify-content: space-between;
  font-size: 1.2em;
  font-weight: bold;
  color: #1a1f2e;
}

.total-price {
  color: #3498db;
}

.submit-btn {
  background: #3498db;
  color: white;
  padding: 12px;
  border: none;
  border-radius: 6px;
  font-size: 1.1em;
  font-weight: bold;
  cursor: pointer;
  transition: background 0.3s ease;
}

.submit-btn:hover:not(:disabled) {
  background: #2980b9;
}

.submit-btn:disabled {
  background: #bdc3c7;
  cursor: not-allowed;
}

.success-message {
  background: #d5f4e6;
  color: #155724;
  padding: 15px;
  border-radius: 6px;
  margin-top: 20px;
  border-left: 4px solid #27ae60;
}

.error-message {
  background: #fadbd8;
  color: #721c24;
  padding: 15px;
  border-radius: 6px;
  margin-top: 20px;
  border-left: 4px solid #e74c3c;
}

@media (max-width: 600px) {
  .orders-content {
    padding: 30px 20px;
  }

  h1 {
    font-size: 1.5em;
  }

  .item-label {
    flex-direction: column;
    align-items: flex-start;
  }

  .item-controls {
    align-self: flex-end;
  }
}
</style>
