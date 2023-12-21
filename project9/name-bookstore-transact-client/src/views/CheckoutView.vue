<script setup lang="ts">
import {reactive, ref} from "vue";
import useVuelidate from "@vuelidate/core";
import {email, helpers, maxLength, minLength, required} from "@vuelidate/validators";
import {useCartStore} from "@/stores/cart";
import {isCreditCard, isMobilePhone} from "@/validators";
import router from "@/router";

const cartStore = useCartStore();
const cart = cartStore.cart;

import type {BookItem, OrderDetails, ServerErrorResponse} from "@/types";
import CheckoutFieldError from "@/components/CheckoutFieldError.vue";
import { useOrderDetailsStore } from "@/stores/orderDetails";

const orderDeatilsStore = useOrderDetailsStore();
const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.gif`;
};
const bookImagePrefix = `${import.meta.env.BASE_URL}/book-images`;


const totalAmount = cart.total;
const vat = cart.surcharge;



const months: string[] = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

const year: string[] = [
  "2023",
  "2024",
  "2025",
  "2026",
  "2027",
  "2028",
  "2029",
  "2030",
];

const form = reactive({
  name: "",
  address: "",
  phone: "",
  email: "",
  ccNumber: "",
  ccExpiryMonth: "",
  ccExpiryYear: "",
  checkoutStatus: "",
});

const rules = {
  name: {
    required: helpers.withMessage("Please provide a name.", required),
    minLength: helpers.withMessage(
        "Name must have at least 4 letters.",
        minLength(4)
    ),
    maxLength: helpers.withMessage(
        "Name can have at most 45 letters.",
        maxLength(45)
    ),
  },
  phone: {
    required: helpers.withMessage("Please provide a phone number.", required),
    isMobilePhone: helpers.withMessage("Please provide a valid phone number.", isMobilePhone),
  },
  address: {
    required: helpers.withMessage("Please provide a address.", required),
    minLength: helpers.withMessage(
        "address must have at least 10 letters.",
        minLength(10)
    ),
    maxLength: helpers.withMessage(
        "address can have at most 45 letters.",
        maxLength(45)
    ),
  },
  email: {
    required: helpers.withMessage("Please provide a email.", required),
    email: helpers.withMessage("Please provide a valid email.", email),
  },
  ccNumber:{
    required: helpers.withMessage("Please provide credit card number.", required),
    isCreditCard: helpers.withMessage("Please provide a valid credit card number.", isCreditCard),
  },
};
const v$ = useVuelidate(rules, form);

async function submitOrder() {
  console.log("Submit order");
  const isFormCorrect = await v$.value.$validate();
  if (!isFormCorrect) {
    form.checkoutStatus = "ERROR";
  } else {
    try {
      form.checkoutStatus = "PENDING";
      serverErrorMessage.value = defaultServerErrorMessage;
      console.log(form.ccExpiryMonth,form.ccExpiryYear)
      const placeOrderResponse: OrderDetails | ServerErrorResponse =
          await cartStore
              .placeOrder({
                name: form.name,
                address: form.address,
                phone: form.phone,
                email: form.email,
                ccNumber: form.ccNumber,
                ccExpiryMonth: form.ccExpiryMonth,
                ccExpiryYear: form.ccExpiryYear,
              })

      if ("error" in placeOrderResponse) {
        form.checkoutStatus = "SERVER_ERROR";
        serverErrorMessage.value = placeOrderResponse.message
        console.log("Error placing order", placeOrderResponse);
      } else {
        form.checkoutStatus = "OK";
        orderDeatilsStore.setOrderDeatils(placeOrderResponse as OrderDetails);
        await router.push({name: "confirmation-view"});
      }

    } catch (e) {
      form.checkoutStatus = "SERVER_ERROR";
      serverErrorMessage.value = defaultServerErrorMessage;
      console.log("Error placing order", e);
    }
  }
}

/* NOTE: For example yearFrom(0) == <current_year> */
function yearFrom(index: number) {
  return new Date().getFullYear() + index;
}

const defaultServerErrorMessage = "An unexpected error occurred, please try again."
const serverErrorMessage = ref(defaultServerErrorMessage);




</script>

<style scoped>

form {
  display: flex;
  flex-direction: column;
}

form > div {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 0.5em;
}

form > div > input,
form > div > select {
  background-color: #77aaee;
  margin-left: 0.5em;
}

form > .error {
  color: red;
  text-align: right;
}




body,html {
  height:100%;
  margin:0;
  font-family:lato;
}

h2 {
  margin-bottom:0px;
  margin-top:25px;
  text-align:center;
  font-weight:200;
  font-size:19px;
  font-size:1.2rem;

}
.container {
  height: auto;
  -webkit-box-pack: center;
  -webkit-justify-content: center;
  -ms-flex-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  -webkit-align-items: center;
  -ms-flex-align: center;
  align-items: center;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  background: -webkit-linear-gradient(#c5e5e5, #ccddf9);
  background: linear-gradient(#c9e5e9, #ccddf9);
}
ul {
  margin:0;
  padding:0;
}
ul li {
  list-style:none;
  padding-left:10px;
  cursor:pointer;
}
ul li:hover {
  background:rgba(255,255,255,0.1);
}
.thin {
  font-weight:400;
}

.half-input-table td:first-of-type {
  border-right:10px solid #4488dd;
  width:50%;
}
.window {
  height:auto;
  width:800px;
  background:#fff;
  display:-webkit-box;
  display:-webkit-flex;
  display:-ms-flexbox;
  display:flex;
  box-shadow: 0px 15px 50px 10px rgba(0, 0, 0, 0.2);
  border-radius:30px;
  z-index:10;
}
.order-info {
  height:auto;
  width:50%;
  padding-left:25px;
  padding-right:25px;
  box-sizing:border-box;
  display:-webkit-box;
  display:-webkit-flex;
  display:-ms-flexbox;
  display:flex;
  -webkit-box-pack:center;
  -webkit-justify-content:center;
  -ms-flex-pack:center;
  justify-content:center;
  position:relative;
}
.price {
  bottom:0px;
  position:absolute;
  right:0px;
  color:#4488dd;
}
.order-table td:first-of-type {
  width:25%;
}
.order-table {
  position:relative;
}
.line {
  height:1px;
  width:100%;
  margin-top:10px;
  margin-bottom:10px;
  background:#ddd;
}
.order-table td:last-of-type {
  vertical-align:top;
  padding-left:25px;
}
.order-info-content {
  table-layout:fixed;

}

.full-width {
  width:100%;
}
.button {
  border:none;
  background:#22b877;
  line-height:2em;
  border-radius:10px;
  font-size:19px;
  font-size:1.2rem;
  color:#fff;
  cursor:pointer;
  position:relative;
  bottom:25px;
  width:calc(100% - 50px);
  -webkit-transition:all .2s ease;
  transition:all .2s ease;
  margin-top: 50px;
}
.button:hover {
  background:#22a877;
  color:#eee;
  -webkit-transition:all .2s ease;
  transition:all .2s ease;
}

.total {
  margin-top:40px;
  font-size:20px;
  font-size:1.3rem;
  position:relative;
}
.dense {
  line-height:1.2em;
  font-size:16px;
  font-size:1rem;
}
.input-field {
  background:rgba(255,255,255,0.1);
  margin-bottom:10px;
  margin-top:3px;
  line-height:1.5em;
  font-size:20px;
  font-size:1.3rem;
  border:none;
  padding:5px 10px 5px 10px;
  color:#fff;
  box-sizing:border-box;
  width:100%;
  margin-left: 10px;
  margin-right:auto;
}
.credit-info {
  background:#4488dd;
  height:auto;
  width:50%;
  color:#eee;
  -webkit-box-pack:center;
  -webkit-justify-content:center;
  -ms-flex-pack:center;
  justify-content:center;
  font-size:14px;
  font-size:.9rem;
  display:-webkit-box;
  display:-webkit-flex;
  display:-ms-flexbox;
  display:flex;
  box-sizing:border-box;
  padding-left:25px;
  padding-right:25px;
  border-top-right-radius:30px;
  border-bottom-right-radius:30px;
  position:relative;
}

.credit-info-content {
  margin-top:25px;
  -webkit-flex-flow:column;
  -ms-flex-flow:column;
  flex-flow:column;
  display:-webkit-box;
  display:-webkit-flex;
  display:-ms-flexbox;
  display:flex;
  width:100%;
}

.list-of-buttons-columns4{
  float: left;
  justify-content: center;
}
.continue-shopping{
  background:linear-gradient(to bottom, #ffec64 5%, #ffab23 100%);
  background-color:#ffec64;
  border-radius:7px;
  border:2px solid #ffaa22;
  display:inline-block;
  cursor:pointer;
  color:#333333;
  font-family:Arial;
  font-size:16px;
  font-weight:bold;
  padding:6px 10px;
  text-decoration:none;
}
.continue-shopping:hover {
  background:linear-gradient(to bottom, #ffab23 5%, #ffec64 100%);
  background-color:#ffab23;
}
.continue-shopping:active {
  position:relative;
  top:1px;
}
@media (max-width: 600px) {
  .window {
    width: 100%;
    height: 100%;
    display:block;
    border-radius:0px;
  }
  .order-info {
    width:100%;
    height:auto;
    padding-bottom:100px;
    border-radius:0px;
  }
  .credit-info {
    width:100%;
    height:auto;
    padding-bottom:100px;
    border-radius:0px;
  }
  .button {
    border-radius:0px;
  }
}
</style>

<template>
 <div class='container' v-if="cartStore.count == 0">
   <div class="list-of-buttons-columns4">
     <h1> Your Cart is empty</h1>
     <router-link to="/category/Featured">
       <button class="continue-shopping" value="continue-shopping">continue-shopping</button>
     </router-link>
   </div>
 </div>
 <div class='container' v-else>
    <div class='window'>

      <div class='order-info'>
        <div class='order-info-content' >
          <h2>Order Summary</h2>
          <div class='line'></div>
          <template v-for="item in cartStore.cart.items" :key="item.book.bookId">
            <table class='order-table'>
              <tbody>
              <tr>
                <td><img :src="`${bookImagePrefix}/${bookImageFileName(item.book)}`"
                         :alt="item.book.title" class='full-width'/>
                </td>
                <td>
                  <br> <span class='thin'>Title: {{ item.book.title }}</span>
                  <br> Author: {{ item.book.author }}<br>
                </td>

              </tr>
              <tr>
                <td>
                  <div class='price'>${{ (item.book.price / 100).toFixed(2) }}</div>
                </td>
              </tr>
              </tbody>
            </table>
            <div class='line'></div>
          </template>

          <div class='total'>
          <span style='float:left;'>
            <div class='thin dense'>VAT 4%</div>
            <div class='thin dense'>Delivery</div>
            TOTAL
          </span>
            <span style='float:right; text-align:right;'>
            <div class='thin dense'>${{(vat/100).toFixed(2)}}</div>
            <div class='thin dense'>$4.95</div>
            ${{(totalAmount/100 + 4.95).toFixed(2)}}
          </span>
          </div>
        </div>
      </div>
      <div class='credit-info'>
        <div class='credit-info-content'>
          <form @submit.prevent="submitOrder">
            <div>
              <label for="name">Name</label>
              <input
                  class='input-field'
                  type="text"
                  size="20"
                  id="name"
                  name="name"
                  v-model.lazy="v$.name.$model"
              />
            </div>
            <checkout-field-error :field-name="v$.name"></checkout-field-error>

            <div>
              <label for="address">Address</label>
              <input
                  class='input-field'
                  type="text"
                  size="20"
                  id="address"
                  name="address"
                  v-model.lazy="v$.address.$model"
              />
            </div>
            <checkout-field-error :field-name="v$.address"></checkout-field-error>

            <div>
              <label for="phone">Phone</label>
              <br>
              <input
                  class='input-field'
                  type="text"
                  size="20"
                  id="phone"
                  name="phone"
                  v-model.lazy="v$.phone.$model"
              />
            </div>
            <checkout-field-error :field-name="v$.phone"></checkout-field-error>

            <div>
              <label for="email">Email</label>
              <input class='input-field' type="text" size="20" id="email" name="email" v-model.lazy="v$.email.$model"/>
            </div>
            <checkout-field-error :field-name="v$.email"></checkout-field-error>

            <div>
              <label for="ccNumber">Credit card</label>
              <input class='input-field' type="text" size="20" id="ccNumber" name="ccNumber" v-model.lazy="v$.ccNumber.$model" />
            </div>
            <checkout-field-error :field-name="v$.ccNumber"></checkout-field-error>

            <div>
              <label>Exp Date</label>
              <select v-model="form.ccExpiryMonth">
                <option
                    v-for="(month, index) in months"
                    :key="index"
                    :value="index + 1"
                >
                  {{ month }} ({{ index + 1 }})
                </option>
              </select>
              <select v-model="form.ccExpiryYear">
                <option v-for="(yr) in year">
                  {{ yr }}
                </option>
              </select>
            </div>

            <input
                type="submit"
                name="submit"
                class="button"
                :disabled="form.checkoutStatus === 'PENDING'"
                value="Complete Purchase"
            />
          </form>
        </div>
        <div class='credit-info-content' v-show="form.checkoutStatus !== ''" >
          <div v-if="form.checkoutStatus === 'ERROR'">
            Error: Please fix the problems above and try again.
          </div>

          <div v-else-if="form.checkoutStatus === 'PENDING'">Processing...</div>

          <div v-else-if="form.checkoutStatus === 'OK'">Order placed...</div>

          <div v-else>{{serverErrorMessage}}</div>
        </div>
      </div>
    </div>
  </div>
</template>

