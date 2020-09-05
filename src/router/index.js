import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/HelloWorld'
import Login from '../components/Login.vue'
import Main from "@/components/Main";
import Board from "@/components/IndexWaitingList.vue"

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
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: Login,
    template: Login
 }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
