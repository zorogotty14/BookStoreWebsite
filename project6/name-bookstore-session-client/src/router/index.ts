import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import CategoryView from "@/views/CategoryView.vue";
import CartView from "@/views/CartView.vue";
import CheckoutView from "@/views/CheckoutView.vue";
const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/NameBookstoreSession/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/NameBookstoreSession/category/:name",
      name: "category-view",
      component: CategoryView,
    },
    {
      path: "/NameBookstoreSession/cart/",
      name: "cart-view",
      component: CartView,
    },
    {
      path: "/NameBookstoreSession/checkout/",
      name: "checkout-view",
      component: CheckoutView,
    },
  ],
});

export default router;
