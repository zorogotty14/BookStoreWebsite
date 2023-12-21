<script setup lang="ts">
import CategoryBookListItem from "@/components/CategoryBookListItem.vue";
import { usebookStore} from "@/stores/book";
import { useCategoryStore } from "@/stores/category";

const bookStore = usebookStore();

const categoryStore = useCategoryStore();
categoryStore.fetchCategories();
</script>

<style scoped>
.category-container{
  display: flex;
  margin: 20px;
  justify-content: space-around;
}

.filters {
  flex-basis: 20%;
  background-color: #f4f4f4;
  padding: 20px;
}

.filters h2 {
  font-size: 20px;
  margin-bottom: 10px;
}

.filter-section {
  margin-bottom: 20px;
}

.filter-btn{
  display: flex;
  justify-content: space-between;
}

.filter-section h3 {
  font-size: 16px;
  margin-bottom: 10px;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  margin-bottom: 5px;
}
.arrivals{
  width: 80%;
  height: auto;
  margin-bottom: 10px;
  display: flex;
  flex-wrap: wrap;
  grid-gap: 20px 0;
}

@media (max-width: 700px) {
  .category-container {
    display: flex;
    flex-wrap: wrap;
    position: relative;
    right: 10px;
    margin-left: 10px;
  }
  .arrivals{
    width: 80%;
    height: auto;
    display: flex;
    flex-wrap: wrap;
    grid-gap: 20px 0;
  }
}

</style>

<template>
  <div class="category-container">
    <div class="filters">
      <h2>Filter Options</h2>

      <div class="filter-section">
        <div class="filter-btn"> <h3>Category</h3>
          <button>clear</button></div>
        <ul>
          <li v-for="category in categoryStore.categoryList" :key="category.categoryId">
            <input type="checkbox" id="category1"><label for="category1">{{ category.name }}</label>
          </li>
        </ul>
        <h3>Format</h3>
        <ul>
          <li><input type="checkbox" id="category1"><label for="category1">Hard Cover</label></li>
          <li><input type="checkbox" id="category2"><label for="category2">Soft Cover</label></li>
        </ul>
      </div>
      <div class="filter-section">
        <h3>Price Range</h3>
        <input type="range" id="priceRange" min="0" max="100">
        <p>Price: <span id="priceValue">0 - 100</span></p>
      </div>
      <!-- Add more filter sections as needed -->

    </div>

    <div class="arrivals">
      <template v-for="book in bookStore.bookList" :key="book.bookId">
          <category-book-list-item :book="book"></category-book-list-item>

      </template>
    </div>
  </div>
</template>
