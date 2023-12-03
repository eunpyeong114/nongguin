<template>
  <div id="footer">
    <div id="box">
      <img
        id="logoTag"
        src="@/assets/nongguin-logo-dark2.png"
        @click="goToMain"
      />
      <div id="pBox1">
        <br />
        <p>농구하고 싶을땐, 농구인</p>
        <p>이용약관 | 개인정보 처리방침 | 사업자 정보 확인</p>
        <p>
          농구인 | 서울특별시 강남구 테헤란로 212 (역삼동 718-5번지) 멀티캠퍼스
          역삼
        </p>
      </div>
      <div id="pBox2">
        <br />
        <p>대표 메일 | card0923@naver.com</p>
        <p>대표 전화 | 010-1234-5678</p>
        <p>주식회사 김앤전 | 대표 전은평</p>
        <p>copyright nongguin All rights reserved</p>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
#footer {
  background-color: grey;
  display: flex;
}
#logoTag {
  width: 150px;
  height: 150px;
}
#box {
  display: flex;
  width: 60%;
  margin: 0 auto;
  justify-content: space-around;
}
#pBox1 p {
  font-size: 0.8rem;
  margin-bottom: 0;
  color: rgb(245, 244, 242);
}
#pBox2 p {
  font-size: 0.8rem;
  margin-bottom: 0;
  color: rgb(245, 244, 242);
}
#pBox {
  padding: 0.2rem;
}
</style>
