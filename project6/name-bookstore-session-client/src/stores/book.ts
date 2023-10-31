import { defineStore} from "pinia";
import { apiUrl} from "@/api";
import type { BookItem} from "@/types";
export const usebookStore = defineStore("book", {
    state: () => ({
        bookList: [] as BookItem[],
    }),
    actions: {
        async fetchBooks( categoryName: string) {
            const response = await fetch(`${apiUrl}/categories/name/${categoryName}/books`);
            const data = await response.json();
            this.bookList = data as BookItem[];
        }
    },
    // getters
});