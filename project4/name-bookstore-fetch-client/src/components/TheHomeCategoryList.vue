<script setup lang="ts">
import type { CategoryItem } from "@/types";
let response = await fetch("http://localhost:8080/NameBookstoreFetch/api/categories");
let data = await response.json();
let categoryList = data as CategoryItem[];
const categoryImageFileName = function (category: CategoryItem): string {
  let name = category.name.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.jpg`;
};
</script>

<style scoped>
ul {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 1em;
  padding-left: 100px;
}

li {
  text-align: center;
  cursor: pointer;
}

li div {
  margin-bottom: -2em;
  padding: 0.5em 0;
  background: rgba(0, 0, 0, 0.5); /* last # is percent opacity */
  color: white;
  transform: translateY(-2.25em);
}
.category-img{
  height: 250px;
  width: 200px;
}
</style>

<template>
  <ul>
    <li v-for="category in categoryList" :key="category.categoryId">
      <router-link :to="'/NameBookstoreFetch/category/' + category.name">
        <img
            :src="'category-images/' + categoryImageFileName(category)"
            :alt="category.name + ' category'"
            class="category-img"
        />
        <div>{{ category.name }}</div>
      </router-link>
    </li>
  </ul>
</template>
