<script setup lang="ts">
import CartTable from "@/components/CartTable.vue";
import { useCartStore } from "@/stores/cart";
const cartStore = useCartStore();
const clearCart = function () {
  cartStore.clearCart();
};
</script>
<style scoped>
h1 {
  margin: 1em auto;
  text-align: center;
}
.list-of-buttons-row{
  width: 100%;
  height: auto;
  display: flex;
  padding-left: 100px;
  padding-right: 100px;
}

.list-of-buttons-columns1{
  float: left;
  margin: 20px;
  margin-left: 50px;
  justify-content: center;
}

.list-of-buttons-columns2{
  float: left;
  margin-left: 400px;
  justify-content: center;
}
.list-of-buttons-columns3{
  float: left;
  margin-left: 200px;
  justify-content: center;
}
.list-of-buttons-columns4{
  float: left;
  margin-left: 45%;
  justify-content: center;
}
.continue-shopping{
  background:linear-gradient(to bottom, #ffec64 5%, #ffab23 100%);
  background-color:#ffec64;
  border-radius:7px;
  border:2px solid #ffaa22;
  display:inline-block;
  cursor:pointer;
  color:#333333;
  font-family:Arial;
  font-size:16px;
  font-weight:bold;
  padding:6px 10px;
  text-decoration:none;
}
.continue-shopping:hover {
  background:linear-gradient(to bottom, #ffab23 5%, #ffec64 100%);
  background-color:#ffab23;
}
.continue-shopping:active {
  position:relative;
  top:1px;
}

.clear_cart {
  background:linear-gradient(to bottom, #fcf9e8 5%, #edc27c 100%);
  background-color:#fcf9e8;
  border-radius:7px;
  border:2px solid #ffbb54;
  display:inline-block;
  cursor:pointer;
  color:#333333;
  font-family:Arial;
  font-size:16px;
  font-weight:bold;
  padding:6px 10px;
  text-decoration:none;
}
.clear_cart:hover {
  background:linear-gradient(to bottom, #edc27c 5%, #fcf9e8 100%);
  background-color:#edc27c;
}
.clear_cart:active {
  position:relative;
  top:1px;
}
@media (max-width: 1200px) {
  .list-of-buttons-columns1 {
    margin-left: 20px;
  }
  .list-of-buttons-columns2{
    margin-left: 50px;
    margin-top: 15px;
  }
  .list-of-buttons-columns3{
    margin-left: 50px;
    margin-top: 15px;
  }
}


</style>

<template>
  <h1>Cart Page</h1>
  <h1>Total Items: {{ cartStore.count}}</h1>
  <div class="list-of-buttons-row" v-if="cartStore.count == 0">
      <div class="list-of-buttons-columns4">
        <h1> Your Cart is empty</h1>
        <router-link to="/nameBookstoreOrder/category/Featured">
          <button class="continue-shopping" value="continue-shopping">continue-shopping</button>
        </router-link>
      </div>
  </div>
  <div class="list-of-buttons-row" v-else>
  <div class="list-of-buttons-columns1">
    <button class="clear_cart" value="Clear Cart" @click="clearCart()">clear Cart</button>
  </div>
  <div class="list-of-buttons-columns2">
    <router-link to="/nameBookstoreOrder/category/Featured">
      <button class="continue-shopping" value="continue-shopping">continue-shopping</button>
    </router-link>
  </div>
  <div class="list-of-buttons-columns3">
    <router-link to="/nameBookstoreOrder/checkout/">
      <button class="continue-shopping" value="proceed-to-checkout">Proceed to Checkout</button>
    </router-link>
  </div>
  </div>

  <cart-table></cart-table>
</template>