import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";
//유저 스토어
export const useUserStore = defineStore("user", () => {
  const REST_USER_API = `http://localhost:8080/user`;

  //로그인 된 유저 정보
  const loginUserEmail = ref("");
  const loginUserId = ref(0);

  //로그인 된 유저 삭제
  const delLoginUserId = function () {
    loginUserId.value = 0;
  };

  // 유저 정보 가져오기
  const user = ref();
  const getUser = function (id) {
    axios({
      url: REST_USER_API + "/id/" + id,
      method: "GET",
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
    })
      .then((res) => {
        user.value = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const dropUser = function () {
    user.value = ref();
  };
  //로그인
  const signin = function (user) {
    axios({
      url: REST_USER_API + "/signin",
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: user,
    })
      .then((res) => {
        sessionStorage.setItem("access-token", res.data["access-token"]);
        const token = res.data["access-token"].split(".");
        let emailAndId = JSON.parse(atob(token[1]));
        loginUserEmail.value = emailAndId["userEmail"];
        loginUserId.value = emailAndId["userId"];
        console.log(emailAndId);
        router.push("/");
        getUser(loginUserId.value);
      })
      .catch((err) => {
        console.log(err);
        alert("이메일 또는 비밀번호를 잘못 입력하셨습니다.");
      });
  };

  //회원가입
  const signup = function (user) {
    axios({
      url: REST_USER_API + "/signup",
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: user,
    })
      .then(() => {
        router.push("/user/completesignup");
      })
      .catch((err) => {
        console.log(err);
        alert(err.response.data);
      });
  };

  //유저 정보 수정
  const updateUser = function (user) {
    axios({
      url: REST_USER_API + "/" + user.userId,
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        "access-token": sessionStorage.getItem("access-token"),
      },
      data: user,
    })
      .then(() => {
        router.push({ name: "mypageInfo" });
        alert("수정 완료되었습니다.");
      })
      .catch((err) => {
        console.log(err);
      });
  };
  // 유저 삭제
  const deleteUser = function () {
    axios({
      url: REST_USER_API + "/signout/" + loginUserId.value,
      method: "PUT",
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
      data: user,
    })
      .then((res) => {
        console.log(res);
        router.push("/");
      })
      .catch((err) => {
        console.log(err);
      });
  };
  //

  //리턴
  return {
    loginUserId,
    loginUserEmail,
    delLoginUserId,
    signin,
    signup,
    user,
    getUser,
    updateUser,
    deleteUser,
    dropUser,
  };
});
