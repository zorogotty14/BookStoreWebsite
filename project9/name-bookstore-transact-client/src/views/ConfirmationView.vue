<script setup lang="ts">
import { useCategoryStore } from "@/stores/category";
const categoryStore = useCategoryStore();

import { useOrderDetailsStore } from "@/stores/orderDetails";
import { computed } from "vue";
import {useCartStore} from "@/stores/cart";
import type { BookItem } from "@/types";
const cartStore = useCartStore();
const cart = cartStore.cart;

const orderDetailsStore = useOrderDetailsStore();
const orderDetails = orderDetailsStore.orderDetails;

const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.gif`;
};
const bookImagePrefix = `${import.meta.env.BASE_URL}/book-images`;

var vat = 0;

if (orderDetails && orderDetails.order) {
  vat = orderDetails.order.amount / 400 - 4.95;
} else {
  vat = 0;
}


const orderDate = computed(function () {
  let date = new Date(orderDetails.order.dateCreated);
  return date.toLocaleDateString('en-US', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: 'numeric',
    minute: 'numeric',
    second: 'numeric',
  });
});

const ccExpDate = computed(function (): Date {
  return new Date(orderDetails.customer.ccExpDate);
});

const ccExpYear = computed(function (): number {
  return ccExpDate.value.getFullYear();
});

const ccExpMonth = computed(function (): string {
  let month = ccExpDate.value.getMonth() + 1;
  let result = "" + month;
  if (month < 10) {
    result = "0" + month;
  }
  return result;
});
</script>

<style scoped>
.container {
  height: auto;
  -webkit-box-pack: center;
  -webkit-justify-content: center;
  -ms-flex-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  -webkit-align-items: center;
  -ms-flex-align: center;
  align-items: center;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  background: -webkit-linear-gradient(#c5e5e5, #ccddf9);
  background: linear-gradient(#c9e5e9, #ccddf9);
}


.window {
  height:auto;
  width:800px;
  background:#fff;
  display:-webkit-box;
  display:-webkit-flex;
  display:-ms-flexbox;
  display:flex;
  box-shadow: 0px 15px 50px 10px rgba(0, 0, 0, 0.2);
  border-radius:30px;
  z-index:10;
}
.order-info {
  height:auto;
  width:50%;
  padding-left:25px;
  padding-right:25px;
  box-sizing:border-box;
  display:-webkit-box;
  display:-webkit-flex;
  display:-ms-flexbox;
  display:flex;
  -webkit-box-pack:center;
  -webkit-justify-content:center;
  -ms-flex-pack:center;
  justify-content:center;
  position:relative;
}
.price {
  bottom:0px;
  position:absolute;
  right:0px;
  color:#4488dd;
}
.order-table td:first-of-type {
  width:25%;
}
.order-table {
  position:relative;
}
.line {
  height:1px;
  width:100%;
  margin-top:10px;
  margin-bottom:10px;
  background:#ddd;
}
.order-table td:last-of-type {
  vertical-align:top;
  padding-left:25px;
}
.order-info-content {
  table-layout:fixed;
}
.order-info-content h2{
  padding-top: 10px;
}

.full-width {
  width:100%;
}

.total {
  margin-top:40px;
  font-size:20px;
  font-size:1.3rem;
  position:relative;
}
.dense {
  line-height:1.2em;
  font-size:16px;
  font-size:1rem;
}

.list-of-buttons-columns4{
  float: left;
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
</style>

<template>
  <div>
    <!-- Check if orderDetails is defined and has books -->
    <div class='container' v-if="orderDetails.books && orderDetails.books.length > 0">
      <div class='window'>
        <div class='order-info'>
          <div class='order-info-content'>
            <h2>Confirmation Summary</h2>
            <div class='line'></div>
            <template v-for="item in orderDetails.books" :key="item.bookId">
              <table class='order-table'>
                <tbody>
                <tr v-if="item">
                  <td>
                    <img :src="`${bookImagePrefix}/${bookImageFileName(item)}`"
                         :alt="item.title" class='full-width'/>
                  </td>
                  <td>
                    <br><span class='thin'>Title: {{ item.title }}</span>
                    <br>Author: {{ item.author }}<br>
                  </td>
                </tr>
                <tr v-if="item">
                  <td>
                    <div class='price'>${{ (item.price / 100).toFixed(2) }}</div>
                  </td>
                </tr>
                </tbody>
              </table>
              <div class='line'></div>
            </template>

            <div class='total'>
              <span style='float:left;'>
                <div class='thin dense'>VAT 4%</div>
                <div class='thin dense'>Delivery</div>
                TOTAL
              </span>
              <span style='float:right; text-align:right;'>
                <div class='thin dense'>${{ vat ? vat.toFixed(2) : 'N/A' }}</div>
                <div class='thin dense'>$4.95</div>
                ${{ orderDetails.order ? (orderDetails.order.amount ? (orderDetails.order.amount / 100).toFixed(2) : 'N/A') : 'N/A' }}
              </span>
            </div>
          </div>
        </div>

        <!-- Check if orderDetails.order is defined -->
        <div class='order-info' v-if="orderDetails.order && orderDetails.customer">
          <div class='order-info-content'>
            <h1>Customer Information</h1>
            <h2>Order Confirmation Number: {{ orderDetails.order.confirmationNumber }}</h2>
            <h2>Date: {{  orderDate }}</h2>
            <h2>Total cost Paid: ${{ orderDetails.order.amount ? (orderDetails.order.amount / 100).toFixed(2) : 'N/A' }}</h2>
            <h2>Phone Number: {{ orderDetails.customer.phone }}</h2>
            <h2>Email: {{ orderDetails.customer.email }}</h2>
            <h2>Name: {{ orderDetails.customer.customerName }}</h2>
            <h2>Address: {{ orderDetails.customer.address }}</h2>
            <template v-if="orderDetails.customer.ccNumber.length === 16">
              <h2> Booked from card: XXXX-XXXX-XXXX-{{ orderDetails.customer.ccNumber.slice(orderDetails.customer.ccNumber.length - 4, orderDetails.customer.ccNumber.length) }} </h2>
            </template>
            <template v-else-if="orderDetails.customer.ccNumber.length === 15">
              <h2> Booked from card: XXXX-XXXXXX-X- {{ orderDetails.customer.ccNumber.slice(orderDetails.customer.ccNumber.length - 4, orderDetails.customer.ccNumber.length) }} </h2>
            </template>
          </div>
        </div>
      </div>
    </div>

    <div class="container" v-else>
      <div class="list-of-buttons-columns4">
        <h1>Your checkout is not completed, Please checkout</h1>
        <router-link to="/checkout">
          <button class="continue-shopping" value="continue-shopping">Go To Checkout</button>
        </router-link>
      </div>
    </div>
  </div>
</template>