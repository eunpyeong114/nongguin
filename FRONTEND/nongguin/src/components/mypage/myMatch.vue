<template>
  <div>
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
    </div>
  </div>
</template>

<script setup>
import { watch, ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useMatchStore } from "@/stores/matchStore";
import { useUserStore } from "@/stores/userStore";

const matchStore = useMatchStore();
const userStore = useUserStore();
const router = useRouter();

onMounted(async () => {
  await matchStore.getMyMatchList(userStore.loginUserId);
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
  background-color: rgb(255, 153, 127);
  transform: scale(1.02);
  color: white;
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

img {
  width: 8rem;
  height: 6rem;
  border-radius: 10px;
  margin: 0.3rem 2rem 0.3rem 0;
}

.imgContainer {
  display: flex;
}
</style>
