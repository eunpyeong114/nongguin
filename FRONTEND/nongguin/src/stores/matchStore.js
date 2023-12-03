import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'
//매치 스토어
export const useMatchStore = defineStore('match', () => {
    const REST_MATCH_API = `http://localhost:8080/match/search`
    const condition=ref({
        date: +new Date()+3240000,
        gender: "both",
        level: 0,
        region: ""
      })
    // 매치목록 조회
    const matchList  = ref([])
    const getMatchList= function(condition){
        axios({
        url: REST_MATCH_API+"/condition",
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "access-token": sessionStorage.getItem("access-token")
        },
        data: condition
        })
        .then((res) => {
            
            matchList.value=res.data
            const nowTimeStamp = +new Date()
            
            matchList.value.forEach((match) => {
            if (match.matchGender == 'm') {
                match.matchGender = '남성'
            } else if (match.matchGender == 'fm') {
                match.matchGender= '여성'
            } else if (match.matchGender == "both") {
                match.matchGender= '성별무관'
            }

            if (match.matchLevel == '1') {
                match.matchLevel = '아마추어'
            } else if (match.matchLevel == '2') {
                match.matchLevel= '세미프로'
            } else if (match.matchLevel == "3") {
                match.matchLevel= '프로'
            }
            let date = new Date(match.matchDate);

            date.setHours(date.getHours() -9);

            let year = date.getFullYear();
            let month = (date.getMonth() + 1).toString().padStart(2, '0');
            let day = date.getDate().toString().padStart(2, '0');
            let hours = date.getHours().toString().padStart(2, '0');
            let minutes = date.getMinutes().toString().padStart(2, '0');

            var formattedDate = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes;
            match.matchDate = formattedDate;
            })
            
            matchList.value = matchList.value.filter((match)=> +new Date(match.matchDate)>nowTimeStamp )
        })
        .catch((err) => {
        console.log(err)
        })
    }

    //매치 조회
    const match = ref()
    const getMatch = function(id){
        axios({
        url: REST_MATCH_API+"/detail/"+id,
        method: 'GET'
        })
        .then((res)=>{
        match.value=res.data
        if (match.value.matchGender == 'm') {
            match.value.matchGender = '남성'
        } else if (match.value.matchGender == 'fm') {
            match.value.matchGender= '여성'
        } else if (match.value.matchGender == "both") {
            match.value.matchGender= '성별무관'
        }
        if (match.value.matchLevel == '1') {
            match.value.matchLevel = '아마추어'
        } else if (match.value.matchLevel == '2') {
            match.value.matchLevel= '세미프로'
        } else if (match.value.matchLevel == "3") {
            match.value.matchLevel= '프로'
        }
        let date = new Date(match.value.matchDate);

        date.setHours(date.getHours() -9);

        let year = date.getFullYear();
        let month = (date.getMonth() + 1).toString().padStart(2, '0');
        let day = date.getDate().toString().padStart(2, '0');
        let hours = date.getHours().toString().padStart(2, '0');
        let minutes = date.getMinutes().toString().padStart(2, '0');

        var formattedDate = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes;
        match.value.matchDate = formattedDate;
        })
        .catch((err)=>{
        console.log(err)
        })
    }
    // 내 모든 매치 조회
    const myMatchList = ref([])
    const getMyMatchList =  function(id){
        axios({
        url: REST_MATCH_API+"/user/"+id,
        method: 'GET',
        headers: {
            "access-token": sessionStorage.getItem("access-token")
        },
        })
        .then((res)=>{
            myMatchList.value=res.data
            myMatchList.value.forEach((match) => {
            if (match.matchGender == 'm') {
            match.matchGender = '남성'
            } else if (match.matchGender == 'fm') {
            match.matchGender= '여성'
            } else if (match.matchGender == "both") {
            match.matchGender= '성별무관'
            }
            if (match.matchLevel == '1') {
                match.matchLevel = '아마추어'
            } else if (match.matchLevel == '2') {
                match.matchLevel= '세미프로'
            } else if (match.matchLevel == "3") {
                match.matchLevel= '프로'
            }
            let date = new Date(match.matchDate);

            date.setHours(date.getHours() -9);

            let year = date.getFullYear();
            let month = (date.getMonth() + 1).toString().padStart(2, '0');
            let day = date.getDate().toString().padStart(2, '0');
            let hours = date.getHours().toString().padStart(2, '0');
            let minutes = date.getMinutes().toString().padStart(2, '0');

            var formattedDate = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes;
            match.matchDate = formattedDate;
        })
        })
        .catch((err) => {
        console.log(err)
        })
    }
    // 내 예정된 매치 조회
    const myResMatchList = ref([])
    const getResMyMatchList =  function(id){
        axios({
        url: REST_MATCH_API+"/reserve/"+id,
        method: 'GET',
        headers: {
            "access-token": sessionStorage.getItem("access-token")
        },
        })
        .then((res)=>{
        myResMatchList.value=res.data
        const nowTimeStamp = +new Date()+32400000
        myResMatchList.value = myResMatchList.value.filter((match)=> match.matchDate>nowTimeStamp )
        myResMatchList.value.forEach((match) => {
            if (match.matchGender == 'm') {
            match.matchGender = '남성'
            } else if (match.matchGender == 'fm') {
            match.matchGender= '여성'
            } else if (match.matchGender == "both") {
            match.matchGender= '성별무관'
            }
            if (match.matchLevel == '1') {
                match.matchLevel = '아마추어'
            } else if (match.matchLevel == '2') {
                match.matchLevel= '세미프로'
            } else if (match.matchLevel == "3") {
                match.matchLevel= '프로'
            }
            let date = new Date(match.matchDate);

            date.setHours(date.getHours() -9);

            let year = date.getFullYear();
            let month = (date.getMonth() + 1).toString().padStart(2, '0');
            let day = date.getDate().toString().padStart(2, '0');
            let hours = date.getHours().toString().padStart(2, '0');
            let minutes = date.getMinutes().toString().padStart(2, '0');

            var formattedDate = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes;
            match.matchDate = formattedDate;
        })
        })
        .catch((err) => {
        console.log(err)
        })
    }
   
    
    //리턴
    return {matchList,getMatchList,condition,match,getMatch,myMatchList,getMyMatchList,myResMatchList,getResMyMatchList}
})