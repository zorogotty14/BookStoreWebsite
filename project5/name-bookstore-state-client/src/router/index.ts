import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import CategoryView from "@/views/CategoryView.vue";
const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/NameBookstoreState/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/NameBookstoreState/category/:name",
      name: "category-view",
      component: CategoryView,
    },
  ],
});

export default router;
