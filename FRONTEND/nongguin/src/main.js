
import { createApp } from 'vue'
import { createPinia } from 'pinia'

import router from './router'

// v-calendar lib
import VCalendar from 'v-calendar';
import 'v-calendar/style.css';


const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(VCalendar,{})
import { useUserStore } from "@/stores/userStore";

import App from './App.vue'
const userStore = useUserStore();

if(sessionStorage.getItem("access-token") ){
    
    const userId= JSON.parse(
        atob(sessionStorage.getItem("access-token").split(".")[1])
      ).userId
    userStore.loginUserId=userId
    userStore.getUser(userId)
}
app.mount('#app')
