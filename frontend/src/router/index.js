import Vue from 'vue';
import VueRouter from 'vue-router';
import Login from '../components/Login.vue';
import Main from "@/components/Main";
import Board from "@/components/IndexWaitingList.vue";
import Enroll from "@/views/Enroll.vue";
import Register from "@/components/Register";
import MyPage from "@/components/MyPage";

Vue.use(VueRouter)
const routes = [
    {
        path: '/',
        name: 'Home',
        component: Main,
    },
    {
        path: '/board',
        name: 'Main',
        component: Board,
    },
    {
        path: '/login',
        name: 'Login',
        component: Login,
    },
    {
        path: '/enroll',
        name: 'Enroll',
        component: Enroll
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },
    {
        path: '/mypage',
        name: 'MyPage',
        component: MyPage
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
