<template>
  <div id="topLine" />
  <div id="myPage">
    <div id="profileContainer">
      <div id="profile" @click="goToMyInfo">
        <div id="profileImage"></div>
        <p id="profileName">{{ userStore.user.userName }}</p>
      </div>
      <div id="buttons">
        <div class="mypageNav" @click="goUpdate">
          <div id="aTag">
            <i class="fa-solid fa-person"></i>
            <p id="myInfoMo">&nbsp&nbsp&nbsp&nbsp내 정보 수정</p>
          </div>
        </div>
        <hr />
        <div class="mypageNav" @click="goFriendReq">
          <div id="aTag">
            <p><i class="fa-solid fa-user-plus"></i>&nbsp&nbsp&nbsp친구 요청</p>
            <div id="reqCnt">{{ friendStore.friendReqList.length }}</div>
          </div>
        </div>
        <hr />
        <div class="mypageNav" @click="goFriend">
          <div id="aTag">
            <p>
              <i class="fa-solid fa-user-group"></i>&nbsp&nbsp&nbsp친구 목록
            </p>
          </div>
        </div>
        <hr />
        <div class="mypageNav" @click="goMyMatch">
          <div id="aTag">
            <i class="fa-solid fa-clipboard"></i>
            <p id="myRec">&nbsp&nbsp&nbsp&nbsp나의 매치 기록</p>
          </div>
        </div>
        <hr />
        <div class="mypageNav" @click="goResMatch">
          <div id="aTag">
            <i class="fa-regular fa-calendar-days"></i>
            <p id="mySch">&nbsp&nbsp&nbsp&nbsp나의 예정 매치</p>
          </div>
        </div>
        <hr />
      </div>
    </div>
    <router-view id="view"></router-view>
  </div>
</template>

<script setup>
//import
import { onBeforeMount } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useUserStore } from "@/stores/userStore";
import { useFriendStore } from "@/stores/friendStore";
const userStore = useUserStore();
const friendStore = useFriendStore();
const router = useRouter();
const route = useRoute();

const goToMyInfo = function () {
  router.push({ name: "mypageInfo" });
};
// 친구목록 이동
const goUpdate = function () {
  router.push({ name: "mypageUpdate" });
};
// 친구요청 이동
const goFriendReq = function () {
  router.push({ name: "mypageFriendReq" });
};
// 유저 정보 조회 수정 이동
const goFriend = function () {
  router.push({ name: "mypageFriend" });
};
// 내 매치목록 이동
const goMyMatch = function () {
  router.push({ name: "mypageMatch" });
};
// 내 예정목록 이동
const goResMatch = function () {
  router.push({ name: "mypageResMatch" });
};

onBeforeMount(async () => {
  await userStore.getUser(userStore.loginUserId);
  await friendStore.getFriendReqList(userStore.loginUserId)
});
</script>

<style scoped>
#aTag {
  display: flex;
}
#myRec {
  padding: 0.6rem 0 0 0;
}
#mySch {
  padding: 0.6rem 0 0 0;
}
#myInfoMo {
  padding: 0.6rem 0 0 0;
}
#topLine {
  margin: 0;
  padding: 0;
  height: 0.4rem;
  background-color: rgb(255, 116, 80);
}
#myPage {
  display: flex;
  margin: 0;
  padding: 0%;
}
#reqCnt {
  width: 1.4rem;
  height: 1.4rem;

  background-color: rgb(255, 61, 43);
  color: white;
  border-radius: 50%;
  margin-top: 0.2rem;
  margin-left: 0.2rem;
  text-align: center;
  font-size: 0.85rem;
  font-weight: 600;
}
#profile {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  cursor: pointer;
  font-family: "Hi Melody", sans-serif;
  font-family: "Nanum Gothic", sans-serif;
}

#profileImage {
  margin: 0 auto;
  padding: 0 auto;
  width: 100px;
  height: 100px;
  background-image: url("../assets/penguin.png");
  background-size: contain;
  background-repeat: no-repeat;
  background-color: #fff;
  border-radius: 50%;
}

.mypageNav:hover {
  background-color: rgb(255, 132, 101);
  border-radius: 10px;
  font-weight: 500;
  transition: all 0.3s ease-in-out;
  color: white;
  cursor: pointer;
}
#profileName {
  text-align: center;
  font-weight: 600;
}
#buttons {
  margin: 0 auto;
  display: flex;
  flex-direction: column;
}
#buttons button {
  margin: 1rem 0;
  color: black;
  background-color: #fff;
}
hr {
  margin: 0.2rem;

  color: rgb(151, 154, 156);
}
#topLine {
  color: rgb(109, 110, 110);
}
#profileContainer {
  background-color: #fff;
  margin: 0;
  padding: 1rem;
  width: 15rem;
  height: 34rem;
  border-radius: 10px;
}

#myPage {
  width: 60%;
  margin: 0 auto;
  padding: 3rem;
  font-family: "Hi Melody", sans-serif;
  font-family: "Nanum Gothic", sans-serif;
}
#view {
  width: 40rem;

  padding: 1rem;
  margin: 0 1rem;
  background-color: #fff;
  border-radius: 10px;
}

.mypageNav {
  height: 3.6rem;
  padding: 0.5rem 1rem 1.5rem 1rem;
  display: flex;
}

i {
  padding: 0.8rem 0 0 0;
}

.fa-user-plus {
  font-size: 1.1rem;
}
.fa-person {
  font-size: 1.6rem;
  padding: 0.6rem 0 0.8rem 0;
}

.fa-clipboard {
  font-size: 1.36rem;
}
.fa-calendar-days {
  font-size: 1.24rem;
}
</style>
