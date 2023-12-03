<template>
  <div>
    <h5 class="title">
      <i class="fa-solid fa-clipboard"></i> {{ friendStore.userName }}님의 예정
      매치
    </h5>
    <div
      class="match"
      v-for="match in friendStore.friendMatchList"
      :key="match.id"
    >
      <div class="matchInner">
        <div class="imgContainer">
          <img :src="`/src/assets/courtPic/${match.courtId}.jpg`" />
          <div>
            <p>{{ match.matchDate }}</p>
            <div>
              <span id="courtName">{{ match.courtName }}</span>
            </div>
            <span id="courtAdd"
              >{{ match.courtAddress }} {{ match.courtTel }}</span
            >
            <router-link
              :to="{ name: 'detailMatch', params: { matchId: match.matchId } }"
            >
              <p id="join">
                <i class="fa-brands fa-gratipay"></i> &nbsp;같이 참여하기
              </p></router-link
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// import
import { watch, ref,onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import { useFriendStore } from "@/stores/friendStore";

const friendStore = useFriendStore();
const router = useRouter();
// 친구가 신청한 매치 상세보기 이동
const detailMatch = (id) => {
  router.push({ name: "detailMatch", params: { matchId: id } });
};
onBeforeMount( () => {
  const friend = JSON.parse(sessionStorage.getItem("friend"))
  console.log(friend)
  friendStore.getFriendMatchList(friend.friendId);
  friendStore.userName = friend.friendName;
});
</script>

<style scoped>
.title {
  color: rgb(255, 61, 43);
  font-size: 2rem;
  font-family: "Black Han Sans", sans-serif;

  margin-top: 1.5rem;
  margin-bottom: 2.5rem;
}
img {
  width: 8rem;
  height: 6rem;
  border-radius: 10px;
  margin: 0.3rem 2rem 0.3rem 0;
}

.matchInner {
  margin: 1rem 0.5rem;
  padding: 0.5rem 1rem;
  background-color: rgb(245, 240, 240);
  border-radius: 10px;
}
#join:hover {
  transform: scale(1.03);
  transition: all 0.1s ease-in;
}
#courtName {
  text-decoration: none;
  color: black;
}
#courtAdd {
  font-size: 0.8rem;
}

#join {
  margin-top: 1rem;
  text-align: right;
  width: 22rem;
  font-size: 0.8rem;
  color: rgb(250, 147, 121);
  font-weight: 600;
}

a {
  color: rgb(90, 87, 87);
  text-decoration: none;
}
.imgContainer {
  display: flex;
}
</style>
