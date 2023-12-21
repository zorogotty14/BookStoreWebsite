import { defineStore} from "pinia";
import { apiUrl} from "@/api";
import type { CategoryItem} from "@/types";
export const useCategoryStore = defineStore("category", {
    state: () => ({
        categoryList: [] as CategoryItem[],
    }),
    actions: {
        async fetchCategories() {
            const response = await fetch(`${apiUrl}/categories`);
            const data = await response.json();
            this.categoryList = data as CategoryItem[];
        }
    },
    // getters
});