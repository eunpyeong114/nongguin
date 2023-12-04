# FINAL PJT nongguin

## 팀장:전은평, 팀원:김중광

### ✔ 작성 정보

작 성 자 : 김중광, 전은평  
날 짜: 2023 11 23  
환 경 : Windows 10 Pro  
개발도구 : Visual Studio Code, Google Chrome , sts workbench
사용 프레임워크 : Bootstrap

### ✔ 프로젝트 요구사항

1. 기존 진행한 관통 프로젝트의 요구사항을 정리하고 페어 별로 프로젝트에 적용할
   아이디어를 추가하여 Final Project를 완성한다.
2. SpringBoot와 MyBatis연동을 활용한 RestAPI 서버 구현
3. Vue3버전을 활용한 SPA 클라이언트 구현

### ✔ 프로젝트 목표

- 과정에서 배운 Web Front 기술인 HTML / CSS / Javascript를 이해하고 활용할 수
  있다.
- CSS, Bootstrap을 활용해서 향상된 디자인을 구현할 수 있다.
- UX(사용자 경험), UI(사용자 인터페이스)를 고려한 사이트를 구현하여 사용자  
  입장에서 편리하고 멋진 디자인을 설계해 본다.

## ERD

![ERD](/assets/diagram.PNG)

## 클래스 다이아그램

![클래스 다이아그램](/BACKEND/nongguinUML.png)

## 요구사항 명세서

![요구사항 명세서](/assets/요구사항명세서.PNG)

## 페이지 명세서

![페이지 명세서](/assets/페이지명세서.PNG)

## SWAGGER

![SWAGGER](/assets/SWAGGER.png)

## ⌨ 전체 코드

### 📁폴더 구조

```
📁  PJT-FINAL-M-JEP-KJK
    📁BACKEND
        📁 src
            📦main
            ┣ 📂java
            ┃ ┗ 📂com
            ┃ ┃ ┗ 📂nongguin
            ┃ ┃ ┃ ┣ 📂config
            ┃ ┃ ┃ ┃ ┣ 📜PropertyConfig.java
            ┃ ┃ ┃ ┃ ┣ 📜SwaggerConfig.java
            ┃ ┃ ┃ ┃ ┗ 📜WebConfig.java
            ┃ ┃ ┃ ┣ 📂controller
            ┃ ┃ ┃ ┃ ┣ 📜FriendController.java
            ┃ ┃ ┃ ┃ ┣ 📜MatchController.java
            ┃ ┃ ┃ ┃ ┣ 📜PaymentController.java
            ┃ ┃ ┃ ┃ ┗ 📜UserController.java
            ┃ ┃ ┃ ┣ 📂interceptor
            ┃ ┃ ┃ ┃ ┗ 📜JwtInterceptor.java
            ┃ ┃ ┃ ┣ 📂model
            ┃ ┃ ┃ ┃ ┣ 📂dao
            ┃ ┃ ┃ ┃ ┃ ┣ 📜FriendDao.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜MatchDao.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜Match_UserDao.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜PaymentDao.java
            ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDao.java
            ┃ ┃ ┃ ┃ ┣ 📂dto
            ┃ ┃ ┃ ┃ ┃ ┣ 📜Court.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜Friend.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜Match.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜Match_User.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜Payment.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜SearchCondition.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜SearchResult.java
            ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java
            ┃ ┃ ┃ ┃ ┗ 📂service
            ┃ ┃ ┃ ┃ ┃ ┣ 📜FriendService.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜FriendServiceImpl.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜MatchService.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜MatchServiceImpl.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜Match_UserService.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜Match_UserServiceImpl.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜PaymentService.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜PaymentServiceImpl.java
            ┃ ┃ ┃ ┃ ┃ ┣ 📜UserService.java
            ┃ ┃ ┃ ┃ ┃ ┗ 📜UserServiceImpl.java
            ┃ ┃ ┃ ┣ 📂util
            ┃ ┃ ┃ ┃ ┗ 📜JwtUtil.java
            ┃ ┃ ┃ ┗ 📜NongguinServerApplication.java
            ┗ 📂resources
            ┃ ┣ 📂mappers
            ┃ ┃ ┣ 📜FriendMapper.xml
            ┃ ┃ ┣ 📜MatchMapper.xml
            ┃ ┃ ┣ 📜Match_UserMapper.xml
            ┃ ┃ ┣ 📜PaymentMapper.xml
            ┃ ┃ ┗ 📜UserMapper.xml
            ┃ ┣ 📜application-env.properties
            ┃ ┗ 📜application.properties
    📁FRONTEND
        📁nongguin
            📁src
                📦src
                ┣ 📂assets
                ┃ ┣ 📂courtPic
                ┣ 📂components
                ┃ ┣ 📂common
                ┃ ┃ ┣ 📜Footer.vue
                ┃ ┃ ┗ 📜HeaderNav.vue
                ┃ ┣ 📂match
                ┃ ┃ ┣ 📜FriendMatch.vue
                ┃ ┃ ┣ 📜MatchDetail.vue
                ┃ ┃ ┗ 📜MatchList.vue
                ┃ ┣ 📂mypage
                ┃ ┃ ┣ 📜myMatch.vue
                ┃ ┃ ┣ 📜mypage.vue
                ┃ ┃ ┣ 📜mypageFriend.vue
                ┃ ┃ ┣ 📜mypageFriendReq.vue
                ┃ ┃ ┣ 📜mypageUpdate.vue
                ┃ ┃ ┗ 📜myResMatch.vue
                ┃ ┗ 📂user
                ┃ ┃ ┣ 📜UserSignIn.vue
                ┃ ┃ ┣ 📜UserSignInComplete.vue
                ┃ ┃ ┣ 📜UserSignUp.vue
                ┃ ┃ ┗ 📜UserSignUpComplete.vue
                ┣ 📂router
                ┃ ┗ 📜index.js
                ┣ 📂stores
                ┃ ┣ 📜friendStore.js
                ┃ ┣ 📜matchStore.js
                ┃ ┣ 📜nongGuIn.js
                ┃ ┗ 📜userStore.js
                ┣ 📂views
                ┃ ┣ 📜MatchView.vue
                ┃ ┣ 📜MyPageView.vue
                ┃ ┗ 📜UserView.vue
                ┣ 📜App.vue
                ┗ 📜main.js


```

## 구현결과

### 회원가입

![회원가입](/assets/signup.gif)

### 로그인

![로그인](/assets/signin.gif)

### 유저정보수정

![유저정보수정](/assets/updatemy.gif)

### 매치목록

![매치목록](/assets/matchlist.gif)

### 매치디테일

![매치디테일](/assets/matchdetail.gif)

### 매치 신청 (결제)

![결제](/assets/payment.gif)

### 매치 취소 (결제 취소)

![결제취소](/assets/cancelpay.gif)

### 친구(조회, 요청조회, 수락 , 거절)

![친구](/assets/friend.gif)

### 회원 탈퇴

## ![회원탈퇴](/assets/signout.gif)

## 💡구현방법

### BACKEND

    FriendController : 친구와 관련된 API 생성
        - getAllFriends(int userId) : userId로 나와 친구 관계인 유저 전체 조회
        - requestFriend(Friend friend) : 친구 요청 보냄
        - getRequestsByUserId(int userId) : userId로 받은 친구 요청 전체 조회
        - acceptRequest(Friend friend) : 받은 친구 요청 수락
        - refuseRequest(Friend friend) : 받은 친구 요청 거절
        - getFriendMatch(int userId) : 나와 친구의 유저의 매치 조회

    MatchController : 매치와 관련된 API 생성
        - getMatchsByDay(String matchDate) : 날짜별 전체 매치 조회
        - getMatchsBySearchCondition(SearchCondition searchCondition) : 검색 조건별 전체 매치 조회
        - getMatchByMatchId(int matchId) : 매치 id로 해당 매치 정보 조회
        - getMatchsByUserId(int userId) : 유저 id로 유저가 경혐했던, 앞으로 경험할 매치 전체  조회
        -  getRemainingMatchsByUserId(int userId) : 유저 id로 앞으로 유저가 경험할 매치 내역 조회

    UserController : 유저와 관련된 API 생성
        - signUp(User user) : 회원가입
        - signIn(User user) : 로그인
        - logout(HttpSession session) : 로그아웃
        - deleteUser(int userId) : 회원탈퇴
        - getUserByUserId(int userId) : 유저 id로 유저 정보 조회
        - modifyUser(User user) : 유저 정보 수정
        - getUserByUserEmail(String userEmail) : 유저 email로 유저 정보 조회

    - PaymentController : 결제와 관련된 API 생성
        - pay(Payment payment) : 매치 신청시 결제
        - cancelPayment(Payment payment) : 결제 취소

### FRONTEND

#### store

    userStore : REST api 유저 컨트롤러를 이용하여 데이터를 받아와 관리한다
        - loginUserEmail,loginUserId :  로그인 된 유저 정보 (이메일/id)를 저장한다.
        - delLoginUserId :  로그인 된 유저 정보 삭제한다.
        - user,getUser,dropUser : 로그인된 유저의 모든 정보 조회/삭제한다.
        - signin,signup : REST api를 이용한 로그인/회원가입을 한다.
        - updateUser,deleteUser : 유저 정보 수정, 회원탈퇴를 한다.

    friendStore : REST api 친구 컨트롤러를 이용하여 데이터를 받아와 관리한다
        - friendList,getFriendList : 친구 목록을 조회한다.
        - friendReqList,getFriendReqList : 친구 요청 목록을 조회한다.
        - reqId,emailToId : 친구 요청을 보낼 이메일을 받아 아이디를 조회한다.
        - reqFriend : 조회된 아이디로 실제로 친구요청을 보낸다.
        - refuseReqFriend,accpetReqFriend : 친구 요청 수락, 거절한다.
        - friendMatchList,getFriendMatchList : 친구의 예정된 매치를 조회한다.

    matchStore : REST api 매치 컨트롤러를 이용하여 데이터를 받아와 관리한다
        - condition : 검색 조건을 저장 한다.
        - matchList,getMatchList : 매치 목록을 조회한다.
        - match,getMatch : 매치를 조회한다.
        - myMatchList,getMyMatchList : 내 매치 기록을 조회한다.
        - myResMatchList,getResMyMatchList : 내 예정된 매치들을 조회한다.

#### components

    common
        - HeaderNav : 로그인 매치목록 마이페이지로 이동하거나, 로그아웃을 할수 있다.
        - footer: 화면의 아래에 보여지는 푸터입니다.
    match
        - FriendMatch.vue : 친구의 매치목록을 보여준다.
        - MatchDetail.vue : 매치의 자세한 정보를 보여주며 매치를 신청 할 수 있다. 카카오맵api를 이용해 지도를 보여준다. 사용자의 상태에 따라 로그인을 요구하거나 신청버튼을 보여주거나 이미 신청한 매치임을 보여준다.
        - MatchList.vue : 예정된 매치 목록을 조회한다. 날짜, 지역, 성별, 난이도에 따라 매치를 조회할 수 있다.
    mypage
        - myMatch.vue : 나의 매치 기록을 보여준다.
        - mypage.vue : 나의 정보를 보여준다.
        - mypageFriend.vue : 친구 목록을 보여주며 친구의 예정된 매치를 볼수 있는 버튼이 있다.
        - mypageFriendReq.vue : 친구 요청 목록을 보여주며 수락 취소 버튼이 있다.
        - mypageUpdate.vue : 나의 정보를 수정할 수 있다.
        - myresMatch.vue : 나의 예정된 매치를 보여준다.
    user
        - userSignin.vue : 로그인을 할 수 있다.
        - userSignup.vue : 회원가입을 할 수 있다.
        - UserSignUpComplete.vue:  회원가입 완료 페이지이고 로그인페이지로 이동 할 수 있다.

#### router

    index.js : 라우터 링크 정보

#### views

    MatchView.vue : 자식 컴포넌트 들을 보여준다.
    MypageView.vue : 네비게이션을 누르면 자식 컴포넌트 들을 보여준다.
    UserView.vue : 자식 컴포넌트 들을 보여준다.
