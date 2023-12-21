import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import CategoryView from "@/views/CategoryView.vue";
import CartView from "@/views/CartView.vue";
import CheckoutView from "@/views/CheckoutView.vue";
import ConfirmationView from "@/views/ConfirmationView.vue";
const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/nameBookstoreOrder/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/nameBookstoreOrder/category/:name",
      name: "category-view",
      component: CategoryView,
    },
    {
      path: "/nameBookstoreOrder/cart/",
      name: "cart-view",
      component: CartView,
    },
    {
      path: "/nameBookstoreOrder/checkout/",
      name: "checkout-view",
      component: CheckoutView,
    },
    {
      path: "/nameBookstoreOrder/confirmation/",
      name: "confirmation-view",
      component: ConfirmationView,
    },
  ],
});

export default router;
