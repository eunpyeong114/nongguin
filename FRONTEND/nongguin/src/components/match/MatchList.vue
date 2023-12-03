<template>
  <div class="mainImageContainer">
    <div id="slogan">
      <p id="sloganStr">농구하고 싶을 땐</p>
      <p id="sloganEnd">농구인</p>
    </div>
  </div>
  <div class="container">
    <div class="listContainer">
      <VCalendar
        expanded
        :color="selectedColor"
        :attributes="attrs"
        view="weekly"
        v-model="date"
        @dayclick="show"
      ></VCalendar>
      <div class="selectContainer">
        <select
          class="form-select"
          aria-label="Default select example"
          v-model="region"
        >
          <option value="">지역선택</option>
          <option value="서울">서울</option>
          <option value="경기">경기</option>
          <option value="인천">인천</option>
          <option value="강원">강원</option>
          <option value="대전">대전</option>
          <option value="세종">세종</option>
          <option value="충북">충북</option>
          <option value="충남">충남</option>
          <option value="대구">대구</option>
          <option value="경북">경북</option>
          <option value="부산">부산</option>
          <option value="울산">울산</option>
          <option value="경남">경남</option>
          <option value="광주">광주</option>
          <option value="전남">전남</option>
          <option value="전북">전북</option>
          <option value="제주">제주</option>
        </select>
        <select
          class="form-select"
          aria-label="Default select example"
          v-model="gender"
        >
          <option value="">성별선택</option>
          <option value="m">남성</option>
          <option value="fm">여성</option>
          <option value="both">성별무관</option>
        </select>
        <select
          class="form-select"
          aria-label="Default select example"
          v-model="level"
          :aria-selected="matchStore.condition.level"
        >
          <option value="0">레벨 선택</option>
          <option value="1">아마추어</option>
          <option value="2">세미프로</option>
          <option value="3">프로</option>
        </select>
      </div>
      <div id="matchList">
        <div class="match" v-for="match in matchList" :key="match.id">
          <div class="matchInner">
            <strong>{{ match.matchDate.substring(11) }}</strong>
            <div>
              <RouterLink
                :to="{
                  name: 'detailMatch',
                  params: { matchId: match.matchId },
                }"
                ><p id="courtName">{{ match.courtName }}</p></RouterLink
              >
              <p id="matchDetail">
                - {{ match.matchGender }} - {{ match.courtCapacity / 2 }}vs{{
                  match.courtCapacity / 2
                }}
                - 레벨 : {{ match.matchLevel }}
              </p>
            </div>
          </div>
          <button
            v-if="match.courtCapacity - match.matchApplicantCnt > 0"
            class="applyBtn"
            @click="detailMatch(match.matchId)"
          >
            신청가능!
          </button>
          <button
            disabled
            v-else
            class="soldoutBtn"
            @click="detailMatch(match.matchId)"
          >
            마감!
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// import
import { watch, ref, onMounted, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import { useMatchStore } from "@/stores/matchStore";

const matchStore = useMatchStore();
const router = useRouter();
const matchList = ref([]);

//매치 상세 보기 이동
const detailMatch = async (id) => {
  await matchStore.getMatch(id);
  router.push({ name: "detailMatch", params: { matchId: id } });
};
//검색 조건
const date = ref("");
const gender = ref("");
const level = ref(0);
const region = ref("");

const selectedColor = ref("orange");
const attrs = ref([
  {
    key: "test",
    highlight: true,
    dates: matchStore.condition.date,
  },
]);
// 날짜변경
const show = (day, event) => {
  date.value = day.id;
  attrs.value[0].dates = date.value;
};
// 캘린더 설정
//검색조건에 따라 매치 검색하는 함수
const loadMatchList = () => {
  matchStore.condition = {
    date: date.value,
    gender: gender.value,
    level: level.value,
    region: region.value,
  };
  matchStore.getMatchList(matchStore.condition);
  matchList.value = matchStore.matchList;
};

onBeforeMount(() => {
  date.value = matchStore.condition.date;
  gender.value = matchStore.condition.gender;
  level.value = matchStore.condition.level;
  region.value = matchStore.condition.region;
});
onMounted(async () => {
  await matchStore.getMatchList(matchStore.condition);
  matchList.value = matchStore.matchList;
});
// 검색조건이 바뀔때마다 함수실행

watch(
  () => matchStore.matchList,
  (newValue) => {
    matchList.value = newValue;
  },
  { immediate: true }
);
watch(
  () => date.value,
  (newValue) => {
    loadMatchList();
  }
);
watch(
  () => gender.value,
  (newValue) => {
    loadMatchList();
  }
);
watch(
  () => level.value,
  (newValue) => {
    loadMatchList();
  }
);
watch(
  () => region.value,
  (newValue) => {
    loadMatchList();
  }
);

let a = 1;
const test = function () {
  console.log(a++);
};
</script>

<style scoped>
.container {
  width: 100%;
  margin-bottom: 10rem;
}

.mainImageContainer {
  width: 100%;
  height: 35rem;
  background-image: url("../../assets/mainpage.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}

.listContainer {
  width: 90%;
  margin: 30px;
  text-align: center;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 40px;
}

.selectContainer {
  display: flex;
  margin: 20px 0;
}

.form-select {
  width: 10rem;
  margin: 10px;
  border-radius: 20px;
  color: rgb(112, 110, 110);
  border: 1px solid rgb(197, 194, 194);
}

.match {
  margin: 20px 0;
  border-bottom: 1px solid rgb(197, 194, 194);
  padding: 0 4rem;
  display: flex;
  justify-content: space-between;
}

.matchInner {
  width: 20rem;
  display: flex;
  text-align: left;
}

strong {
  margin-right: 20px;
}

#courtName {
  margin-bottom: 0;
}

#courtName:hover {
  color: rgb(255, 116, 80);
  font-weight: 900;

  transition: all 0.26s ease-out;
}
#matchDetail {
  color: rgb(141, 138, 138);
  font-size: 0.9rem;
}

.applyBtn {
  border-radius: 10px;
  background-color: rgb(252, 246, 246);
  border: 1px solid rgb(255, 116, 80);
  font-size: 0.9rem;
  font-weight: 600;
  height: 2.4rem;
  width: 5rem;
  font-family: "Black Han Sans", sans-serif;
  font-family: "IBM Plex Sans KR", sans-serif;
}

.applyBtn:hover {
  background-color: rgb(255, 116, 80);
  transition: all 0.2s ease-in;
  color: white;
}

.soldoutBtn {
  background-color: rgb(206, 204, 204);
  border-radius: 10px;
  border: 1px solid rgb(255, 116, 80);
  font-size: 0.9rem;
  height: 2.4rem;
  width: 5rem;
  font-family: "Black Han Sans", sans-serif;
  font-family: "IBM Plex Sans KR", sans-serif;
}

a {
  color: rgb(65, 64, 64);
  text-decoration: none;
}

#slogan {
  font-size: 4.5rem;
  font-family: "Do Hyeon", sans-serif;
  text-shadow: 2px 2px 3px rgb(255, 116, 80);
  color: white;
  position: relative;
  top: 12rem;
  left: 10rem;
  animation-name: move;
  animation-duration: 2s;
  animation-iteration-count: 1;
  animation-fill-mode: forwards;
  width: 42rem;
}

#sloganStr,
#sloganEnd {
  margin: 0;
  width: 50rem;
}

#sloganEnd {
  font-size: 7rem;
  color: rgb(255, 96, 43);
  text-shadow: 2px 2px 2px white;
}
@keyframes move {
  0% {
    left: 0;
    top: -100px;
  }
  10% {
    top: 230px;
  }
  25% {
    top: 100px;
  }
  40% {
    top: 230px;
  }
  55% {
    top: 130px;
  }
  65% {
    top: 230px;
  }
  74% {
    top: 170px;
  }
  80% {
    top: 230px;
  }
  84% {
    top: 180px;
  }
  88% {
    top: 230px;
  }
  92% {
    top: 200px;
  }
  96% {
    top: 230px;
  }
  97% {
    top: 220px;
  }
  98% {
    top: 230px;
  }
  99% {
    top: 225px;
  }
  100% {
    left: 300px;
    top: 230px;
  }
}

#matchList {
  background-color: white;

  padding: 0.5rem 1rem 0.1rem 1rem;

  border-radius: 5px;
}
</style>
