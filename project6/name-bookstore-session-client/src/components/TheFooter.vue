<script setup lang="ts">
</script>

<style scoped>
footer {
  display: flex;
  background-color: #333;
  flex-direction: row;
  gap: 1em;
  padding: 10px;
  margin: 10px;
}

.footer-wrapper .footer-nav{
  margin-top: 5px;
  width: 100%;
  height: 80px;
  background: #333;
  padding: 0 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.footer-wrapper .footer-nav .footer-left{
  display: flex;
  align-items: center;
}

.footer-wrapper .footer-nav .footer-left .footer-bookstore-logo {
  max-width: 100%;
  height: 80px;
  display: block;
  background-color: white;
  align-items: center;
}

.footer-wrapper .footer-nav .footer-left .footer-bookstore-logo img{
  height: 100px;
  width: 100px;
}

.footer-wrapper .footer-nav .footer-right ul{
  display: flex;
}

.footer-wrapper .footer-nav .footer-right ul li{
  margin-right: 0;
  border-radius: 2px;
  text-transform: uppercase;
  letter-spacing: 3px;
}

.footer-wrapper .footer-nav .footer-right ul li:hover{
  background-color: red;
}

.footer-wrapper .footer-nav .footer-right ul li a{
  display: block;
  padding: 8px 10px;
  color: white;
}
.footer-bookstore-logo-img{
  min-width: 100px;
  min-height: 100px;
  height: 100px;
  width: 100px;
  margin: 10px;
}

@media (max-width: 700px) {
  .footer-wrapper .footer-nav{
    display: flex;
    flex-wrap: wrap;
    position: relative;
    right: 10px;
    width: 100%;
    height: auto;
  }
  .footer-wrapper .footer-nav .footer-right ul {
    display: flex;
    flex-wrap: wrap;
    position: relative;
    right: 10px;
  }
}
</style>

<template>
  <footer class="footer">
    <div class="footer-wrapper">
      <div class="footer-nav">
        <div class="footer-left">
          <div class="footer-bookstore-logo">
            <router-link to="/NameBookstoreSession/">
              <img
                  src="/site-images/bookstore-logo.png"
                  alt="Prime Books Logo"
                  class="footer-bookstore-logo-img"
              />
            </router-link>
            </div>
        </div>
        <div class="footer-right">
          <ul>
            <li> <router-link to="/NameBookstoreSession/">
                       About Us
              </router-link></li>
            <li><router-link to="/NameBookstoreSession/">
              Contact Us
            </router-link></li>
            <li><router-link to="/NameBookstoreSession/">
              Directions
            </router-link></li>
            <li><router-link to="/NameBookstoreSession/"><i class="fa-brands fa-facebook"></i>
            </router-link></li>
            <li><router-link to="/NameBookstoreSession/"><i class="fa-brands fa-x-twitter"></i>
            </router-link></li>
            <li><router-link to="/NameBookstoreSession/"><i class="fa-brands fa-linkedin"></i>
            </router-link></li>
            <li><router-link to="/NameBookstoreSession/"><i class="fa-brands fa-github"></i>
            </router-link></li>
            <li><router-link to="/NameBookstoreSession/"><i class="fa-brands fa-instagram"></i>
            </router-link></li>
          </ul>
        </div>
      </div>
    </div>
  </footer>

</template>
