<template>
  <div>
    <RouterView id="view"></RouterView>
    <h5 class="title"><i class="fa-solid fa-clipboard"></i> 나의 매치 기록</h5>
    <div class="match" v-for="match in matchStore.myMatchList" :key="match.id">
      <div class="matchInner">
        <RouterLink
          :to="{
            name: 'detailMatch',
            params: { matchId: match.matchId },
          }"
        >
          <div class="imgContainer">
            <img
              :src="`/src/assets/courtPic/${match.courtId}.jpg`"
              alt=""
              srcset=""
            />
            <div>
              <p>{{ match.matchDate }}</p>
              <div>
                <span id="courtName">{{ match.courtName }}</span>
              </div>
              <span id="courtAdd"
                >{{ match.courtAddress }} {{ match.courtTel }}</span
              >
            </div>
          </div>
        </RouterLink>
      </div>
      <div id="reviewRegist">
        <button
          type="button"
          class="btn btn-primary"
          data-bs-toggle="modal"
          data-bs-target="#exampleModal"
          data-bs-whatever="@mdo"
        >
          리뷰 작성
        </button>

        <div
          class="modal fade"
          id="exampleModal"
          tabindex="-1"
          aria-labelledby="exampleModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">
                  리뷰 작성
                </h1>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div class="modal-body">
                <form>
                  <div class="mb-3">
                    <textarea
                      class="form-control"
                      id="message-text"
                      v-model="review"
                    ></textarea>
                  </div>
                </form>
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  취소
                </button>
                <button
                  type="button"
                  class="btn btn-primary"
                  @click="reviewRegist(match.matchId)"
                >
                  확인
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { watch, ref, onMounted } from "vue";
import { useRouter, useRoute, RouterLink, RouterView } from "vue-router";
import { useMatchStore } from "@/stores/matchStore";
import { useUserStore } from "@/stores/userStore";
import { useReviewStore } from "@/stores/reviewStore";
import axios from "axios";
const matchStore = useMatchStore();
const userStore = useUserStore();
const reviewStore = useReviewStore();
const router = useRouter();

onMounted(async () => {
  await matchStore.getMyMatchList(userStore.loginUserId);
});

const review = ref("");
// 리뷰 등록
const reviewRegist = function (matchId) {
  // paymentId 받아오기
  console.log(matchId);
  console.log(userStore.loginUserId);
  axios({
    url:
      "http://localhost:8080/payment/getid/" +
      matchId +
      "/" +
      userStore.loginUserId,
    method: "GET",
    headers: {
      "access-token": sessionStorage.getItem("access-token"),
    },
    data: { userId: userStore.loginUserId, matchId },
  }).then((res) => {
    console.log(userStore.loginUserId);
    console.log(matchId);
    console.log(res.data);
  });
};
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
  margin: 0.5rem;
  padding: 0.5rem 1rem;
  background-color: rgb(245, 240, 240);
  border-radius: 10px;
}

.matchInner:hover {
  background-color: rgb(255, 153, 127);
  transform: scale(1.02);
  color: white;
  transition: all 0.1s ease-in;
  cursor: pointer;
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

img {
  width: 8rem;
  height: 6rem;
  border-radius: 10px;
  margin: 0.3rem 2rem 0.3rem 0;
}

.imgContainer {
  display: flex;
}

#reviewRegist {
  font-size: 0.9rem;
  text-align: right;
  margin-right: 1rem;
  padding: 0 0 1rem 0;
}

#review:hover {
  color: rgb(255, 61, 43);
  font-weight: 600;
  animation-name: review;
  animation-duration: 0.4s;
  animation-fill-mode: forwards;
}

@keyframes review {
  100% {
    margin-right: -0.9rem;
  }
}
#review {
  margin: 0;
  padding: 0;
}

.btn-primary {
  color: white;
  background-color: rgb(255, 132, 101);
}

.btn {
  border: none;
}
</style>
