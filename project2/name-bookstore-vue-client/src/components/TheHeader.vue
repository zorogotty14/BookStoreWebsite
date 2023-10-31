<script setup lang="ts">
import TheHeaderDropdown from "./TheHeaderDropdown.vue";
import TheHeaderSearchBar from "@/components/TheHeaderSearchBar.vue";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  list-style: none;
  text-decoration: none;
}


.wrapper .top_nav{
  margin-top: 5px;
  width: 100%;
  height: 65px;
  background: #fff;
  padding: 0 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .top_nav .left{
  display: flex;
  align-items: center;
}


.wrapper .top_nav .right ul{
  display: flex;
}

.wrapper .top_nav .right ul li{
  margin-right: 0;
  border-radius: 2px;
  text-transform: uppercase;
  letter-spacing: 3px;
  background-color: #333;
}

.wrapper .top_nav .right ul li:hover{
  background-color: red
}

.wrapper .top_nav .right ul li a{
  display: block;
  padding: 8px 10px;
  color: #f9f9f9;
}


.wrapper .banner img{
  width: 100%;
  height: 400px;
}


#nav-cart-count {
  background-color: red;
  color: white;
  border-radius: 50%;
  height: 15px;
  width: 15px;
  font-size: 15px;
  align-items: center;

  display: flex;
  justify-content: center;

  position: absolute;
  margin-left: 10px;
}
@media (max-width: 1200px) {

  .wrapper .top_nav{
    display: flex;
    flex-direction: column;
    position: relative;
    right: 10px;
    margin-bottom: 10px;
    width: 100%;
    height: auto;
  }
  .wrapper .top_nav .right {
    display: flex;
    flex-direction: column;
    margin-left: 10px;
  }

}
</style>

<template>
  <header class="container">
      <div class="wrapper">
      <div class="top_nav">
        <div class="left">
          <div class="bookstore-logo">
            <router-link to="/NameBookstoreVue/">
            <img
                src="/site-images/bookstore-logo.png"
                alt="Another Bookstore Logo"
                width="150"
                height="auto"
            />
          </router-link>
          </div>
          <the-header-search-bar></the-header-search-bar>
        </div>
        <the-header-dropdown></the-header-dropdown><br>
        <div class="right">
          <ul>
            <li><router-link to="/NameBookstoreVue/">Hi Reader! <i class="fa-solid fa-user"></i></router-link></li>
            <li><router-link to="/NameBookstoreVue/"><i class="fa-solid fa-download"></i>watchList</router-link></li>
            <li> <span id="nav-cart-count">5</span> <router-link to="/NameBookstoreVue/"><i class="fa-solid fa-cart-shopping"></i></router-link></li>
          </ul>
        </div>
      </div>
      </div>

  </header>
</template>
