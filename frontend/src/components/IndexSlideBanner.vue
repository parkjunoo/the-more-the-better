<template>
    <div class="section">
        <div class="wrapper">
                    <button class = "btn btn--action" v-on:click="show = !show" >
                📂 전체 목록 보기        </button>
            <transition name="fade">
                <p v-if="show"><IndexWaitingList></IndexWaitingList></p>
            </transition>
        </div>



            <section class="webdesigntuts-workshop">
                <div action="" method="" @keyup="event">
                    <input v-on:input="typing" v-bind:value="search" type="search" placeholder="배달하고 싶으신 지점을 찾아주세요!">
                    <button>Search</button>
                </div>
                <table>
                    <tr id="tr_hover" v-for="item in result" :key="item.index" @click="openModal(item)" @mouseover="mouseOver">
                        <td>{{item.store.name}}</td>
                        <td>{{item.closetime}}</td>
                        <td>{{item.standby}} / {{item.minperson}}</td>
                    </tr>
                </table>
            </section>


        <br><br><br>

        <div id="demo" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <!-- 슬라이드 쇼 -->
                <div class="carousel-item active">
                    <!--가로-->
                    <div class="container">



                        <div class="bird-container bird-container--one">
                            <div class="bird bird--one"></div>
                        </div>

                        <div class="bird-container bird-container--two">
                            <div class="bird bird--two"></div>
                        </div>

                        <div class="bird-container bird-container--three">
                            <div class="bird bird--three"></div>
                        </div>



                    </div>


                </div>

                <div class="carousel-item">
                    <img class="d-block w-100"
                         src="../assets/main.jpg"
                         alt="Second slide">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>배달료를 아끼고 행복을 더하세요!</h5>
                        <p>多多益善</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100"
                         src="../assets/main.jpg"
                         alt="Third slide">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>배달료를 아끼고 행복을 더하세요!</h5>
                        <p>多多益善</p>
                    </div>
                </div>
                <!-- 슬라이드 쇼 -->
                <a class="carousel-control-prev" href="#demo" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <!-- <span>Previous</span> -->
                </a>
                <a class="carousel-control-next" href="#demo" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <!-- <span>Next</span> -->
                </a>
                <!-- / 화살표 버튼 끝 -->
                <!-- 인디케이터 -->
                <ul class="carousel-indicators">
                    <li data-target="#demo" data-slide-to="0" class="active"></li>
                    <!--0번부터시작-->
                    <li data-target="#demo" data-slide-to="1"></li>
                    <li data-target="#demo" data-slide-to="2"></li>
                </ul>
                <!-- 인디케이터 끝 -->
            </div>
        </div>
        <ServiceModal @close="closeModal" v-if="modal">
      <!-- default 슬롯 콘텐츠 -->
      <div class="modalBox" >

        <div class="modalBox1">
  
          <h3 style="text-align: center;">
            📝{{modalData.store.name}}
            </h3>
          
          <img class="fit-picture" style=" width: 100%;height: 300px;" :src="modalData.store.picture">
          
          <hr style="border: inset 1px orange; width: 100%;">
          
          <p> 부가 설명<span class="price"></span></p>
          
          <hr>  
        </div>
          <div class="modalBox2" style="color:black">
          <h4> 📌게시자 : <span class="price"><i class="fa fa-shopping-cart"></i> <b>{{modalData.host.name}}</b></span></h4>
          <hr style="border: inset 1px orange; width: 100%;"> 
          <p> 시작 시간 : <span class="price">{{modalData.createdDate}}</span></p>
          <p> 마감 시간 : <span class="price">{{modalData.closetime}}</span></p>
          <hr style="border: inset 1px orange; width: 100%;">
          <p> 주문 인원<span class="price">{{modalData.standby}}/{{modalData.minperson}}
              <div class="dropdown">
                <button @click="detailMember" class="dropbtn">자세히보기</button>
                <div v-if="listValue" id="myDropdown" class="dropdown-content">
                  <hr>  
                  <p> 대기멤버</p>
                  <p v-for="member in modalData.waitingmems" v-bind:key="member.id"> {{member.name}}</p>
                  <hr>  
                </div>
              </div>
            </span>
          </p>
          <p> 배달 수령 장소 : <span class="price">{{modalData.meetplace}}</span></p>
          <p> 최소 주문 가격 : <span class="price">{{modalData.mincost}}</span></p>
          
          <hr>        
          </div>



        </div> 

        <template slot="footer">
          <button class="dropbtn" style="width: 100px;" @click="doSend">제출</button>
        </template>
        <!-- /footer -->
      </ServiceModal>
    </div>

</template>

<script>
    import IndexWaitingList from "./IndexWaitingList";
    const storage = window.sessionStorage;
    import ServiceModal from './servicecoms/ServiceModal'
    import axios from 'axios'
    export default {
        data(){
            return {
                mem_no: '',
                modal: false,
                modalData:[],
                message: '',
                search : '',
                result : [],
                listValue : false,
                show:"",

            }
        },
        components:{
            ServiceModal,
            IndexWaitingList
        },
        methods:{
            detailMember() {
              this.listValue = !this.listValue 
            },
            mouseOver(){
                this.style = { backgroundColor: 'red' }
            },
            openModal(orders) {
                this.modalData = orders;
                console.log(this.modalData);
                this.modal = true
            },
            closeModal() {
                this.modalData = [];
                this.modal = false;
            },
            doSend(index) {
                console.log(this.modalData.store.no);
                axios.post('/order/setmem/' + this.modalData.store.no + "/" + storage.getItem("member"))
                    .then(res => {
                        if(res.data.status) {
                            console.log("등록성공");
                            alert("주문대기열에 추가되었습니다.");
                            this.closeModal();
                            this.init();
                        }
                    }).catch(e => {
                    console.log("주문 대기열 등록 실패");
                    alert(JSON.stringify(e.response.data.message));
                });
            },
            typing(e) {
                this.search = e.target.value
                if(this.search == ""){
                    this.result = [];
                }
            },
            event:function(){
                console.log("입력 : " + this.search);
                axios.get('/order/search/' + this.search, {
                    search: this.search
                }).then(res =>{
                    console.log(res.data);
                    this.result=res.data;
                })
            },
            init(){
                console.log("시작");
                this.modal = false;
                this.message = '';
                this.orders =  [];
                this.listValue = false;
                
                axios.get('/order/all')
                    .then(res =>{
                        for(var i=0; i<JSON.parse(res.data.orders.length); i++) {

                            this.orders.push({
                                order: res.data.orders[i],
                                store: res.data.orders[i].store,
                            })
                        }
                    })
            }

        }
    }

</script>


<style scoped>

    @import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
    @import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');

p{
    color:black;
}
    table{
  margin : auto;
  border-radius: 5px;
  z-index: 1;
  
}
td{
  bottom: 5px;
  right: 40px;
  background-color: rgb(255, 255, 255);
  width: 233px; height: 40px;
  border-bottom: 1px solid #444444;
  position: relative;
  margin : auto;
  z-index: 1;
}
    .dropbtn {
  background-color: #4CAF50;
  color: white;
  padding: 3px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}
.dropbtn:hover, .dropbtn:focus {
  background-color: #46a049;
}

p{
  color: black;
  font-size: 20px;
  left: 10px;
  font-family: 'Do Hyeon', sans-serif;
}
h2{
  font-family: 'Do Hyeon', sans-serif;
}
.modalBox{
  font-family: 'Do Hyeon', sans-serif;
  overflow: hidden;
  display:inline-block;
  background-color: #ffefc3;
  
}
.modalBox1{
    font-family: 'Do Hyeon', sans-serif;
  margin: 10px;
  float: left;
  background-color: white;
  width: 400px;
  height: 500px;

}
.modalBox2{
    font-family: 'Do Hyeon', sans-serif;
  margin: 10px;
  float: left;
  width: 300px;
  height: 500px;
  background-color: white; 
}

    body{
        background-color: #b38e74;
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
        width: fit-content;
        position: center;
    }

    .container {
        position: center;
        width: fit-content;
        margin: auto;
        margin-bottom: 50px;
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
        background: rgb(179, 142, 116);
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
        background: #fff49a;
        height: 100%;
        position: center;
        text-align: center;
        width: 100%;
        align-content: center;
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
        background: linear-gradient(left, #fff49a, #fff49a, #fff49a);
        top: 192px;
    }

    .webdesigntuts-workshop:after {
        background: #fff49a;
        background: linear-gradient(left, #fff49a, #fff49a, #fff49a);
        top: 191px;
    }

    .webdesigntuts-workshop div {
        border-radius: 30px;
        display: inline-block;
        font-size: 0px;
        margin:auto 0;
        padding: 0px;
        position: relative;
        z-index: 1;
        width: 100%;
    }

    .webdesigntuts-workshop input {
        background: rgb(89, 90, 26);   
        background: linear-gradient(white,white);   
        border: 1px solid #444;
        border-radius: 5px 0 0 5px;
        color: black;
        display: block;
        float: left;
        font-family: 'Cabin', helvetica, arial, sans-serif;
        font-size: 13px;
        font-weight: 400;
        height: 40px;
        margin: 0;
        padding: 0 10px;
        text-shadow: 0 -1px 0 #000;
        width: 700px;
    }

    .ie .webdesigntuts-workshop input {
        line-height: 40px;
    }

    .webdesigntuts-workshop input::-webkit-input-placeholder {
        color: black;
    }

    .webdesigntuts-workshop input:-moz-placeholder {
        color: black;
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
        background: linear-gradient(#333, #e5ef3b);
        box-sizing: border-box;
        border: 1px solid rgb(229, 232, 241);
        border-left-color: rgb(229, 232, 241);
        border-radius: 0 5px 5px 0;
        box-shadow: 0 2px 0 rgb(229, 232, 241);
        color: #e5ef3b;
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
        color: #8e8231;
        outline: none;
    }

    .webdesigntuts-workshop button:active {
        background: rgb(229, 232, 241);
        background: linear-gradient(#393939, #292929);
        box-shadow: 0 1px 0 #fff49a, inset 1px 0 1px #8e8e22;
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

    .button {
        border: none;
        color: white;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
    }

    .button1 {background-color: #4CAF50;} /* Green */
    .button2 {background-color: #008CBA;} /* Blue */

    body {
        background-color: #f2f2f2;
    }

    h2 {
        font-family: 'Do Hyeon', sans-serif;
    }

    .carousel-inner > .carousel-item > img {
        width: 320px;
        height: 360px;

    }


    html,
    body {
        margin: 0;
        padding: 0;
        font-family: 'Do Hyeon', sans-serif;
        background-color: #f2f2f2;
    }

    .section {
        width: 100%;
    }

    a {
        text-decoration: none;
        font-family: 'Do Hyeon', sans-serif;
        text-decoration: none !important;
        font-size: 30px;
        color: black;

    }

    a:hover {
        text-decoration: none !important;
        color: black;
    }

    .container {
        position: relative;
        width: 1170px;
        margin: 0 auto;
        color: white;
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
        color: gray;
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

    .col.imgholder.img {
        height: 100%;
        width: 100%;
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

    @media all and (max-width: 450px) {
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
        background: lightgoldenrodyellow;
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
        margin: auto 0;
        padding: 0px;
        position: relative;
        z-index: 1;
    }

    .webdesigntuts-workshop input {
      background: rgb(89, 90, 26);   
      background: linear-gradient(white,white);   
      border: 1px solid #444;
      border-radius: 5px 0 0 5px;
      color: black;
      display: block;
      float: left;
      font-family: 'Cabin', helvetica, arial, sans-serif;
      font-size: 13px;
      font-weight: 400;
      height: 40px;
      margin: 0;
      padding: 0 10px;
      text-shadow: 0 -1px 0 #000;
      width: 700px;
      margin-left: 330px;
    }

    .ie .webdesigntuts-workshop input {
        line-height: 40px;
    }

    .webdesigntuts-workshop input::-webkit-input-placeholder {
        color: black;
    }

    .webdesigntuts-workshop input:-moz-placeholder {
        color: black;
    }

    .webdesigntuts-workshop input:focus {
        animation: glow 800ms ease-out infinite alternate;
        background: rgb(229, 232, 241);
        background: linear-gradient(rgb(229, 232, 241), rgb(229, 232, 241));
        border-color: #393;
        box-shadow: 0 0 5px rgba(0, 255, 0, .2), inset 0 0 5px rgba(0, 255, 0, .1), 0 2px 0 #000;
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
        background: #87d489;
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
            box-shadow: 0 0 5px rgba(0, 255, 0, .2), inset 0 0 5px rgba(0, 255, 0, .1), 0 2px 0 #000;
        }
        100% {
            border-color: #6f6;
            box-shadow: 0 0 20px rgba(0, 255, 0, .6), inset 0 0 10px rgba(0, 255, 0, .4), 0 2px 0 #000;
        }
    }


    *,
    :after,
    :before {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }

    section {
        height: 100vh;
        width: 100%;
        display: table;

    }
    section.ok{
        background-color: #555;
    }

    p{
        color: black;
        font-family: arial;
        display: table-cell;
        text-align: center;
        vertical-align: middle;
    }

    .scroll-down {
        opacity: 1;
        -webkit-transition: all .5s ease-in 3s;
        transition: all .5s ease-in 3s;
    }

    .scroll-down {
        position: absolute;
        bottom: 30px;
        left: 50%;
        margin-left: -16px;
        display: block;
        width: 32px;
        height: 32px;
        border: 2px solid #FFF;
        background-size: 14px auto;
        border-radius: 50%;
        z-index: 2;
        -webkit-animation: bounce 2s infinite 2s;
        animation: bounce 2s infinite 2s;
        -webkit-transition: all .2s ease-in;
        transition: all .2s ease-in;
        transform: scale(1)
    }

    .scroll-down:before {
        position: absolute;
        top: calc(50% - 8px);
        left: calc(50% - 6px);
        transform: rotate(-45deg);
        display: block;
        width: 12px;
        height: 12px;
        content: "";
        border: 2px solid white;
        border-width: 0px 0 2px 2px;
    }

    @keyframes bounce {
        0%,
        100%,
        20%,
        50%,
        80% {
            -webkit-transform: translateY(0);
            -ms-transform: translateY(0);
            transform: translateY(0);
        }
        40% {
            -webkit-transform: translateY(-10px);
            -ms-transform: translateY(-10px);
            transform: translateY(-10px);
        }
        60% {
            -webkit-transform: translateY(-5px);
            -ms-transform: translateY(-5px);
            transform: translateY(-5px);
        }
    }


    .button {
        background-color: #d2d7d2; /* Green */
        width : 100%;
        border: none;
        color: white;
        padding: 3px 10px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
    }

    *,
    *::before,
    *::after {
        box-sizing: border-box;
    }

    h1 {
        font-family: 'Arima Madurai', cursive;
        color: black;
        font-size: 4rem;
        letter-spacing: -3px;
        text-shadow: 0px 1px 1px rgba(255,255,255,0.6);
        position: relative;
        z-index: 3;
    }

    .container {
        position: relative;
        overflow: hidden;
        display: flex;
        align-items: center;
        justify-content: center;
        min-height: 100vh;
        min-height: 35rem;
        background-image: linear-gradient(to bottom,  rgba(255,168,76,0.6) 0%,rgba(255,123,13,0.6) 100%), url('../assets/main.jpg');
        background-blend-mode: soft-light;
        background-size: cover;
        background-position: center center;
        padding: 2rem;
    }

    .bird {
        background-image: url('../assets/main.jpg');
        background-size: auto 100%;
        width: 88px;
        height: 125px;
        will-change: background-position;

        animation-name: fly-cycle;
        animation-timing-function: steps(10);
        animation-iteration-count: infinite;

    &--one {
         animation-duration: 1s;
         animation-delay: -0.5s;
     }

    &--two {
         animation-duration: 0.9s;
         animation-delay: -0.75s;
     }

    &--three {
         animation-duration: 1.25s;
         animation-delay: -0.25s;
     }

    &--four {
         animation-duration: 1.1s;
         animation-delay: -0.5s;
     }

    }

    .bird-container {
        position: absolute;
        top: 20%;
        left: -10%;
        transform: scale(0) translateX(-10vw);
        will-change: transform;

        animation-name: fly-right-one;
        animation-timing-function: linear;
        animation-iteration-count: infinite;

    &--one {
         animation-duration: 15s;
         animation-delay: 0;
     }

    &--two {
         animation-duration: 16s;
         animation-delay: 1s;
     }

    &--three {
         animation-duration: 14.6s;
         animation-delay: 9.5s;
     }

    &--four {
         animation-duration: 16s;
         animation-delay: 10.25s;
     }

    }

    @keyframes fly-cycle {

        100% {
            background-position: -900px 0;
        }

    }

    @keyframes fly-right-one {

        0% {
            transform: scale(0.3) translateX(-10vw);
        }

        10% {
            transform: translateY(2vh) translateX(10vw) scale(0.4);
        }

        20% {
            transform: translateY(0vh) translateX(30vw) scale(0.5);
        }

        30% {
            transform: translateY(4vh) translateX(50vw) scale(0.6);
        }

        40% {
            transform: translateY(2vh) translateX(70vw) scale(0.6);
        }

        50% {
            transform: translateY(0vh) translateX(90vw) scale(0.6);
        }

        60% {
            transform: translateY(0vh) translateX(110vw) scale(0.6);
        }

        100% {
            transform: translateY(0vh) translateX(110vw) scale(0.6);
        }

    }

    @keyframes fly-right-two {

        0% {
            transform: translateY(-2vh) translateX(-10vw) scale(0.5);
        }

        10% {
            transform: translateY(0vh) translateX(10vw) scale(0.4);
        }

        20% {
            transform: translateY(-4vh) translateX(30vw) scale(0.6);
        }

        30% {
            transform: translateY(1vh) translateX(50vw) scale(0.45);
        }

        40% {
            transform: translateY(-2.5vh) translateX(70vw) scale(0.5);
        }

        50% {
            transform: translateY(0vh) translateX(90vw) scale(0.45);
        }

        51% {
            transform: translateY(0vh) translateX(110vw) scale(0.45);
        }

        100% {
            transform: translateY(0vh) translateX(110vw) scale(0.45);
        }

    }

    .btn {
        padding: 15px;
        font-weight: 700;
        font-size: auto;
        font-family: 'Do Hyeon', sans-serif;
        text-decoration: none;
        text-align: center;
        align-content: center;
        transition: all .5s ease;
    }
    .btn--action{

        width: 100%;
        padding: 0.05rem;
        border-radius: 1.5rem;
        background-color: #f6e58d;
        color: #000;
        transition: all .5s ease 0s;
        box-shadow: 0 10px #f9ca24;
        margin-bottom: auto;
        align-content: center;
    }

    /* .btn--action:hover{
    box-shadow: inset 0 0 0 .25rem #ffbe76;
    } */

    .btn--action span{
        display: inline-block;
        position: relative;
        transition: all .5s ease;
    }

    .btn--action span:before{
        content: '\f04c';
        color: #000;
        font-size: 10px;
        font-family: 'Do Hyeon', sans-serif;
        display: inline-block;
        position: absolute;
        top: 10px;
        left: 1.5rem;
        opacity: 0;
        transition: all .1s ease 0s;
    }

    .btn--action:hover span{
        padding-left: 1.5rem;
    }

    .btn--action:hover span:before{
        left: 0;
        opacity: 1;
    }

    .btn--action:active {
        box-shadow: 0 5px #f0932b;
        transform: translateY(5px);
    }

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

</style>


