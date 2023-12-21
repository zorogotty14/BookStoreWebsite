<script setup lang="ts"></script>

<style scoped>
.page {
  display: flex;
  margin-left: 40%;
}

h1 {
  padding: 1em;
}

p {
  padding: 1em;
}

.offer-btn {
  box-shadow:inset 0px 1px 0px 0px #fff6af;
  background:linear-gradient(to bottom, #ffec64 5%, #ffab23 100%);
  background-color:#ffec64;
  border-radius:6px;
  border:1px solid #ffaa22;
  display:inline-block;
  cursor:pointer;
  color:#333333;
  font-family:Arial;
  font-size:15px;
  font-weight:bold;
  padding:6px 24px;
  padding-top: 10px;
  text-decoration:none;
  text-shadow:0px 1px 0px #ffee66;
  margin-left: 20%;
}
.offer-btn:hover {
  background:linear-gradient(to bottom, #ffab23 5%, #ffec64 100%);
  background-color:#ffab23;
}
.offer-btn:active {
  position:relative;
  top:1px;
}
</style>
<template>
  <div class="page">
    <section class="flow-content container dark-background">
      <h1>Your Content was Not Found</h1>
        <router-link to="/">
          <button class="offer-btn">Go Back</button>
        </router-link>
    </section>
  </div>
</template>