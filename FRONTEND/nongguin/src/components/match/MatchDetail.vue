<template>
  <div id="topLine"></div>
  <div class="container">
    <img :src="`/src/assets/courtPic/${matchStore.match.courtId}.jpg`" alt="" />
    <div class="divideLeftRight">
      <div>
        <div class="leftContainer">
          <strong id="strong">매치 포인트</strong>
          <div class="leftContainerTitle">
            <div class="leftContainerInner">
              <p>
                <i class="fa-solid fa-star"></i
                >{{ matchStore.match.matchLevel }}
              </p>
            </div>
            <div class="leftContainerInner">
              <p>
                <i class="fa-solid fa-venus-mars"></i
                >{{ matchStore.match.matchGender }}
              </p>
            </div>
            <div class="leftContainerInner">
              <p>
                <i class="fa-solid fa-basketball"></i>
                {{ matchStore.match.courtCapacity / 2 }} vs
                {{ matchStore.match.courtCapacity / 2 }}
              </p>
            </div>
            <div class="leftContainerInner">
              <p>
                <i class="fa-solid fa-people-group"></i>현재
                {{ matchStore.match.matchApplicantCnt }}명 참여 중
              </p>
            </div>
          </div>
        </div>
        <!-- 경기장 정보 -->
        <div class="leftContainer">
          <strong id="strong">경기장 정보</strong>
          <div class="leftContainerTitle">
            <div class="leftContainerInner">
              <p><i class="bi bi-grid-3x3-gap"></i>28m x 15m</p>
            </div>
            <div class="leftContainerInner">
              <p><i class="fa-solid fa-shower"></i>샤워가능</p>
            </div>
            <div class="leftContainerInner">
              <p><i class="fa-solid fa-car-side"></i>주차가능</p>
            </div>
            <div class="leftContainerInner">
              <p><i class="fa-solid fa-shoe-prints"></i>농구화 대여 가능</p>
            </div>
            <div class="leftContainerInner">
              <p><i class="fa-regular fa-clock"></i>9:00 ~ 21:00</p>
            </div>
          </div>
        </div>
        <div class="leftContainer">
          <strong id="strong">구장 소개</strong>
          <br />
          <p>{{ matchStore.match.courtDes }}</p>
        </div>
        <div class="leftContainer">
          <strong id="strong">구장 특이사항 </strong>
          <br /><br />
          <p>
            <i class="fa-solid fa-route"> 농구장 가는 길</i><br /><br />
            ▪엘리베이터를 이용하여 8층 농구장으로 이동(주말 오전 9시 매치 입장
            시 후문[정문에서 코스트코 방향으로 이동]을 통해 8시 40분부터
            출입가능)
          </p>
          <br /><br />
          <p>
            <i class="fa-solid fa-car"> 주차 </i><br /><br />▪매치 신청 시
            선착순 3대 2시간 무료(롯데마트 8층 주차장은 사용 불가)
            <br />
            ▪주차 미등록자는 더에프 사무실 직원에게 문의 - 주차 등록은 매치 30분
            이내 변경 및 신청 불가(이후 10분당 500원)
            <br />▪ 21시 이후 입차 시 주차비용 발생
            <br />
            ▪연타임 주차 신청 가능<br />
            (출차 시 부스에 이야기하면 연타임 처리)<br />
            (* 종종 주차누락이 있어 무료 주차 사전등록을 완료했음에도 1층 주차
            차단기에서 통과가 안된다면, 사전 주차등록한 차량의 번호를 주차부스
            담당자에게 소통해주세요.)
          </p>
          <br /><br />
          <p>
            <i class="fa-solid fa-shoe-prints"> 농구화</i><br /><br />
            ▪대여 가능 (3,000원, Size : 250~285) <br />▪구장 입구에 있는 8층
            더에프 사무실에서 대여 (사이즈별 재고 여부는 구장 측으로 문의
            주세요.)
          </p>
          <br /><br />
          <p>
            <i class="fa-solid fa-dumbbell"> 워밍업존</i><br /><br />
            ▪농구장 정면에 워밍업존 무료 이용 가능 <br />▪패스 리바운더, 사이클,
            벤치프레스, 스텝 레더 등
          </p>
          <br /><br />
          <p>
            <i class="fa-solid fa-smoking"> 흡연</i><br /><br />
            ▪농구장 옆에 흡연장 이용 <br />▪흡연 구역 외 마트내 흡연 적발시 퇴장
            조치 및 과태료 부과
          </p>
          <br /><br />
          <p>
            <i class="fa-solid fa-restroom"> 화장실</i> <br /><br />
            ▪7층 화장실 이용
          </p>
          <br /><br />
          <p>
            <i class="fa-solid fa-ban"> 무단 출입 금지</i><br /><br />
            ▪레슨용 시설 더에프랩(실내)와 스킬피치 앞에 무단 출입금지 안내문이
            비치되어 있습니다.
          </p>
          <br /><br />
          <p>
            <i class="fa-solid fa-question"> 기타 </i><br /><br />
            ▪긴급 시 사용 가능한 자동 제세동기가 8F 더에프 필드 사무실 옆 벽면에
            설치돼 있습니다.
          </p>
        </div>
      </div>
      <!-- 우측 컨테이너 -->
      <div class="rightContainer">
        <strong id="strong" style="text-decoration: none"
          >{{ new Date(matchStore.match.matchDate).getFullYear() }}년
          {{ new Date(matchStore.match.matchDate).getMonth() + 1 }}월
          {{ new Date(matchStore.match.matchDate).getDate() }}일
          {{ dayResult }}
          {{ new Date(matchStore.match.matchDate).getHours() }}시</strong
        >
        <p id="courtName">{{ matchStore.match.courtName }}</p>
        <p>▪주소 : {{ matchStore.match.courtAddress }}</p>
        <p>▪연락처 : {{ matchStore.match.courtTel }}</p>
        <div id="map"></div>
        <hr />
        <span id="matchFee"
          ><strong id="fee"
            ><i class="fa-solid fa-won-sign"></i
            >{{ matchStore.match.matchFee }}</strong
          >
          / 2시간</span
        >
        <p v-if="userStore.loginUserId==0" style="text-align: center">
          로그인 후 신청할 수 있습니다.<br /><br />
          <RouterLink :to="{ name: 'userSignin' }" class="goToLogin"
            >로그인하러가기</RouterLink
          >
        </p>
        <button id="applyBtn" @click="goPayment" v-else-if="flag">
          참가 신청
        </button>
        <div v-else>
          <p>이미 신청한 매치입니다.</p>
          <button id="applyBtn" @click="cancelMatch(matchStore.match.matchId)">
            참가 취소
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { watch, ref, onMounted, onBeforeMount } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useMatchStore } from "@/stores/matchStore";
import { useUserStore } from "@/stores/userStore";
import { storeToRefs } from "pinia";

const userStore = useUserStore();
const matchStore = useMatchStore();
const { IMP } = window;
const amount = ref(110);
const router = useRouter();
const route = useRoute();
const tokenData = ref([]);
const url = ref({
  "background-image": "",
});

const day = ref(0);
const dayResult = ref("");

const flag = ref(true);

onBeforeMount(async () => {
  await matchStore.getMatch(route.params.matchId);
  await matchStore.getMyMatchList(userStore.loginUserId);
  // window.scrollTo(0, 0)
});
watch(
  () => matchStore.myMatchList,
  (newMatch, oldMatch) => {
    if (matchStore.myMatchList == "") {
      flag.value = true;
      return;
    }
    matchStore.myMatchList.forEach((match) => {
      if (match.matchId == route.params.matchId) {
        flag.value = false;
      }
    });
  }
);

//결제
const goPayment = function () {
  if (window.confirm("결제하시겠습니까?")) {
    const IMP = window.IMP; // 생략 가능
    IMP.init("imp77607565"); // Example: imp00000000
    IMP.request_pay(
      {
        // param
        pg: "kakaopay",
        pay_method: "card",
        name: matchStore.match.courtName,
        amount: matchStore.match.matchFee,
        buyer_email: "ggwang@gmail.com",
        buyer_name: "김중광",
        buyer_tel: "010-4242-4242",
        buyer_addr: "수원시 화성",
        buyer_postcode: "01181",
        m_redirect_url: "http://localhost:5173/mypage", // 모바일 결제후 리다이렉트될 주소!!
      },
      async (rsp) => {
        // callback
        if (rsp.success) {
          // 결제 성공시
          tokenData.value = JSON.parse(
            atob(sessionStorage.getItem("access-token").split(".")[1])
          );
          await axios({
            url: "http://localhost:8080/payment/payment",
            method: "post",
            headers: {
              "Content-Type": "application/json",
              "access-token": sessionStorage.getItem("access-token"),
            },
            data: {
              paymentImpUid: rsp.imp_uid,
              paymentDate: new Date(),
              paymentAmount: matchStore.match.matchFee,
              matchId: matchStore.match.matchId,
              userId: tokenData.value["userId"],
            },
          })
            .then((data) => {
              async (requestPayResponse) => {
                const { success, error_msg } = requestPayResponse;
                if (!success) {
                  alert(`결제에 실패하였습니다. 에러 내용: ${error_msg}`);
                  return;
                }
                // 이전 단계에서 구현한 결제정보 사후 검증 API 호출
                const res = await axios({
                  url: "/payments/complete",
                  method: "post",
                  headers: { "Content-Type": "application/json" },
                  data: {
                    imp_uid: rsp.imp_uid,
                    merchant_uid: rsp.merchant_uid,
                  },
                });
              };
              router.push({ name: "mypageResMatch" });
            })
            .catch((err) => {
              console.log(rsp.imp_uid);
              console.log(err);
            });
        } else {
          // 결제 실패시
          alert(`결제에 실패하였습니다.`);
        }
      }
    );
  }
};
//취소
const cancelMatch = function (matchId) {
  if (window.confirm("취소하시겠습니까?")) {
    const userId = userStore.user.userId;
    console.log(userId);
    console.log(matchId);
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
        matchStore.getMyMatchList(userStore.loginUserId);
        router.push({
          name: detailMatch,
          params: { matchId: route.params.matchId },
        });
      })
      .catch((err) => console.log(err));
  }
};

const initMap = function () {
  var mapContainer = document.getElementById("map"), // 지도를 표시할 div
    mapOption = {
      center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
      level: 3, // 지도의 확대 레벨
    };

  // 지도를 생성합니다
  var map = new kakao.maps.Map(mapContainer, mapOption);

  // 주소-좌표 변환 객체를 생성합니다
  var geocoder = new kakao.maps.services.Geocoder();

  // 주소로 좌표를 검색합니다
  geocoder.addressSearch(
    matchStore.match.courtAddress,
    function (result, status) {
      // 정상적으로 검색이 완료됐으면
      if (status === kakao.maps.services.Status.OK) {
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
          map: map,
          position: coords,
        });

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
      }
    }
  );
};

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script"); // autoload=false 스크립트를 동적으로 로드하기 위해서 사용
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7e8bf018267e88ec9100327188b89911&libraries=services`;
    script.addEventListener("load", () => {
      kakao.maps.load(initMap);
    }); //헤드태그에 추가
    document.head.appendChild(script);
  }
  watch(
    () => matchStore.match,
    (newMatch, oldMatch) => {
      initMap();
    }
  );
  const d = new Date(matchStore.match.matchDate);
  day.value = d.getDay();
  const dayName = [
    "일요일",
    "월요일",
    "화요일",
    "수요일",
    "목요일",
    "금요일",
    "토요일",
  ];
  dayResult.value = dayName[day.value];
});

const myMarkerPosition = ref([[33.450701, 126.570667]]);

const markers = ref([]);
</script>

<style scoped>
#map {
  width: 23rem;
  height: 23rem;
  border-radius: 10px;
  margin: 1rem auto;
}
#topLine {
  margin: 0;
  margin-bottom: 1rem;
  padding: 0;
  height: 0.4rem;
  background-color: rgb(255, 116, 80);
}
.container {
  display: flex;
  flex-direction: column;

  width: 70rem;
  margin: 0 auto;
  /* border: 1px solid black; */
}

.leftContainer {
  margin: 1rem 0;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;

  width: 40rem;

  /* border: 3px double black; */
  border-radius: 10px;
  background-color: white;
}
.rightContainer {
  margin: 1rem 0 1rem 1rem;
  padding: 1rem 0 1rem 1rem;
  display: flex;
  flex-direction: column;

  width: 32rem;
  height: 42rem;
  border-radius: 10px;
  background-color: white;

  position: sticky;
  top: 10px;
  font-family: "Hi Melody", sans-serif;
  font-family: "Nanum Gothic", sans-serif;
}

.leftContainerTitle {
  display: flex;
  flex-wrap: wrap;
  font-family: "Hi Melody", sans-serif;
  font-family: "Nanum Gothic", sans-serif;
}
.leftContainerInner {
  margin-left: 0.2rem;

  padding-top: 0.4rem;
  width: 40%;
  display: flex;
}
i {
  margin-right: 0.7rem;
}

#strong {
  font-size: 1.2rem;
  color: rgb(255, 61, 43);
  text-decoration: underline;

  margin: 10px 0 5px 10px;
}

p {
  font-size: 1rem;
  font-family: "Hi Melody", sans-serif;
  font-family: "Nanum Gothic", sans-serif;

  display: inline-block;
  padding: 0 2rem;
}

.divideLeftRight {
  display: flex;
}

#courtName {
  font-size: 1.36rem;
  font-weight: 550;

  margin-top: 1rem;
}

#matchFee {
  margin: 0 1rem 1rem 1rem;

  color: rgb(97, 93, 93);
}

#applyBtn {
  width: 8rem;
  margin: 0.5rem auto;

  border-radius: 5px;
  background-color: rgb(252, 246, 246);
  border: 2px solid rgb(255, 116, 80);
  font-size: 0.9rem;
  height: 2.4rem;
}

#applyBtn:hover {
  background-color: rgb(255, 116, 80);
  transition: all 0.15s ease-out;
  color: white;
}

.goToLogin {
  color: rgb(255, 116, 80);
}

img {
  width: 100%;
  height: 22rem;
  margin: 0;
  padding: 0;
}

#line {
  height: 1rem;
}

#fee {
  text-decoration: none;
}
</style>
