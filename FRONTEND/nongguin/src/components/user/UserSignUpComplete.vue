<template>
  <div id="completeSignup">
    <div id="welcome"></div>
    <h1><span>농구인</span> 회원이 되신것을 환영합니다.</h1>
    <br />
    <p @click="goLogin">로그인 하러가기</p>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";

const router = useRouter();
const goLogin = function () {
  router.push({ name: "userSignin" });
};
</script>

<style scoped>
#welcome {
  background-image: url("../../assets/welcomeSignUp.png");
  width: 21rem;
  height: 21rem;
  background-size: cover;
  border-radius: 50%;
  animation-name: fadein;
  animation-duration: 2s;
}

@keyframes fadein {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

#completeSignup {
  display: flex;
  flex-direction: column;
  align-items: center;
}
span {
  color: orangered;
}
h1 {
  margin-top: 2rem;
  font-weight: 600;
  font-family: "Do Hyeon", sans-serif;
}

p {
  cursor: pointer;
  color: orangered;
  margin-bottom: 10rem;
  text-decoration: underline;
  font-family: "Do Hyeon", sans-serif;
  font-size: 1.5rem;
}
p:hover {
  transform: rotate3d();
}
</style>
