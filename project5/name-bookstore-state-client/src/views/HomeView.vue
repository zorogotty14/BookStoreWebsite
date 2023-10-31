<script setup lang="ts">
import TheHomeCategoryList from "@/components/TheHomeCategoryList.vue";
</script>

<style scoped>
.home-page {
  display: flex;
}
.offers{
  display: flex;
  justify-content: center;
}

.offer-column {
  float: left;
  width: 400px;
  height: 220px;
  margin: 20px;
  padding-left: 60px;
  padding-top: 30px;
  justify-content: center;
  background-color:#aaa;

}

.offer-column h1{
  padding-top: 10px;
  padding-bottom: 10px;
}
.offer-row{
  width: 100%;
  height: auto;
  display: flex;
  padding-left: 100px;
  padding-right: 100px;
}

.offer-btn {
  box-shadow:inset 0px 1px 0px 0px #fff6af;
  background:linear-gradient(to bottom, #ffec64 5%, #ffab23 100%);
  background-color:#ffec64;
  border-radius:6px;
  border:1px solid #ffaa22;
  display:inline-block;
  cursor:pointer;
  color:#333333;
  font-family:Arial;
  font-size:15px;
  font-weight:bold;
  padding:6px 24px;
  padding-top: 10px;
  text-decoration:none;
  text-shadow:0px 1px 0px #ffee66;
}
.offer-btn:hover {
  background:linear-gradient(to bottom, #ffab23 5%, #ffec64 100%);
  background-color:#ffab23;
}
.offer-btn:active {
  position:relative;
  top:1px;
}


@media (max-width: 1200px) {
  .home-page {
    flex-direction: column;
    position: relative;
    right: 10px;
  }
  .offer-row{
    display: flex;
    flex-direction: column;
    height: auto;
  }
}
</style>
<template>
  <div class="home-page">
    <section class="offers">
      <div class="offer-row">
        <div class="offer-column">
          <h1>Buy 3 and get 20% off</h1>
          <router-link to="/NameBookstoreState/category/Featured">
            <button class="offer-btn">Shop Now</button>
          </router-link>
          <h1>Buy 4 and get 30% off</h1>
          <router-link to="/NameBookstoreState/category/Featured">
            <button class="offer-btn">Shop Now</button>
          </router-link>
        </div>
        <div class="offer-column">
          <h1>Collectible spotlight</h1> <br>
          <router-link to="/NameBookstoreState/category/Collectible-Books">
            <button class="offer-btn">Shop Now</button>
          </router-link>
          <br> <h1>Rare and Vintage books</h1>
        </div>
        <div class="offer-column">
          <h1>Back to School Guide</h1> <br>
          <router-link to="/NameBookstoreState/category/science-math">
            <button class="offer-btn">Shop Now</button>
          </router-link>
          <br> <h1>Offers start at $4.99</h1>
        </div>
      </div>
    </section>
  </div>
  <div class="home-page">

    <section class="category-images container">
      <the-home-category-list></the-home-category-list>
    </section>
  </div>
</template>
