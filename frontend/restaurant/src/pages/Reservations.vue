<template>
  <div class="reservations-container">
    <div class="reservations-content">
      <div class="header">
        <router-link to="/" class="back-btn">← Back</router-link>
        <h1>📅 Make a Reservation</h1>
      </div>

      <form @submit.prevent="submitReservation" class="reservation-form">
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
          <label for="email">Email *</label>
          <input
            v-model="form.email"
            type="email"
            id="email"
            placeholder="Enter your email"
            required
          />
        </div>

        <div class="form-row">
          <div class="form-group">
            <label for="date">Date *</label>
            <input
              v-model="form.date"
              type="date"
              id="date"
              required
            />
          </div>

          <div class="form-group">
            <label for="time">Time *</label>
            <input
              v-model="form.time"
              type="time"
              id="time"
              required
            />
          </div>
        </div>

        <div class="form-group">
          <label for="people">Number of People *</label>
          <input
            v-model.number="form.people"
            type="number"
            id="people"
            min="1"
            max="20"
            placeholder="How many people?"
            required
          />
        </div>

        <div class="form-group">
          <label for="comments">Special Requests</label>
          <textarea
            v-model="form.comments"
            id="comments"
            placeholder="Any special requests? (optional)"
            rows="4"
          ></textarea>
        </div>

        <button type="submit" class="submit-btn" :disabled="isLoading">
          {{ isLoading ? 'Sending...' : 'Reserve Table' }}
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
import { ref } from 'vue'
import axios from 'axios'

const form = ref({
  name: '',
  email: '',
  date: '',
  time: '',
  people: 2,
  comments: ''
})

const isLoading = ref(false)
const successMessage = ref('')
const errorMessage = ref('')

const submitReservation = async () => {
  isLoading.value = true
  successMessage.value = ''
  errorMessage.value = ''

  try {
    // Submit reservation to backend
    const response = await axios.post('http://localhost:3000/api/reservations', {
      name: form.value.name,
      email: form.value.email,
      date: form.value.date,
      time: form.value.time,
      numberOfPeople: form.value.people,
      comments: form.value.comments
    })

    successMessage.value = 'Reservation confirmed! We look forward to seeing you.'
    
    // Reset form
    form.value = {
      name: '',
      email: '',
      date: '',
      time: '',
      people: 2,
      comments: ''
    }
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Failed to make reservation. Please try again.'
    console.error('Reservation error:', error)
  } finally {
    isLoading.value = false
  }
}
</script>

<style scoped>
.reservations-container {
  min-height: calc(100vh - 70px);
  background: #ecf0f1;
  padding: 40px 20px;
}

.reservations-content {
  max-width: 500px;
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

.reservation-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 15px;
}

label {
  color: #1a1f2e;
  font-weight: 600;
  margin-bottom: 8px;
  font-size: 0.95em;
}

input,
textarea {
  padding: 12px;
  border: 2px solid #bdc3c7;
  border-radius: 6px;
  font-size: 1em;
  font-family: inherit;
  transition: border-color 0.3s ease;
}

input:focus,
textarea:focus {
  outline: none;
  border-color: #3498db;
}

textarea {
  resize: vertical;
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
  margin-top: 10px;
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
  .reservations-content {
    padding: 30px 20px;
  }

  .form-row {
    grid-template-columns: 1fr;
  }

  h1 {
    font-size: 1.5em;
  }
}
</style>
