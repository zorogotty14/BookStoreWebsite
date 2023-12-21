<script setup lang="ts">
import { useCategoryStore} from "@/stores/category";
const categoryStore = useCategoryStore();
</script>

<style scoped>
.header-dropdown {
  display: flex;
  position: relative;
}

.categories-button {
  margin-top: 0.25em;
  background-color: var(--primary-color-dark);
  z-index: 1;
}

.header-dropdown ul {
  display: none;
  position: absolute;
  top: 100%;
  left: 0;
  background-color: white;
  z-index: 2;
}

.header-dropdown li {
  display: block;
  padding: 0.25em 0.5em;
}

.header-dropdown a {
  display: block;
  color: black;
  text-decoration: none;
}

.header-dropdown a:hover {
  background: lightgray;
}

.header-dropdown:hover ul {
  display: block;
}
@media (max-width: 700px) {
  .header-dropdown {
    display: flex;
    flex-wrap: wrap;
    position: relative;
    right: 10px;
  }
}
</style>

<template>
  <div class="header-dropdown">
    <button class="button categories-button"><i class="fa-solid fa-list"></i> Categories</button>
    <ul>

      <li v-for="category in categoryStore.categoryList" :key="category.categoryId">
        <router-link :to="'/category/' + category.name">
          {{ category.name }}
        </router-link>
      </li>

    </ul>
  </div>
</template>
