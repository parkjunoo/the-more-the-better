import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)
Vue.use(Vuetify);
Vue.config.productionTip = false

Vue.component( "store-rank-com", {
  template :
      `<div class="col four">
      <h1 class="icon">{{store}}</h1>
      <h1 class="service">{{rank}}</h1>
      <p>누적 : {{count}}</p>
  </div>`,
  props:['store', 'count' ,'rank']
})

Vue.component( "store-event-com", {
  template :
      `<div class="col three">
      <h1 class="icon side">{{index}}</h1>
      <h1 class="feature side">{{storeName}}</h1>
      <p class="side">{{content}}</p>
    </div>`,
  props:['index', 'storeName' ,'content']
})

new Vue({
  vuetify: new Vuetify(),
  data: {
    sitename: "🔅다다익선",
    toolbar: ["둘러보기", "로그인", "회원가입", "고객센터"],
    stores:[
      {
        storeImg: "https://dtd31o1ybbmk8.cloudfront.net/photos/ba1b1c1b8c7f1c3475980282a46e4fa5/thumb.jpg",
        storeName: "스타벅스 남부터미널점",
        curPeople: "12시 30분 4/5명"
      },
      {
        storeImg: "https://ediya.com/C/images/ediyarab/interior_slide01_2.jpg",
        storeName: "이디야 남부터미널점",
        curPeople: "12시 50분 4/5명"
      },
      {
        storeImg: "https://lh3.googleusercontent.com/proxy/BiuP7UQuQ4iFEtZLeOU04wPDny2tkw5Mrs2kgClNIYiVvx1NX_WaN_jSoa3arYE8EMESj9IVIDjR2ozzhNI0kt2Pw2tIrGsLy6qtZq36InPDEVeAxI1ptizU9azM_WzsA2GIaB59gEk5ya8rC98mNN1p1B8NbzsfiiisHqL5m9CfjehuUMyV3B8A8t2V68FZ8C7QvtBI3ymCql4yLs_GUb1Yo4d3zprjGZ8jjGNH-H9-fPOB3gsKLM-lrOUwSYlzG9UeydvHfkcyHAtYHY8qV-NYSgGajh5zszWDpsuthTAvaU3polCM8Q8iGabK",
        storeName: "바나프레소 남부터미널점",
        curPeople: "12시 30분 4/5명"
      },
      {
        storeImg: "https://selecto.co.kr/images/foundation/2v0011.jpg",
        storeName: "셀렉토 커피",
        curPeople: "12시 30분 4/5명"
      },
      {
        storeImg: "https://t1.daumcdn.net/cfile/blog/022DE64A50B6BFB41D",
        storeName: "요거프레소 남부터미널점",
        curPeople: "12시 30분 4/5명"
      },
      {
        storeImg: "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTvl9Y1FiBVE7ZZ82OXYZ_j4A8V5qY0573Zxw&usqp=CAU",
        storeName: "투썸플레이스 남부터미널점",
        curPeople: "12시 30분 4/5명"
      },
    ],
    ranking:[
      {
        rank : 1,
        store : "스벅",
        count : 200 + "잔",
      },
      {
        rank : 2,
        store : "이댜",
        count : 199 + "잔",
      },
      {
        rank : 3,
        store : "바나",
        count : 198 + "잔",
      },
      {
        rank : 4,
        store : "메가",
        count : 197 + "잔",
      }
    ],
    events:[
      {
        index : 1,
        storeName : "스벅 Event",
        content : "2+1 크로아상"
      },
      {
        index : 2,
        storeName : "이댜 Event",
        content : "2+1 바닐라라떼"
      },
      {
        index : 3,
        storeName : "바나 Event",
        content : "2+1 아아"
      },
      {
        index : 4,
        storeName : "셀렉 Event",
        content : "2+1 마들렌"
      },
      {
        index : 5,
        storeName : "라리 Event",
        content : "2+1 허니브레드"
      },
      {
        index : 6,
        storeName : "투썸 Event",
        content : "2+1 초코프라프치노"
      }
    ]
  },
  router,
  store,
  render: h => h(App)
}).$mount('#app')
;
