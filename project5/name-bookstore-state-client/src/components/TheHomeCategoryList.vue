<script setup lang="ts">
import type { CategoryItem } from "@/types";
import { useCategoryStore} from "@/stores/category";
const categoryStore = useCategoryStore();
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
    <li v-for="category in categoryStore.categoryList" :key="category.categoryId">
      <router-link :to="'/NameBookstoreState/category/' + category.name">
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
