<template>
    <div class="section bg">
        <div class="container">
            <h1>전체 리스트 보기</h1>
            <h2>배달료 줄이고 행복을 더하세요! 多多益善</h2>
            <div v-for="(store) in stores" :key="store.index">
                <div  class="col three bg nopad pointer" @click="openModal" >
                    <div class="imgholder">
                        <img :src = "store.storeImg" style=" height: 100%;
          width : 100%;
          object-fit: contain;"
                        />
                    </div>
                    <h1 class="feature">{{store.storeName}}</h1>
                    <p>{{store.curPeople}}</p>
                </div>
            </div>
            <div class="group"></div>
        </div>
        <ServiceModal @close="closeModal" v-if="modal">
            <!-- default 슬롯 콘텐츠 -->
            <div><input v-model="message"></div>
            <!-- /default -->
            <!-- footer 슬롯 콘텐츠 -->
            <template slot="footer">
                <button @click="doSend">제출</button>
            </template>
            <!-- /footer -->
        </ServiceModal>
    </div>

</template>

<script>
    import ServiceModal from './servicecoms/ServiceModal'
    export default {
        name: 'IndexWaitingList',
        components: {ServiceModal},
        data(){
            return{
                modal: false,
                message: '',
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
                ]
            }
        },
        mounted:{


        },
        methods:{
            openModal() {
                this.modal = true
            },
            closeModal() {
                this.modal = false
            },
            doSend() {
                if (this.message.length > 0) {
                    alert(this.message)
                    this.message = ''
                    this.closeModal()
                } else {
                    alert('메시지를 입력해주세요.')
                }
            },
            init(){
                axios.get('/order/search' + this.search, {
                    search: this.search
                }).then(res =>{
                    console.log(res);
                })
            }
        }
    }
</script>

<style scoped>
    /*
      terrible.css
      Andrew Tunecliffe, 2015
      http://atunnecliffe.com
    */
    /* uncomment to see outlines of stuff when you hover *-/
    :hover {
      background:rgba(0, 0, 0, 0.1);
    }/**/
    @import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
    @import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');

    body{
        background-color:#f2f2f2;
    }
    h2{
        font-family: 'Do Hyeon', sans-serif;
    }
    .carousel-inner>.carousel-item>img {  width: 320px; height: 360px;

    }


    html,
    body {
        margin: 0;
        padding: 0;
        font-family: 'Do Hyeon', sans-serif;
        background-color:#f2f2f2;
    }

    .section {
        width: 100%;
    }

    .container {
        position: relative;
        width: 1170px;
        margin: 0 auto;
        color: #444;
        font-size: 14px;
        font-weight: 300;
        font-family: 'Do Hyeon', sans-serif;
    @import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
        overflow: hidden;
    }

    .section .container {
        padding: 30px 0 50px 0;
    }

    .section.bg {
        background: #f7f7f7;
    }
    /*
      Header
    */

    .hold {
        height: 80px;
    }

    .header {
        line-height: 80px;
        width: 100%;
        transition: line-height 0.2s linear, box-shadow 0.2s linear;
        position: fixed;
        top: 0;
        left: 0;
        z-index: 100;
        background: rgba(245, 245, 245, 0.97);
    }

    .header.small {
        line-height: 50px;
        box-shadow: 0px 1px 3px 0px rgba(50, 50, 50, 0.8);
    }

    .header.small > .container > #logo {
        height: 40px;
    }

    #logo {
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        float: left;
        height: 40px;
        width: 170px;
        margin-left: 5px;
    }

    ul.nav {
        float: right;
        list-style: none;
        margin: 0;
        padding: 0;
    }

    ul.nav li {
        float: left;
        position: relative;
    }

    ul.nav li a {
        transition: color 0.2s linear;
        font-size: 18px;
    }

    ul.nav li:hover a {
        color: red;
    }

    ul.nav li a {
        padding: 21px;
        color: initial;
        text-decoration: initial;
    }
    /*
      Slider
    */

    .section .slider,
    .section .footer {
        background: #333;
    }

    .slidercontent {
        text-align: center;
    }

    .hero {
        font-family: 'Roboto Slab', sans-serif;
        color: white;
        font-weight: normal;
        letter-spacing: 1px;
    }

    h1.hero {
        font-size: 54px;
    }

    h2.hero {
        font-size: 30px;
        margin-bottom: 60px;
    }

    h1.hero:after {
        content: "";
        width: 300px;
        position: relative;
        border-bottom: 1px solid #aaa;
        text-align: center;
        margin: auto;
        margin-top: 15px;
    }

    .call {
        color: white;
        display: block;
        margin-bottom: 20px;
    }

    .call span {
        display: inline;
        border: 1px solid white;
        padding: 8px 13px;
        font-size: 20px;
        transition: background 0.15s linear;
    }

    .call span:hover {
        background: rgba(255, 255, 255, 0.1);
        cursor: pointer;
    }
    /*
      Columns
    */

    .col {
        float: left;
        padding: 0;
        margin: 0;
        position: relative;
    }

    .col.four {
        width: 23%;
        margin: 0 1%;
    }

    .col.three {
        width: 31.3%;
        margin: 0 1%;
    }

    .col.two {
        width: 40%;
        margin: 0 2.5%;
        padding: 0 2.5%;
    }

    .col.extrapad {
        padding-top: 20px;
        padding-bottom: 20px;
    }

    .col .service,
    .col .feature {
        font-size: 50px;
        font-weight: 300;
        color: black;
        font-family: 'Do Hyeon', sans-serif;
    }

    .col .service:after {
        content: "";
        width: 50px;
        position: relative;
        border-bottom: 1px solid #eee;
        display: block;
        text-align: center;
        margin: auto;
        margin-top: 15px;
    }

    .col .feature {
        font-size: 19px;
    }

    .col h1.side,
    .col p.side,
    .col span.side:first-of-type {
        margin-left: 50px;
        text-align: left;
    }

    .col .icon {
        border-radius: 50%;
        height: 85px;
        width: 85px;
        line-height: 85px;
        text-align: center;
        margin: 0 auto;
        transition: background 0.25s linear, color 0.25s linear;
    }

    .col .icon.side {
        position: absolute;
        padding: 0;
        margin: 0;
        top: -15px;
        height: 50px;
        width: 50px;
    }

    .col:hover > .icon {
        background: #F44336;
        color: white;
    }

    .col:hover > .icon.side {
        background: initial;
        color: initial;
    }

    .responsivegroup {
        display: none;
    }
    /*
      Column specifics
    */

    .col p,
    .col h1 {
        padding: 0 1%;
        text-align: center;
    }

    .group.margin {
        margin-bottom: 20px;
    }

    .col .imgholder {
        height: 300px;
        width: 100%;
        background: #333;
        transition: background 0.3s linear;
    }

    .col.imgholder.img{
        height: 100%;
        width : 100%;
        object-fit: contain;
    }

    .col.bg {
        background: #FFF;
    }

    .col.pointer {
        cursor: pointer;
    }

    .col.bg:hover .imgholder {
        background: #555;
    }

    .col span.feature {
        font-size: 20px;
    }
    /*
      Text
    */

    .container > h1:not(.hero) {
        margin-bottom: 30px;
        text-align: center;
    }

    .container > h1:after {
        content: "";
        width: 30px;
        position: relative;
        border-bottom: 1px solid #aaa;
        display: block;
        text-align: center;
        margin: auto;
        margin-top: 15px;
    }

    h2 {
        font-family: 'Roboto Slab', sans-serif;
        text-align: center;
        font-weight: 400;
        font-size: 18px;
    }

    .left,
    .left > h1,
    .left > p {
        text-align: left;
    }

    .reset {
        text-align: left !important;
    }

    .reset:after {
        display: none !important;
    }
    /*
      Slider with Content
    */

    .white h1,
    .white h2,
    .white p,
    .white div,
    .white a {
        color: #fff;
    }
    /*
      Responsive
    */

    .group:after {
        content: "";
        display: table;
        clear: both;
    }

    @media all and (max-width: 768px) {
        .container {
            width: 95%;
        }
        .col.four {
            width: 48%;
            margin: 1%;
        }
        .col.three {
            display: block;
            width: 95%;
            padding: 0;
            margin: 0 auto;
            float: none;
        }
        .header {
            height: auto;
            background: #eee;
        }
        #logo {
            position: initial;
            float: none;
            display: block;
            transform: none;
            margin: 10px auto 0 auto;
        }
        ul.nav {
            float: none;
            display: block;
            text-align: center;
            margin: 0 auto;
        }
        ul.nav li {
            float: initial;
            display: inline-block;
        }
        .responsivegroup {
            display: block;
        }
        .responsivegroup:after {
            content: "";
            display: table;
            clear: both;
        }
    }

    @media all and (min-width: 768px) {
        .container {
            width: 750px;
        }
    }

    @media all and (min-width: 992px) {
        .container {
            width: 970px;
        }
    }

    @media all and (min-width: 1200px) {
        .container {
            width: 1170px;
        }
    }

    @media all and (max-width:450px) {
        .col, .col.four, .col.three, .col.two {
            display: block;
            width: 95%;
            padding: 0;
            margin: 0 auto;
            float: none;
        }
        .col.extrapad {
            padding: 1%;
            margin-bottom: 10px;
        }
        .group {
            display: none;
        }
    }

    .webdesigntuts-workshop {
        background: #151515;
        height: 100%;
        position: absolute;
        text-align: center;
        width: 100%;
    }

    .webdesigntuts-workshop:before,
    .webdesigntuts-workshop:after {
        content: '';
        display: block;
        height: 1px;
        left: 50%;
        margin: 0 0 0 -400px;
        position: absolute;
        width: 800px;
    }

    .webdesigntuts-workshop:before {
        background: #444;
        background: linear-gradient(left, #151515, #444, #151515);
        top: 192px;
    }

    .webdesigntuts-workshop:after {
        background: #000;
        background: linear-gradient(left, #151515, #000, #151515);
        top: 191px;
    }

    .webdesigntuts-workshop form {
        background: rgb(229, 232, 241);
        background: linear-gradient(rgb(229, 232, 241), rgb(229, 232, 241));
        border: 1px solid #000;
        border-radius: 30px;
        box-shadow: inset 0 0 0 1px #272727;
        display: inline-block;
        font-size: 0px;
        margin:auto 0;
        padding: 0px;
        position: relative;
        z-index: 1;
    }

    .webdesigntuts-workshop input {
        background: rgb(89, 90, 26);
        background: linear-gradient(white,white);
        border: 1px solid #444;
        border-radius: 5px 0 0 5px;
        box-shadow: 0 2px 0 rgb(229, 232, 241);
        color: #888;
        display: block;
        float: left;
        font-family: 'Cabin', helvetica, arial, sans-serif;
        font-size: 13px;
        font-weight: 400;
        height: 40px;
        margin: 0;
        padding: 0 10px;
        text-shadow: 0 -1px 0 #000;
        width: 1000px;
    }

    .ie .webdesigntuts-workshop input {
        line-height: 40px;
    }

    .webdesigntuts-workshop input::-webkit-input-placeholder {
        color: #888;
    }

    .webdesigntuts-workshop input:-moz-placeholder {
        color: #888;
    }

    .webdesigntuts-workshop input:focus {
        animation: glow 800ms ease-out infinite alternate;
        background: rgb(229, 232, 241);
        background: linear-gradient(rgb(229, 232, 241),rgb(229, 232, 241));
        border-color: #393;
        box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
        color: #efe;
        outline: none;
    }

    .webdesigntuts-workshop input:focus::-webkit-input-placeholder {
        color: #efe;
    }

    .webdesigntuts-workshop input:focus:-moz-placeholder {
        color: #efe;
    }

    .webdesigntuts-workshop button {
        background: rgb(229, 232, 241);
        background: linear-gradient(#333, #222);
        box-sizing: border-box;
        border: 1px solid rgb(229, 232, 241);
        border-left-color: rgb(229, 232, 241);
        border-radius: 0 5px 5px 0;
        box-shadow: 0 2px 0 rgb(229, 232, 241);
        color: black;
        display: block;
        float: left;
        font-family: 'Cabin', helvetica, arial, sans-serif;
        font-size: 13px;
        font-weight: 400;
        height: 40px;
        line-height: 40px;
        margin: 0;
        padding: 0;
        position: relative;
        text-shadow: 0 -1px 0 rgb(229, 232, 241);
        width: 80px;
    }

    .webdesigntuts-workshop button:hover,
    .webdesigntuts-workshop button:focus {
        background: rgb(229, 232, 241);
        background: linear-gradient(rgb(229, 232, 241), rgb(229, 232, 241));
        color: #5f5;
        outline: none;
    }

    .webdesigntuts-workshop button:active {
        background: rgb(229, 232, 241);
        background: linear-gradient(#393939, #292929);
        box-shadow: 0 1px 0 #000, inset 1px 0 1px #222;
        top: 1px;
    }

    @keyframes glow {
        0% {
            border-color: #393;
            box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
        }
        100% {
            border-color: #6f6;
            box-shadow: 0 0 20px rgba(0,255,0,.6), inset 0 0 10px rgba(0,255,0,.4), 0 2px 0 #000;
        }
    }

</style>
