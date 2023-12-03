<template>
  <div>
    <div>
      <form @submit.prevent="">
        <h1>내 정보</h1>
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon1">이름</span>
          <input
            type="text"
            class="form-control"
            placeholder="이름을 입력해주세요"
            aria-label="name"
            aria-describedby="basic-addon1"
            v-model="name"
            readonly
          />
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon1">전화번호</span>
          <input
            type="text"
            class="form-control"
            placeholder="000-0000-0000 형식으로 입력해주세요"
            aria-label="phone"
            aria-describedby="basic-addon1"
            v-model="phone"
            readonly
          />
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon1">이메일</span>
          <input
            type="text"
            class="form-control"
            placeholder="이메일을 입력해주세요"
            aria-label="email"
            aria-describedby="basic-addon1"
            v-model="email"
            @input="checkEmail"
            readonly
          />
        </div>

        <div id="passMsg" v-if="passMsg">{{ passMsg }}</div>
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon1">주소</span>
          <input
            type="text"
            class="form-control"
            placeholder="주소를 입력해주세요"
            aria-label="address"
            aria-describedby="basic-addon1"
            v-model="address"
            readonly
          />
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon1">성별</span>
          <input
            type="text"
            class="form-control"
            aria-label="gender"
            aria-describedby="basic-addon1"
            v-model="gender"
            readonly
          />
        </div>
      </form>
      <button id="signupBtn" type="goUpdate" @click="goUpdate">
        내정보 수정
      </button>
    </div>
  </div>
</template>

<script setup>
// import
import { ref, onBeforeMount } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useUserStore } from "@/stores/userStore";
import { useFriendStore } from "@/stores/friendStore";

const friendStore = useFriendStore();
const userStore = useUserStore();
const route = useRoute();
const router = useRouter();
//선언
const name = ref("");
const phone = ref("");
const email = ref("");
const pass = ref("");
const address = ref("");
const gender = ref("");
const goUpdate = function () {
  router.push({ name: "mypageUpdate" });
};

onBeforeMount(async () => {
  await userStore.getUser(userStore.loginUserId);
  await friendStore.getFriendReqList(userStore.loginUserId)
  name.value = userStore.user.userName;
  phone.value = userStore.user.userPhone;
  email.value = userStore.user.userEmail;
  pass.value = userStore.user.userPass;
  address.value = userStore.user.userAddress;
  gender.value = userStore.user.userGender == "m" ? "남성" : "여성";
});
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
}
#signup {
  display: flex;
  justify-content: center;
  flex-direction: column;
  width: 40%;
  margin: 2rem auto;
  padding: 2rem;
  background-color: white;
  border: 4px double rgb(255, 116, 80);
  border-radius: 10px;
}
button {
  border-radius: 10px;
  padding: 5px;
  border: 1px solid grey;
  width: 100%;
}

h1 {
  color: orangered;
  font-size: 2.7rem;
  font-family: "Do Hyeon", sans-serif;
}

form {
  margin-top: 2rem;
}

#gender {
  margin: 1rem 0;
}

#signupBtn {
  padding: 5px;
  margin-top: 1rem;
  width: 100%;
  height: 2.7rem;
  background-color: rgb(252, 246, 246);
  border-radius: 10px;
  border: 2px solid rgb(255, 116, 80);
  font-size: 0.9rem;
}

#signupBtn:hover {
  background-color: rgb(255, 116, 80);
  color: white;
  transition: all 0.3s ease-in-out;
}

#passMsg {
  font-size: 0.8rem;
  color: red;
}

.input-group-text {
  width: 5.5rem;
}

span,
label {
  display: inline-block;
  text-align: center;
}
</style>
