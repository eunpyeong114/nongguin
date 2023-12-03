import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

//매치 스토어
export const useFriendStore = defineStore("friend", () => {
  const REST_FRIEND_API = `http://localhost:8080/friend`;
  const REST_USER_API = `http://localhost:8080/user`;

  //친구 목록 조회
  const friendList = ref([]);
  const getFriendList = function (id) {
    axios({
      url: REST_FRIEND_API + "/list/" + id,
      method: "GET",
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
    })
      .then((res) => {
        friendList.value = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };

  //친구 요청 목록 조회
  const friendReqList = ref([]);
  const getFriendReqList = function (id) {
    axios({
      url: REST_FRIEND_API + "/request/list/" + id,
      method: "GET",
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
    })
      .then((res) => {
        friendReqList.value = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };
  //친구의 이메일로 아이디 조회
  const reqId = ref("");
  const emailToId = function (email) {
    axios({
      url: REST_USER_API + "/email/" + email.value,
      method: "GET",
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
    })
      .then((res) => {
        reqId.value = res.data.userId;
      })
      .catch((err) => {});
  };
  // 친구요청 보내기
  const reqFriend = function (inputData) {
    axios({
      url: REST_FRIEND_API + "/request",
      method: "POST",
      headers : {
        "access-token": sessionStorage.getItem("access-token"),
      },
      data: inputData,
    })
      .then((res) => {
        alert("친구 신청완료");
      })
      .catch((err) => {
        alert(err);
        console.log(err);
      });
  };
  //친구 요청 거절
  const refuseReqFriend = function (inputData) {
    console.log(inputData);
    axios({
      url: REST_FRIEND_API + "/request/refuse",
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        "access-token": sessionStorage.getItem("access-token"),
      },
      data: inputData,
    })
      .then((res) => {
        console.log("거절됨");
        getFriendList(inputData.friendFromUser);
        getFriendReqList(inputData.friendFromUser);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  // 친구 요청 수락
  const accpetReqFriend = function (inputData) {
    console.log(inputData);
    axios({
      url: REST_FRIEND_API + "/request/accept",
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        "access-token": sessionStorage.getItem("access-token"),
      },
      data: inputData,
    })
      .then((res) => {
        console.log("수락됨");
        getFriendList(inputData.friendFromUser);
        getFriendReqList(inputData.friendFromUser);
        router.push({ name: "mypageFriend" });
      })
      .catch((err) => {
        console.log(err);
      });
  };
  //친구의 예정된매치 조회
  const friendMatchList = ref([]);
  const getFriendMatchList = function (userId) {
    console.log(userId);
    axios({
      url: REST_FRIEND_API + "/match/" + userId,
      method: "GET",
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
    })
      .then((res) => {
        friendMatchList.value = res.data;
        const nowTimeStamp = +new Date() + 32400000;
        friendMatchList.value = friendMatchList.value.filter(
          (match) => match.matchDate > nowTimeStamp
        );
        friendMatchList.value.forEach((match) => {
          if (match.matchGender == "m") {
            match.matchGender = "남성";
          } else if (match.matchGender == "fm") {
            match.matchGender = "여성";
          } else if (match.matchGender == "both") {
            match.matchGender = "성별무관";
          }
          if (match.matchLevel == "1") {
            match.matchLevel = "아마추어";
          } else if (match.matchLevel == "2") {
            match.matchLevel = "세미프로";
          } else if (match.matchLevel == "3") {
            match.matchLevel = "프로";
          }
          let date = new Date(match.matchDate);

          date.setHours(date.getHours() - 9);

          let year = date.getFullYear();
          let month = (date.getMonth() + 1).toString().padStart(2, "0");
          let day = date.getDate().toString().padStart(2, "0");
          let hours = date.getHours().toString().padStart(2, "0");
          let minutes = date.getMinutes().toString().padStart(2, "0");

          var formattedDate =
            year + "-" + month + "-" + day + " " + hours + ":" + minutes;
          match.matchDate = formattedDate;
        });
        router.push({ name: "friendMatch" });
      })
      .catch((err) => {
        console.log(err);
      });
  };
  
  //리턴
  return {
    friendList,
    getFriendList,
    friendReqList,
    getFriendReqList,
    reqId,
    emailToId,
    reqFriend,
    refuseReqFriend,
    accpetReqFriend,
    friendMatchList,
    getFriendMatchList,
  };
});
