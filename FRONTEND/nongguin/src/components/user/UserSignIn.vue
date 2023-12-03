<template>
  <div id="topLine" />
  <div id="signin">
    <div>
      <h2>농구하고 싶을땐</h2>
      <h1>농구인</h1>
      <form @submit.prevent="signIn">
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon1">이메일</span>
          <input
            type="text"
            class="form-control"
            placeholder="이메일을 입력해주세요"
            aria-label="email"
            aria-describedby="basic-addon1"
            v-model="email"
          />
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon1">비밀번호</span>
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호를 입력해주세요"
            aria-label="password"
            aria-describedby="basic-addon1"
            v-model="password"
          />
        </div>
        <button type="submit">로그인</button>
      </form>
      <br />
      <a id="signupBtn" @click="goSignUp">회원가입</a>
    </div>
  </div>
</template>

<script setup>
// import
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/userStore";

const userStore = useUserStore();
const email = ref("");
const password = ref("");
const router = useRouter();

//로그인
const signIn = () => {
  let user = {
    userEmail: email.value,
    userPass: password.value,
  };
  userStore.signin(user);
};
// 회원가입 이동
const goSignUp = () => {
  router.push("/user/signup");
};
</script>

<style scoped>
#topLine {
  margin: 0;
  padding: 0;
  height: 0.4rem;
  background-color: rgb(255, 116, 80);
}
#signin {
  display: flex;
  justify-content: center;
  flex-direction: column;

  width: 27rem;
  margin: 6rem auto;
  padding: 3rem;

  border: 4px double rgb(255, 116, 80);
  border-radius: 10px;
  background-color: white;
}

button {
  padding: 5px;
  margin-top: 1rem;
  width: 100%;
  height: 2.4rem;
  background-color: rgb(252, 246, 246);
  border-radius: 10px;
  border: 1px solid rgb(255, 116, 80);
  font-size: 0.9rem;
}

button:hover {
  color: white;
  background-color: rgb(255, 116, 80);
  transition: all 0.25s ease-in-out;
}
h1 {
  color: rgb(255, 61, 43);
  font-family: "Do Hyeon", sans-serif;
  font-size: 2.5rem;
  margin-bottom: 2rem;
}

h2 {
  font-size: 2.3rem;
  font-family: "Do Hyeon", sans-serif;
}

#signupBtn {
  cursor: pointer;
  color: rgb(255, 61, 43);
  text-decoration: underline;
  font-weight: 550;
  font-size: 0.9rem;
}

.input-group-text {
  width: 5.5rem;
}

input {
  caret-color: orangered;
  caret-shape: block;
}
</style>
