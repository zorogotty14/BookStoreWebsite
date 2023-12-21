<script setup lang="ts">
import type { BookItem } from "@/types";
import TheCategoryNav from "@/components/TheCategoryNav.vue";
import TheCategoryBookList from "@/components/TheCategoryBookList.vue";
import { useRoute } from "vue-router";
import { usebookStore } from "@/stores/book";
import { watch} from "vue";

const route = useRoute();
const bookStore = usebookStore();

watch(() => route.params.name,
    (newCategoryName) => {
      bookStore.fetchBooks(newCategoryName as string);
    },
    { immediate: true}
);
bookStore.fetchBooks(route.params.name as string);
</script>

<style scoped>


</style>

<template>
  <div>
    <the-category-nav></the-category-nav>
    <the-category-book-list> </the-category-book-list>
  </div>
</template>
