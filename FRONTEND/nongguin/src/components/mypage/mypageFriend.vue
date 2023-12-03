<template>
  <div class="container">
    <span id="friend"
      ><i class="fa-solid fa-user-group"></i> 내 친구
      <span>({{ friendStore.friendList.length }})</span>
    </span>
    <div
      v-for="friend in friendStore.friendList"
      :key="friend.userId"
      class="friendList"
    >
      <div>
        <img src="../../assets/penguin.png" alt="" />
        <span class="friendName">{{ friend.userName }}</span>
      </div>
      <a
        id="friendMatchsBtn"
        @click="goFriendMatch(friend.userName, friend.userId)"
        ><i class="fa-solid fa-magnifying-glass"></i> 친구의 매치</a
      >
    </div>
  </div>
</template>

<script setup>
import { watch, ref, onMounted, onBeforeMount } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useUserStore } from "@/stores/userStore";
import { useFriendStore } from "@/stores/friendStore";

const friendStore = useFriendStore();
const userStore = useUserStore();
const reqEmail = ref("");

const router = useRouter();
const route = useRoute();
// 이메일을 아이디로
const reqId = function () {
  friendStore.emailToId(reqEmail);
};
// 친구 요청
const reqFriend = function () {
  const input = {
    friendFromUser: userStore.loginUserId,
    friendToUser: friendStore.reqId,
  };
  friendStore.reqFriend(input);
};

//친구요청 거절
const refuseReq = async function (id) {
  const inputData = {
    friendFromUser: userStore.loginUserId,
    friendToUser: id,
  };
  await friendStore.refuseReqFriend(inputData);
};

//친구요청 수락
const accpetReq = async function (id) {
  const inputData = {
    friendFromUser: userStore.loginUserId,
    friendToUser: id,
  };
  await friendStore.accpetReqFriend(inputData);
};

//친구의 매치보러가기
const goFriendMatch = function (userName, userId) {
  sessionStorage.setItem(
    "friend",
    JSON.stringify({
      friendId: userId,
      friendName: userName,
    })
  );
  friendStore.getFriendMatchList(userId);
  friendStore.userName = userName;
  console.log(friendStore.friendMatchList);
  router.push({ name: "friendMatch" });
};

onBeforeMount(async () => {
  await friendStore.getFriendList(userStore.loginUserId);
  await friendStore.getFriendReqList(userStore.loginUserId);
});
</script>

<style scoped>
.friendName {
  margin-left: 1rem;
  font-weight: 2rem;
}
img {
  width: 2.5rem;
  height: 2.5rem;
}

.friendList {
  display: flex;
  justify-content: space-between;
  padding: 1rem;
  border-bottom: 1px solid rgb(219, 212, 212);
}

#friend {
  display: inline-block;
  font-size: 2em;
  margin-top: 1.5rem;
  margin-bottom: 1.5rem;
  color: rgb(255, 61, 43);
  font-family: "Do Hyeon", sans-serif;
}

#friendMatchsBtn {
  cursor: pointer;
  border: 1px solid rgb(255, 116, 80);
  border-radius: 5px;
  color: rgb(255, 116, 80);
  padding: 0.4rem 0.7rem 0rem 0.7rem;
  font-weight: 600;
}

#friendMatchsBtn:hover {
  background-color: rgb(255, 116, 80);
  color: white;
  transition: all 0.2s ease-in-out;
}
</style>
