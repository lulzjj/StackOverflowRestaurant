<template>
  <div class="welcome-container">
    <div class="welcome-content">
      <div class="welcome-header">
        <h1>🍽️ Stack Overflow Restaurant</h1>
        <p class="tagline">Where code meets cuisine</p>
      </div>



      <div class="welcome-text">
        <p>Welcome to Stack Overflow Restaurant! Your favorite place for delicious food and perfect service.</p>
        <p>Choose an option below to get started:</p>
      </div>

      <div class="buttons-container">
        <router-link to="/reservations" class="btn btn-reservations">
          <span class="btn-icon">📅</span>
          <span>Make a Reservation</span>
        </router-link>
        <router-link to="/orders" class="btn btn-orders">
          <span class="btn-icon">🍴</span>
          <span>Order Meals</span>
        </router-link>
        <router-link to="/kitchen" class="btn btn-kitchen">
          <span class="btn-icon">👨‍🍳</span>
          <span>Kitchen Dashboard</span>
        </router-link>
      </div>

      <div class="features">
        <div class="feature">
          <div class="feature-icon">🎯</div>
          <h3>Quick Reservations</h3>
          <p>Reserve your table in just a few clicks</p>
        </div>
        <div class="feature">
          <div class="feature-icon">⚡</div>
          <h3>Easy Ordering</h3>
          <p>Order delicious meals online</p>
        </div>
        <div class="feature">
          <div class="feature-icon">🚀</div>
          <h3>Fast Service</h3>
          <p>We ensure the best experience for you</p>
        </div>
      </div>

      <div class="fun-facts">
        <p>🌟 Open since 2024 • 🏆 5-Star Ratings • 😋 1000+ Happy Customers</p>
      </div>
    </div>
  </div>
</template>

<script setup>
// Welcome page component - no logic needed
</script>

<style scoped>
.welcome-container {
  min-height: calc(100vh - 70px);
  display: flex;
  align-items: center;
  justify-content: center;
  background: #ecf0f1;
  padding: 20px;
}

.welcome-content {
  text-align: center;
  background: white;
  border-radius: 8px;
  padding: 50px;
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
  max-width: 800px;
  animation: slideIn 0.6s ease-out;
  border-top: 4px solid #3498db;
}

@keyframes slideIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.welcome-header {
  margin-bottom: 30px;
}

h1 {
  color: #1a1f2e;
  font-size: 2.8em;
  margin-bottom: 10px;
  animation: bounce 1s ease-out;
}

@keyframes bounce {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-10px);
  }
}

.tagline {
  color: #3498db;
  font-size: 1.3em;
  margin-bottom: 25px;
  font-style: italic;
  font-weight: 500;
}

.welcome-text {
  color: #555;
  font-size: 1.1em;
  margin: 30px 0;
  line-height: 1.6;
}

.buttons-container {
  display: flex;
  gap: 15px;
  justify-content: center;
  margin: 40px 0;
  flex-wrap: wrap;
}

.btn {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  padding: 20px 30px;
  border-radius: 8px;
  text-decoration: none;
  font-size: 1em;
  font-weight: bold;
  color: white;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  min-width: 140px;
}

.btn-icon {
  font-size: 2.5em;
}

.btn-reservations {
  background: #3498db;
}

.btn-reservations:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 25px rgba(52, 152, 219, 0.4);
}

.btn-orders {
  background: #2980b9;
}

.btn-orders:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 25px rgba(41, 128, 185, 0.4);
}

.btn-kitchen {
  background: #34495e;
}

.btn-kitchen:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 25px rgba(52, 73, 94, 0.4);
}

.features {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
  gap: 20px;
  margin: 50px 0;
  padding: 30px;
  background: #f8f9fa;
  border-radius: 8px;
  border-left: 4px solid #3498db;
}

.feature {
  padding: 20px;
  animation: fadeIn 0.8s ease-out;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: scale(0.9);
  }
  to {
    opacity: 1;
    transform: scale(1);
  }
}

.feature-icon {
  font-size: 2.5em;
  margin-bottom: 10px;
}

.feature h3 {
  color: #3498db;
  margin-bottom: 8px;
  font-size: 1.05em;
}

.feature p {
  color: #888;
  font-size: 0.9em;
  margin: 0;
}

.fun-facts {
  margin-top: 30px;
  padding: 20px;
  background: #ecf0f1;
  border-radius: 8px;
  color: #2c3e50;
  font-weight: 500;
  border-left: 4px solid #3498db;
}

@media (max-width: 768px) {
  .welcome-content {
    padding: 30px 20px;
  }

  h1 {
    font-size: 2em;
  }

  .tagline {
    font-size: 1.1em;
  }

  .hero-images {
    gap: 10px;
  }

  .image-card {
    width: 120px;
    height: 120px;
  }

  .buttons-container {
    flex-direction: column;
  }

  .btn {
    min-width: 100%;
  }

  .features {
    grid-template-columns: repeat(2, 1fr);
  }
}
</style>
