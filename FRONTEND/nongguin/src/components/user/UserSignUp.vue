<template>
  <div id="signup">
    <form @submit.prevent="signup">
      <h1>농구인</h1>
      <h2>회원가입</h2>
      <div class="input-group mb-3">
        <span class="input-group-text" id="basic-addon1">이름</span>
        <input
          type="text"
          class="form-control"
          placeholder="이름을 입력해주세요"
          aria-label="name"
          aria-describedby="basic-addon1"
          v-model="name"
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
          @input="checkEmail"
        />
      </div>
      <div id="passMsg" v-if="emailMsg">{{ emailMsg }}</div>
      <div class="input-group mb-0.5">
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
          @input="checkPass"
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
        />
      </div>
      <div class="input-group mb-3">
        <label class="input-group-text" for="inputGroupSelect01">성별</label>
        <select class="form-select" id="inputGroupSelect01">
          <option selected>성별을 선택해주세요</option>
          <option value="m">남성</option>
          <option value="fm">여성</option>
        </select>
      </div>
      <button id="signupBtn" type="submit">회원가입</button>
    </form>
  </div>
</template>

<script setup>
// import
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/userStore";

const userStore = useUserStore();
//선언
const name = ref("");
const phone = ref("");
const certificationNumber = ref("");
const email = ref("");
const pass = ref("");
const passCheck = ref("");
const gender = ref("");
const address = ref("");

const router = useRouter();

//회원가입 함수
const signup = () => {
  const user = {
    userName: name.value,
    userEmail: email.value,
    userPass: pass.value,
    userGender: gender.value,
    userAddress: address.value,
    userPhone: phone.value,
  };
  if (!email.value.includes("@")) {
    alert("입력한 이메일 형식을 확인해주세요");
    return;
  }
  let flag = true;
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
    alert("조금 더 강력한 비밀번호를 입력해주세요");
    return;
  }
  if (pass.value != passCheck.value) {
    alert("입력한 비밀번호가 서로 일치하지 않습니다.");
    return;
  }

  userStore.signup(user);
};
const emailMsg = ref("");
const passMsg = ref("");

const checkEmail = function () {
  if (!email.value.includes("@")) {
    emailMsg.value = "입력한 이메일 형식을 확인해주세요";
  } else {
    emailMsg.value = "";
  }
};
const checkPass = function () {
  if (pass.value != passCheck.value) {
    passMsg.value = "비밀번호가 일치하지 않습니다.";
  } else {
    passMsg.value = "";
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
  margin-top: 1.2rem;
  padding: 0 0 0 2rem;
  color: orangered;
  font-size: 2.7rem;
  font-family: "Black Han Sans", sans-serif;
}

h2 {
  font-size: 3rem;
  padding: 0 0 0 2rem;
  font-family: "Black Han Sans", sans-serif;

  margin-bottom: 2rem;
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
  width: 22.85%;
  height: 2.7rem;
  background-color: rgb(252, 246, 246);
  border-radius: 10px;
  border: 2px solid rgb(255, 116, 80);
  font-size: 0.9rem;

  margin: 1rem auto;
  margin-top: 3rem;
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

input {
  caret-color: orangered;
  caret-shape: block;
}
</style>
