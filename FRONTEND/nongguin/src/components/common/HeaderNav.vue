<template>
  <div>
    <div class="navContainer">
      <img id="logoTag" src="@/assets/nongguin-logo.jpg" @click="goToMain" />
      <div class="navAtag">
        <p v-if="userName">{{ userName }}님 반갑습니다!</p>
        <div id="list">
          <router-link to="/">매치 목록</router-link>
        </div>
        <div>
          <router-link :to="`/mypage`" v-if="userStore.loginUserId != 0" id="my"
            >마이 페이지</router-link
          >
        </div>
        <div>
          <router-link to="/user" v-if="userStore.loginUserId == 0" id="login"
            >로그인</router-link
          >
          <a id="loout" @click="logout" v-else>로그아웃</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
//import
import { useUserStore } from "@/stores/userStore";
import { useRouter } from "vue-router";
import { onMounted, ref, watch } from "vue";
import { useMatchStore } from "@/stores/matchStore";
const router = useRouter();
const userStore = useUserStore();
const userName = ref("");
const matchStore = useMatchStore();

//로그아웃
const logout = function () {
  sessionStorage.removeItem("access-token");
  userStore.delLoginUserId();
  userName.value = "";
  matchStore.myMatchList=ref("")
  userStore.dropUser()
  alert("로그아웃 되었습니다!");
  router.push("/");
};

const goToMain = function () {
  router.push({ name: "matchList" });
};

watch(
  () => userStore.user,
  () => {
    userName.value = userStore.user.userName;
  }
);
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

.navContainer {
  padding: 0 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;

  background-color: rgb(245, 244, 242);
}

.navAtag {
  /* width: 41rem; */
  font-size: 1.8rem;
  font-family: "Do Hyeon", sans-serif;
  color: rgb(255, 116, 80);
  display: flex;
  justify-content: space-evenly;
}

a {
  text-align: center;
  cursor: pointer;
  margin: 1rem;
  text-decoration: none;
  color: rgb(255, 116, 80);
}
a:hover {
  color: rgb(255, 61, 43);
  border-bottom: 4px solid rgb(255, 61, 43);
  transform: scale(1.4rem);
  transition: all 0.12s ease-in-out;
}
#loout:hover {
  color: rgb(255, 61, 43);
}
p {
  /* width: 15rem; */
  margin: auto 1rem;
}

.router-link-active {
  color: rgb(255, 61, 43);
}

#logoTag {
  cursor: pointer;
}
</style>
