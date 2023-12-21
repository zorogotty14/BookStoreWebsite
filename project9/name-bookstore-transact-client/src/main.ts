import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";

import "./assets/global.css";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faS } from "@fortawesome/free-solid-svg-icons";
library.add(faS)

const app = createApp(App).component('fa', FontAwesomeIcon);

app.use(createPinia());
app.use(router);

app.mount("#app");
