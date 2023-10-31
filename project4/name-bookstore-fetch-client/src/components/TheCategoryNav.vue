<script setup lang="ts">
import { inject } from "vue";
import type { CategoryItem } from "@/types";
const categoryList = inject("categoryList") as CategoryItem[];
</script>

<style scoped>
.category-buttons {
  display: flex;
  flex-direction: row;
  background-color: #333;
  text-align: center;
}

.button.category-button.router-link-active {
  background-color: red;
}

.button.category-button {
  background-color: #333;
}

.button.category-button:hover,
.button.category-button:active {
  background-color: red;
}
@media (max-width: 700px) {
  .category-buttons {
    display: flex;
    flex-wrap: wrap;
    right: 10px;
  }
}
</style>

<template>
  <nav class="category-nav">
    <ul class="category-buttons">
      <li v-for="category in categoryList" :key="category.categoryId">
        <router-link
          :to="'/NameBookstoreFetch/category/' + category.name"
          class="button category-button"
        >
          {{ category.name }}
        </router-link>
      </li>
    </ul>
  </nav>

</template>
