<template>
  <div id="top">
    <div id="main">
      <HeaderNav></HeaderNav>
      <router-view></router-view>
      <Footer></Footer>
    </div>
  </div>
</template>

<script setup>
import HeaderNav from "./components/common/HeaderNav.vue";
import Footer from "./components/common/Footer.vue";
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  background-color: rgb(245, 244, 242);
}

#main {
  width: 100%;
}
#top {
  display: flex;
  justify-content: center;
  
  height: 100%;
}
</style>
