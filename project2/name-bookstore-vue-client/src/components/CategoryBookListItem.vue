<script setup lang="ts">
import { defineProps } from "vue";
import type { BookItem } from "@/types";
const bookImagePrefix = `${import.meta.env.BASE_URL}/book-images`;
const props = defineProps<{
  book: BookItem;
}>();
const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.gif`;
};
</script>
<style scoped>
@media (max-width: 1200px) {
  .arrivals_box {
    flex-direction: column;
    position: relative;
    margin-left: 20px;
  }
  .arrivals_box .arrivals_card {
    flex-direction: column;
    position: relative;

  }
}
  .arrivals_box .arrivals_card {
    width: 250px;
    height: 400px;
    text-align: center;
    padding: 5px;
    border: 1px solid #919191;
    background-color: #aaa;;
    margin-bottom: auto;
  }

  .arrivals_box .arrivals_card:hover {
    box-shadow: 0 0 5px #ffab23;
  }

  .arrivals_box .arrivals_card .arrivals_image {
    width: 100px;
    height: 150px;
    margin: 0 auto;
    cursor: pointer;
    box-shadow: 0 0 8px rgba(0, 0, 0, 0.5);
    overflow: hidden;
  }

  .arrivals_box .arrivals_card .arrivals_image img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    object-position: center;
    transition: 0.3s;
  }

  .arrivals_box .arrivals_card:hover .arrivals_image img {
    transform: scale(1.1);
  }

  .arrivals_box .arrivals_card .arrivals_image .offer-btn-image {
    position: inherit;
    transform: translate(50%, -80%);
    box-shadow: inset 0 1px 0 0 #fff6af;
    background: linear-gradient(to bottom, #ffec64 5%, #ffab23 100%);
    background-color: #ffec64;
    border-radius: 6px;
    border: 1px solid #ffaa22;
    display: inline-block;
    cursor: pointer;
    color: #333333;
    font-family: Arial;
    font-size: 15px;
    font-weight: bold;
    padding: 6px 24px;
    padding-top: 10px;
    text-decoration: none;
    text-shadow: 0 1px 0 #ffee66;
  }

  .arrivals_box .arrivals_card .arrivals_image .offer-btn-image:hover {
    background: linear-gradient(to bottom, #ffab23 5%, #ffec64 100%);
    background-color: #ffab23;
  }

  .arrivals_box .arrivals_card .arrivals_image .offer-btn-image:active {
    position: relative;
    top: 1px;
  }

  .arrivals_box .arrivals_card .arrivals_tag p {
    font-family: var(--default-font-family);
    font-size: 20px;
    padding-bottom: 2px;
  }

  .offer-btn {
    box-shadow: inset 0 1px 0 0 #fff6af;
    background: linear-gradient(to bottom, #ffec64 5%, #ffab23 100%);
    background-color: #ffec64;
    border-radius: 6px;
    border: 1px solid #ffaa22;
    display: inline-block;
    cursor: pointer;
    color: #333333;
    font-family: Arial;
    font-size: 15px;
    font-weight: bold;
    padding: 6px 24px;
    padding-top: 10px;
    text-decoration: none;
    text-shadow: 0 1px 0 #ffee66;
  }

  .offer-btn:hover {
    background: linear-gradient(to bottom, #ffab23 5%, #ffec64 100%);
    background-color: #ffab23;
  }

  .offer-btn:active {
    position: relative;
    top: 1px;
  }
</style>

<template>
  <div class="arrivals_box">
    <div class="arrivals_card">
      <div class="arrivals_image">
        <img
            :src="`${bookImagePrefix}/${bookImageFileName(book)}`"
            :alt="book.title"
        />
        <button class="offer-btn-image" v-if="book.isPublic">
          <i class="fa-solid fa-book"></i>
        </button>
      </div><br>
      <div class="arrivals_tag">
        <p>Title: {{ book.title }}</p>
        <p>by: {{ book.author }}</p><br>
        <p>Price: ${{ (book.price / 100).toFixed(2) }}</p><br>
        <button class="offer-btn">
          Add to cart
        </button>
      </div>
    </div>

  </div>
</template>
