import { createRouter, createWebHistory } from "vue-router";
// views
import matchView from "@/views/MatchView.vue";
import userView from "@/views/UserView.vue";
import myPageView from "@/views/MyPageView.vue";

// user components
import userSignin from "@/components/user/UserSignin.vue";
import userSignup from "@/components/user/UserSignup.vue";
import userSignupComplete from "@/components/user/UserSignupComplete.vue";

// match components
import matchList from "@/components/match/MatchList.vue";
import matchDetail from "@/components/match/matchDetail.vue";
import friendMatch from "@/components/match/friendMatch.vue";

// mypage components
import mypage from "@/components/mypage/mypage.vue";
import mypageUpdate from "@/components/mypage/mypageUpdate.vue";
import mypageFriend from "@/components/mypage/mypageFriend.vue";
import myMatch from "@/components/mypage/myMatch.vue";
import myResMatch from "@/components/mypage/myResMatch.vue";
import mypageFriendReq from "@/components/mypage/mypageFriendReq.vue";

// review components
import reviewRegist from "@/components/review/ReviewRegist.vue";

//router
const router = createRouter({
  mode: "history",
  history: createWebHistory(import.meta.env.BASE_URL),
  scrollBehavior() {
    return { top: 0 };
  },

  routes: [
    {
      path: "/",
      name: "match",
      children: [
        {
          path: "",
          name: "matchList",
          component: matchList,
        },
        {
          path: "detailmatch/:matchId",
          name: "detailMatch",
          component: matchDetail,
        },
      ],
    },
    {
      path: "/user",
      name: "user",
      children: [
        {
          path: "",
          name: "userSignin",
          component: userSignin,
        },
        {
          path: "signup",
          name: "signup",
          component: userSignup,
        },
        {
          path: "completesignup",
          name: "completesignup",
          component: userSignupComplete,
        },
      ],
    },
    {
      path: "/mypage",
      name: "mypage",
      component: myPageView,
      children: [
        {
          path: "",
          name: "mypageInfo",
          component: mypage,
        },
        {
          path: "/update",
          name: "mypageUpdate",
          component: mypageUpdate,
        },
        {
          path: "/friend",
          name: "mypageFriend",
          component: mypageFriend,
        },
        {
          path: "/friendreq",
          name: "mypageFriendReq",
          component: mypageFriendReq,
        },
        {
          path: "/match",
          name: "mypageMatch",
          component: myMatch,
        },
        {
          path: "/resmatch",
          name: "mypageResMatch",
          component: myResMatch,
        },
        {
          path: "/friendmatch",
          name: "friendMatch",
          component: friendMatch,
        },
      ],
    },
    {
      path: "/review",
      name: "reviewRegist",
      component: reviewRegist,
    },
  ],
  scrollBehavior() {
    return { top: 0, behavior: "instant" };
  },
});

export default router;
