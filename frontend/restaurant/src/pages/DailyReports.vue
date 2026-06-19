<template>
  <div class="reports-container">
    <div class="reports-content">
      <div class="header">
        <router-link to="/" class="back-btn">← Back</router-link>
        <h1>📊 Daily Reports</h1>
      </div>

      <div class="reports-grid">
        <div class="report-card">
          <div class="report-header">
            <h2>📈 Sales Report</h2>
          </div>
          <div class="report-body">
            <p>Daily sales data will be displayed here</p>
          </div>
        </div>

        <div class="report-card">
          <div class="report-header">
            <h2>🍽️ Orders Report</h2>
          </div>
          <div class="report-body">
            <p>Daily orders summary will be displayed here</p>
          </div>
        </div>

        <div class="report-card">
          <div class="report-header">
            <h2>👥 Customers Report</h2>
          </div>
          <div class="report-body">
            <p>Customer activity data will be displayed here</p>
          </div>
        </div>

        <div class="report-card">
          <div class="report-header">
            <h2>⏱️ Performance Report</h2>
          </div>
          <div class="report-body">
            <p>Kitchen performance metrics will be displayed here</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// Daily reports component
</script>

<style scoped>
.reports-container {
  min-height: calc(100vh - 70px);
  background: #ecf0f1;
  padding: 40px 20px;
}

.reports-content {
  max-width: 1000px;
  margin: 0 auto;
}

.header {
  display: flex;
  align-items: center;
  gap: 15px;
  margin-bottom: 40px;
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
  font-size: 2em;
  margin: 0;
  text-align: center;
  flex: 1;
}

.reports-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 25px;
}

.report-card {
  background: white;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  border-top: 4px solid #3498db;
}

.report-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.15);
}

.report-header {
  padding: 20px;
  background: #2c3e50;
  color: white;
}

.report-header h2 {
  margin: 0;
  font-size: 1.2em;
}

.report-body {
  padding: 30px 20px;
  text-align: center;
  color: #999;
  min-height: 150px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.report-body p {
  margin: 0;
  font-size: 1em;
}

@media (max-width: 768px) {
  .reports-container {
    padding: 20px;
  }

  .reports-grid {
    grid-template-columns: 1fr;
  }

  h1 {
    font-size: 1.5em;
  }
}
</style>
