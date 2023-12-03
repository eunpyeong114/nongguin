<template>
  <div>
    <div>
      <h5 class="title">
        <i class="fa-solid fa-user-group"></i> 친구 신청하기
      </h5>
      <div class="input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="신청할 친구의 이메일"
          aria-label="reqEmail"
          aria-describedby="basic-addon2"
          v-model="reqEmail"
          @input="reqId"
          @keyup.enter="reqFriend"
        />
        <span @click="reqFriend" class="input-group-text" id="basic-addon2"
          >요청하기</span
        >
      </div>
    </div>
    <hr />
    <h5 class="title">
      <i class="fa-solid fa-signs-post"></i> 받은 친구 요청
      {{ friendStore.friendReqList.length }}
    </h5>

    <div
      v-for="friend in friendStore.friendReqList"
      :key="friend.userId"
      class="reqList"
    >
      <div>
        <img src="../../assets/penguin.png" />
        <span class="friendName">{{ friend.userName }}</span>
      </div>
      <div class="btnDiv">
        <button
          type="button"
          class="btn btn-outline-primary"
          @click="accpetReq(friend.userId)"
        >
          수락
        </button>
        <button
          type="button"
          class="btn btn-outline-danger"
          @click="refuseReq(friend.userId)"
        >
          거절
        </button>
      </div>
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
const goFriendMatch = function (userId) {
  friendStore.getFriendMatchList(userId);
  router.push({ name: "friendMatch" });
};

onBeforeMount(async () => {
  await friendStore.getFriendList(userStore.loginUserId);
  await friendStore.getFriendReqList(userStore.loginUserId);
});
</script>

<style scoped>
.name {
  font-size: 1rem;
}
.friendName {
  margin-left: 1rem;
  font-weight: 2rem;
}
.title {
  color: rgb(255, 61, 43);
  font-size: 2rem;
  font-family: "Do Hyeon", sans-serif;

  margin-top: 1.5rem;
  margin-bottom: 2.5rem;
}

img {
  width: 2.5rem;
  height: 2.5rem;
}

#basic-addon2 {
  cursor: pointer;
}
.reqList {
  display: flex;
  justify-content: space-between;
  padding: 1rem;
  border-bottom: 1px solid rgb(219, 212, 212);
}

.btnDiv {
  width: 9rem;

  display: flex;
  justify-content: space-evenly;
}
</style>
