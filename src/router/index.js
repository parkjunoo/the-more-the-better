import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/HelloWorld'
import Login from '../components/Login.vue'
import Main from "@/components/Main";
import Board from "@/components/IndexWaitingList.vue"
import Register from '@/components/Member'

Vue.use(VueRouter)



  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Main,
    template: Main
  }, {
      path:'/board',
      name:'Main',
      component: Board,
      template:Board

    }, {
    path: '/login',
    name: 'Login',
    component: Login,
    template: Login
 }, {
          path: '/reg',
          name: 'Register',
          component: Register,
          template: Register
      }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
