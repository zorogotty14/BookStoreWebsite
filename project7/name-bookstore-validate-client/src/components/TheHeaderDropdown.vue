<script setup lang="ts">
import { useCategoryStore} from "@/stores/category";
const categoryStore = useCategoryStore();
</script>

<style scoped>
.header-dropdown {
  display: flex;
}

.categories-button {
  margin-top: 0.25em;
  background-color: var(--primary-color-dark);
}

.header-dropdown ul {
  display: none;
  background-color: white;
  color: black;
}

.header-dropdown li {
  padding: 0.25em 0.5em;
}

.header-dropdown a {
  color: black;
  text-decoration: none;
}

.header-dropdown li:hover {
  background: lightgray;
}

.header-dropdown:hover ul {
  position: absolute;
  z-index: 2;
  display: block;
  min-width: 8em;
  cursor: pointer;
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
        <router-link :to="'/NameBookstoreValidate/category/' + category.name">
          {{ category.name }}
        </router-link>
      </li>
    </ul>
  </div>
</template>
