<template>
    <div class="hold">
        <div class="header">
            <div class="container">
                <div id="logo">
                    <h2><a href="/">🔅다다익선</a></h2>
                </div>
                <ul class="nav">
                    <nav>
                        <li>
                            <!--                            <router-link-->
                            <!--                                    v-for="routes in toolbar"-->
                            <!--                                    v-bind:key="routes.id"-->
                            <!--                                    :to="`${routes.page}`">{{routes.text}}-->
                            <!--                            </router-link>-->




                            <button class="btn" v-if="isLogged==true">🎉 환영합니다! </button>
                            <router-link to="/Enroll">등록</router-link>
<!--                            <router-link to="/list">게시판</router-link>-->
                            <router-link to="/page" v-if="isLogged == true">마이페이지</router-link>
                            <router-link to="/login" v-else = "isLogged==false">로그인</router-link>
<!--                            <router-link to="/login" v-else-if = "isLogged==false">로그아웃</router-link>-->
                            <button class="btn" @click="logout" v-if="isLogged== true">로그아웃</button>
                        </li>
                        <!--          <li v-for="item in toolbar" :key="item.index"> <a href="#">{{item}}</a></li>-->
                    </nav>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>

    import axios from 'axios';

    import IndexWaitingList from "./IndexWaitingList";

    const storage = window.sessionStorage;

    export default {
        name: 'IndexMenuBar',
        components: {IndexWaitingList},
        data() {
            return {
                // sitename: "🔅다다익선",

                isLogged: this.checkIfIsLogged(),

                show: true,

            }
        },
        created() {
            this.$bus.$on("logged", () => {
                this.isLogged = this.checkIfIsLogged();
            })
        },
        methods: {
            logout() {
                console.log("vue : logout");

                if (storage.getItem("member")) { //if exist login info
                    storage.removeItem("member");
                    this.isLogged = this.checkIfIsLogged();
                    console.log("로그아웃 성공");
                }
            },
            checkIfIsLogged () {
                if (storage.getItem("member")) {
                    return true
                } else {
                    return false
                }
            },
            init() {
                if (storage.getItem("member")) {
                    console.log("member : " + storage.getItem("member"));
                    this.isLogged = this.checkIfIsLogged();
                    console.log(status);
                } else {
                    this.isLogged = this.checkIfIsLogged();
                    console.log(status);
                }
            }, mounted() {
                this.init();
                this.isLogged = this.checkIfIsLogged();
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
        background: #fce505;
    }

    .section.bg {
        background: #fce505;

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
        margin: auto 0;
        padding: 0px;
        position: relative;
        z-index: 1;
    }

    .webdesigntuts-workshop input {
        background: rgb(89, 90, 26);
        background: linear-gradient(white, white);
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
            box-shadow: 0 0 5px rgba(0, 255, 0, .2), inset 0 0 5px rgba(0, 255, 0, .1), 0 2px 0 #000;
        }
        100% {
            border-color: #6f6;
            box-shadow: 0 0 20px rgba(0, 255, 0, .6), inset 0 0 10px rgba(0, 255, 0, .4), 0 2px 0 #000;
        }
    }

</style>



