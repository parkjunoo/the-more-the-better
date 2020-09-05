import Vue from 'vue';
import VueRouter from 'vue-router';
import Login from '../components/Login.vue';
import Main from "@/components/Main";
import Board from "@/components/IndexWaitingList.vue";
import Enroll from "@/views/Enroll.vue";

Vue.use(VueRouter)
  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Main,
  }, 
  {
    path:'/board',
    name:'Main',
    component: Board,
  },
     {
    path: '/login',
    name: 'Login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: Login,
 },
 {
   path: '/enroll',
   name: 'Enroll',
   component: Enroll
 }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
