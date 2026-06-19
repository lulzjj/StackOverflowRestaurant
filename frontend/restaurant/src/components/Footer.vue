<template>
  <footer class="footer">
    <div class="footer-content">
      <div class="footer-section">
        <h3>Stack Overflow Restaurant</h3>
        <p>Your destination for quality food and excellent service.</p>
      </div>

      <div class="footer-section">
        <h4>Quick Links</h4>
        <ul>
          <li><router-link to="/reservations">Reservations</router-link></li>
          <li><router-link to="/orders">Order Online</router-link></li>
          <li><router-link to="/kitchen">Kitchen Dashboard</router-link></li>
        </ul>
      </div>

      <div class="footer-section">
        <h4>Contact</h4>
        <p>Email: info@stackoverflowrestaurant.com</p>
        <p>Phone: +49 (0) 123 456789</p>
        <p>Address: Hauptstraße 123, 10115 Berlin</p>
      </div>

      <div class="footer-section">
        <h4>Hours</h4>
        <p>Mon - Thu: 11:00 - 23:00</p>
        <p>Fri - Sat: 11:00 - 00:00</p>
        <p>Sun: 11:00 - 22:00</p>
      </div>
    </div>

    <div class="footer-bottom">
      <div class="legal-links">
        <a href="#impressum" @click.prevent="showImpressum = !showImpressum">Impressum</a>
        <a href="#privacy">Datenschutz</a>
        <a href="#terms">AGB</a>
      </div>
      <p class="copyright">&copy; 2024 Stack Overflow Restaurant. All rights reserved.</p>
    </div>

    <div v-if="showImpressum" class="impressum-modal">
      <div class="impressum-content">
        <button @click="showImpressum = false" class="close-btn">✕</button>
        <h2>Impressum</h2>

        <h3>Angaben gemäß § 5 TMG</h3>
        <p>
          Stack Overflow Restaurant<br />
          Hauptstraße 123<br />
          10115 Berlin<br />
          Deutschland
        </p>

        <h3>Vertreten durch</h3>
        <p>Geschäftsführer: Max Mustermann</p>

        <h3>Kontakt</h3>
        <p>
          Telefon: +49 (0) 123 456789<br />
          E-Mail: info@stackoverflowrestaurant.com
        </p>

        <h3>Registereintrag</h3>
        <p>
          Eintragung im Handelsregister.<br />
          Registergericht: Amtsgericht Berlin<br />
          Registernummer: HR 123456
        </p>

        <h3>Umsatzsteuer-ID</h3>
        <p>Umsatzsteuer-Identifikationsnummer gemäß §27 a UStG: DE 123456789</p>

        <h3>Verantwortlich für den Inhalt</h3>
        <p>
          Max Mustermann<br />
          Hauptstraße 123<br />
          10115 Berlin
        </p>

        <h3>Haftungsausschluss</h3>
        <p>
          Die Inhalte unserer Seiten wurden mit größter Sorgfalt erstellt. Für die Richtigkeit, Vollständigkeit
          und Aktualität der Inhalte können wir jedoch keine Gewähr übernehmen. Als Diensteanbieter sind wir
          verantwortlich für eigene Inhalte auf diesen Seiten. Für die Richtigkeit, Vollständigkeit und
          Aktualität dieser Inhalte sind wir nicht verantwortlich.
        </p>
      </div>
    </div>
  </footer>
</template>

<script setup>
import { ref } from 'vue'

const showImpressum = ref(false)
</script>

<style scoped>
.footer {
  background: #1a1f2e;
  color: #ecf0f1;
  padding: 40px 20px 20px;
  margin-top: 60px;
  border-top: 1px solid #2c3e50;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto 30px;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 30px;
}

.footer-section h3 {
  color: #ecf0f1;
  font-size: 1.2em;
  margin-bottom: 15px;
}

.footer-section h4 {
  color: #bdc3c7;
  font-size: 1em;
  margin-bottom: 12px;
}

.footer-section p {
  color: #95a5a6;
  font-size: 0.9em;
  line-height: 1.6;
  margin-bottom: 8px;
}

.footer-section ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.footer-section ul li {
  margin-bottom: 8px;
}

.footer-section a {
  color: #3498db;
  text-decoration: none;
  transition: color 0.3s ease;
}

.footer-section a:hover {
  color: #5dade2;
}

.footer-bottom {
  max-width: 1200px;
  margin: 0 auto;
  padding-top: 20px;
  border-top: 1px solid #2c3e50;
  text-align: center;
}

.legal-links {
  margin-bottom: 15px;
}

.legal-links a {
  color: #3498db;
  text-decoration: none;
  margin: 0 15px;
  font-size: 0.9em;
  transition: color 0.3s ease;
}

.legal-links a:hover {
  color: #5dade2;
}

.copyright {
  color: #7f8c8d;
  font-size: 0.85em;
  margin: 0;
}

.impressum-modal {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.7);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 2000;
  padding: 20px;
}

.impressum-content {
  background: white;
  border-radius: 10px;
  padding: 40px;
  max-width: 600px;
  max-height: 80vh;
  overflow-y: auto;
  position: relative;
  color: #2c3e50;
}

.close-btn {
  position: absolute;
  top: 15px;
  right: 15px;
  background: none;
  border: none;
  font-size: 1.5em;
  cursor: pointer;
  color: #7f8c8d;
  transition: color 0.3s ease;
}

.close-btn:hover {
  color: #2c3e50;
}

.impressum-content h2 {
  color: #1a1f2e;
  margin-top: 0;
  margin-bottom: 25px;
  border-bottom: 2px solid #3498db;
  padding-bottom: 10px;
}

.impressum-content h3 {
  color: #1a1f2e;
  font-size: 1.05em;
  margin-top: 20px;
  margin-bottom: 10px;
}

.impressum-content p {
  color: #555;
  font-size: 0.9em;
  line-height: 1.6;
  margin-bottom: 15px;
}

@media (max-width: 768px) {
  .footer-content {
    gap: 20px;
  }

  .footer-section {
    text-align: center;
  }

  .legal-links a {
    display: block;
    margin: 8px 0;
  }

  .impressum-content {
    padding: 25px;
    max-height: 90vh;
  }
}
</style>
