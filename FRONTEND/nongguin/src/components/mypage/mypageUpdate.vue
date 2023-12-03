<template>
  <div>
    <div>
      <form @submit.prevent="update">
        <h1>내 정보 수정</h1>
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
            readonly
          />
        </div>
        <div id="passMsg" v-if="emailMsg">{{ emailMsg }}</div>
        <div class="input-group">
          <span class="input-group-text" id="basic-addon1">비밀번호</span>
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호를 입력해주세요"
            aria-label="pass"
            aria-describedby="basic-addon1"
            v-model="pass"
          />
        </div>
        <p></p>
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon1">비밀번호</span>
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호를 한번 더 입력해주세요"
            aria-label="passCheck"
            aria-describedby="basic-addon1"
            v-model="passCheck"
            @input="checkNewPass"
          />
        </div>
        <div id="passMsg" v-if="passMsg">{{ passMsg }}</div>
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon1">기존 비밀번호</span>
          <input
            type="password"
            class="form-control"
            placeholder="기존 비밀번호를 입력해주세요"
            aria-label="pastpass"
            aria-describedby="basic-addon1"
            v-model="pastpass"
            @input="checkPass"
          />
        </div>
        <div id="passMsg" v-if="checkMsg">{{ checkMsg }}</div>

        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon1">주소</span>
          <input
            type="text"
            class="form-control"
            placeholder="주소를 입력해주세요"
            aria-label="address"
            aria-describedby="basic-addon1"
            v-model="address"
          />
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text" id="basic-addon1">성별</span>
          <input
            type="text"
            class="form-control"
            placeholder="주소를 입력해주세요"
            aria-label="gender"
            aria-describedby="basic-addon1"
            v-model="gender"
            readonly
          />
        </div>
        <button id="signupBtn" type="submit">수정</button>
      </form>
      <p id="del" @click="deleteUser">회원탈퇴</p>
    </div>
  </div>
</template>

<script setup>
// import
import { ref, onBeforeMount } from "vue";
import { useRoute } from "vue-router";
import { useUserStore } from "@/stores/userStore";
import axios from "axios";

const userStore = useUserStore();
const route = useRoute();
//선언
const name = ref("");
const phone = ref("");
const email = ref("");
const pass = ref("");
const address = ref("");
const passCheck = ref("");
const pastpass = ref("");
const gender = ref("");
let flag = true;

const passMsg = ref("");
const checkNewPass = function () {
  if (pass.value != passCheck.value) {
    passMsg.value = "비밀번호가 일치하지 않습니다.";
  } else {
    passMsg.value = "";
  }
};
const checkMsg = ref("");
const checkPass = function () {
  if (pastpass.value != userStore.user.userPass) {
    checkMsg.value = "기존 비밀번호와 일치하지 않습니다.";
  } else {
    checkMsg.value = "";
  }
};
//회원 정보 수정
const update = () => {
  const user = {
    userId: userStore.loginUserId,
    userPass: pass.value,
    userAddress: address.value,
    userPhone: phone.value,
  };
  if (pastpass.value != userStore.user.userPass) {
    alert("기존 비밀번호가 다릅니다.");
    return;
  }
  if (pass.value != userStore.user.userPass) {
    const words = [
      "!",
      "@",
      "#",
      "$",
      "%",
      "^",
      "&",
      "*",
      "(",
      ")",
      ",",
      ".",
      "?",
      '"',
      ":",
      "{",
      "}",
      "|",
      "<",
      ">",
    ];
    words.forEach((word) => {
      if (pass.value.includes(word)) {
        flag = false;
      }
    });
    if (pass.value.length < 8 || flag) {
      alert("비밀번호가 약합니다. ");
      return;
    }
    if (pass.value != passCheck.value) {
      alert("비밀번호와 비밀번호 확인이 같지않음");
      return;
    }
  }
  userStore.updateUser(user);
};

onBeforeMount(async () => {
  await userStore.getUser(userStore.loginUserId);
  name.value = userStore.user.userName;
  phone.value = userStore.user.userPhone;
  email.value = userStore.user.userEmail;
  address.value = userStore.user.userAddress;
  gender.value = userStore.user.userGender == "m" ? "남성" : "여성";
});

//회원탈퇴
const deleteUser = function () {
  if (window.confirm("회원탈퇴하시겠습니까?")) {
    userStore.deleteUser();
    sessionStorage.removeItem("access-token");
    userStore.delLoginUserId();
    alert("성공적으로 탈퇴되었습니다!");
    userStore.dropUser();
    router.push("/");
  }
};
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
  width: 8rem;
}

span,
label {
  display: inline-block;
  text-align: center;
}
#del {
  cursor: pointer;
  margin-top: 1rem;
  text-align: right;
  font-size: 0.8rem;
  text-decoration: underline;
  width: 98%;
  color: rgb(128, 124, 123);
}
</style>
