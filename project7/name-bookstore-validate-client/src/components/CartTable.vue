<script setup lang="ts">
import type { BookItem } from "@/types";
import { useCartStore } from "@/stores/cart";
const cartStore = useCartStore();

const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.gif`;
};
const bookImagePrefix = `${import.meta.env.BASE_URL}/book-images`;

const updateCart = function (book: BookItem, quantity: number) {
  cartStore.cart.update(book, quantity);
};



</script>

<style scoped>
.cart-table {
  display: grid;
  grid-template-columns: max-content minmax(10em, 20em) repeat(3, max-content);
  row-gap: 1em;
  width: fit-content;
  margin: 0 auto;
  background-color: white;
}

ul {
  display: contents;
}

ul > li {
  display: contents;
}

.table-heading {
  background-color: #333;
  color: white;
}

.table-heading > * {
  background-color: #333;
  padding: 0.5em;
}

.heading-book {
  grid-column: 1/ 2;
  padding-left: 40px;
}
.heading-title {
  grid-column: 2/ 3;
  padding-left: 100px;
}
.heading-quantity {
  grid-column: 4/ 5;
  padding-left: 20px;
}
.heading-price {
  grid-column: 3 / 4;
  text-align: right;
}

.heading-subtotal {
  padding-left: 20px;
  grid-column: -2 / -1;
}

img {
  display: block;
  width: 100px;
  height: auto;
}

.cart-book-price {
  padding-left: 1em;
  text-align: right;
}

.cart-book-quantity {
  padding-left: 1em;
  padding-right: 1em;
}

.cart-book-subtotal {
  text-align: right;
  padding-left: 1em;
  padding-right: 1em;
}

/* Row separators in the table */

.line-sep {
  display: block;
  height: 2px;
  background-color: black;
  grid-column: 1 / -1;
}

/* Increment/decrement buttons */

.icon-button {
  border: none;
  cursor: pointer;
}

.inc-button {
  font-size: 1.125rem;
  color: var(--primary-color);
  margin-right: 0.25em;
}

.inc-button:hover {
  color: var(--primary-color-dark);
}

.dec-button {
  font-size: 1.125rem;
  color: #ccc;
}

.dec-button:hover {
  color: #aaa;
}


.cart-book-image {
  width: 100px;
  height: 150px;
  cursor: pointer;
  box-shadow: 0 0 8px rgba(0, 0, 0, 0.5);
  overflow: hidden;
  margin-left: 10px;
}

.cart-book-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
  transition: 0.3s;
}

.cart-book-image img {
  transform: scale(1.1);
}
.cart-book-title{
  margin-left: 20px;
}
.quantity{
  font-size: 20px;
}
</style>

<template>
  <div class="cart-table" v-if="cartStore.count != 0">
    <ul>
      <li class="table-heading">
        <div class="heading-book">Book</div>
        <div class="heading-title">title</div>
        <div class="heading-price">Price </div>
        <div class="heading-quantity">Quantity </div>
        <div class="heading-subtotal">Amount</div>
      </li>
      <template v-for="item in cartStore.cart.items" :key="item.book.bookId">
        <li>
          <div class="cart-book-image">
            <img
                :src="`${bookImagePrefix}/${bookImageFileName(item.book)}`"
                :alt="item.book.title"
            />
          </div>
          <div class="cart-book-title">{{ item.book.title }}</div>
          <div class="cart-book-price">
            ${{ (item.book.price / 100).toFixed(2) }}
          </div>
          <div class="cart-book-quantity">
            <button
                class="icon-button inc-button"
                @click="updateCart(item.book, item.quantity + 1)"
            >
              <i class="fas fa-plus-circle"></i>
            </button>
            <span class="quantity">{{ item.quantity }}</span
            >&nbsp;

            <button
                class="icon-button dec-button"
                @click="updateCart(item.book, item.quantity - 1)"
            >
              <i class="fas fa-minus-circle"></i>
            </button>
          </div>
          <div class="cart-book-subtotal">${{((item.book.price/100)*(item.quantity)).toFixed(2) }}</div>
        </li>
        <li class="line-sep"></li>
      </template>
      <h1> Total: ${{(cartStore.cart.subtotal /100).toFixed(2)}}</h1>

    </ul>
  </div>
</template>

