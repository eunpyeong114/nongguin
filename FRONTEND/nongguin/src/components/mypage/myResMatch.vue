<template>
  <div>
    <h2 class="title">
      <i class="fa-regular fa-calendar-days"></i> 참여 예정 매치
    </h2>
    <div
      class="match"
      v-for="match in matchStore.myResMatchList"
      :key="match.id"
    >
      <div class="matchInner">
        <div class="matchList">
          <div id="matchInner">
            <p>{{ match.matchDate }}</p>
            <RouterLink
              :to="{
                name: 'detailMatch',
                params: { matchId: match.matchId },
              }"
            >
              <div>
                <span id="courtName">{{ match.courtName }}</span>
              </div>
              <span id="courtAdd"
                >{{ match.courtAddress }} {{ match.courtTel }}</span
              ></RouterLink
            >
          </div>
          <button
            type="button"
            class="btn btn-outline-danger"
            @click="cancelMatch(match.matchId)"
          >
            취소
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { watch, ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useUserStore } from "@/stores/userStore";
import { useMatchStore } from "@/stores/matchStore";
import axios from "axios";

const matchStore = useMatchStore();
const userStore = useUserStore();
const router = useRouter();
const route = useRoute();
// 매치 취소
const cancelMatch = function (matchId) {
  const userId = userStore.user.userId;
  axios({
    url: "http://localhost:8080/payment/cancel",
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "access-token": sessionStorage.getItem("access-token"),
    },
    data: {
      matchId,
      userId,
    },
  })
    .then((data) => {
      console.log(data);
      alert("결제가 취소되었습니다.");
      matchStore.getResMyMatchList(userStore.loginUserId);
    })
    .catch((err) => console.log(err));
};
onMounted(async () => {
  await matchStore.getResMyMatchList(userStore.loginUserId);
  matchStore.getMyMatchList(userStore.loginUserId);
});
</script>

<style scoped>
.title {
  color: rgb(255, 61, 43);
  font-size: 2rem;
  font-family: "Do Hyeon", sans-serif;

  margin-top: 1.5rem;
  margin-bottom: 2.5rem;
}

.matchInner {
  margin: 1rem 0.5rem;
  padding: 0.5rem 1rem;
  background-color: rgb(245, 240, 240);
  border-radius: 10px;
}

.matchInner:hover {
  transition: all 0.1s ease-in;
}

#courtName {
  text-decoration: none;
  color: black;
}

#courtAdd {
  font-size: 0.8rem;
}

a {
  color: rgb(90, 87, 87);
  text-decoration: none;
}
.matchList {
  display: flex;
  justify-content: space-between;
}

.btn-outline-danger {
  margin-top: 1.7rem;
  padding: 0;
  width: 4rem;
  height: 2.1rem;
}
</style>
