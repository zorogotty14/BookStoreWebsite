<script setup lang="ts">
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
</script>

<style scoped>
.search_bar input[type="text"]{
  border: 1px solid #666666;
  padding: 5px 10px;
  outline: none;
  width: 350px;
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

  .search_bar input[type="text"]{
   margin-right: 10px;
    width: 50px;
  }
  .offer-btn{
    width: 10px;
    margin-right: 10px;
  }
}
</style>

<template>
          <form>
            <div class="search_bar">
              <input type="text" placeholder="Search"/>
              <button class="offer-btn"><i class="fa-solid fa-magnifying-glass"></i></button>
            </div>
          </form>

</template>
