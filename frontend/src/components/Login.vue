<template>
    <div class="register">
        <div class="register-triangle"></div>
        <img src="../assets/login.png" width=100%>
        <h2 style="align-content: center">다다익선(多多益善) 회원이신가요?<br><br></h2>



        <p>ID : <input type="eid" name="eid" id="eid" v-model="mem_id" ></p>
        <p>PW : <input type="password" name="login" id="pass" v-model="mem_pw" > </p>

        <p><input type="button" @click="login" value="로그인" id="login"></p>

        <button v-on:click="register" class = "btn-r">아직 회원이 아니신가요?</button>

        <br><hr><br>

    </div>
</template>

<script>

    import axios from 'axios';
    const storage = window.sessionStorage;
    export default {
        name: 'HelloWorld',
        props: {
            msg: String
        },
        data: function() {
            return {
                mem_id : "",
                mem_pw : "",
                status: "",
                token: "",
                info: "",
                detailInfo: "",
                result: false
            }
        },

        methods:{
            setInfo(status, token, info){
                this.status = status;
                this.token = token;
                this.info = info;
                this.result = true;
            },
            setDetailInfo(status, token, info, detailInfo){
                this.status = status;
                this.token = token;
                this.info = info;
                this.detailInfo = detailInfo;
            },
            register(){
                this.$router.push({ name: 'Register' })

            },
            login() {

                console.log("vue : start login");

                axios.post("/members/login", {

                    mem_id: this.mem_id,
                    mem_pw: this.mem_pw

                }).then(res => {

                    if(res.data.status) {
                        console.log("status : true");

                        storage.setItem("member", res.data.member.no);
                        this.$bus.$emit('logged', 'User logged')
                        this.$router.push({ name: 'Home' });
                    }

                }).catch(e => {

                    console.log("log in fail");
                    //controller에서 넘어온 에러 문구 출력
                    alert(JSON.stringify(e.response.data.message));

                });
            },
            init() {
                console.log("data initialize");

                this.mem_id = "",
                    this.mem_pw = ""
            }
        }, mounted() {
            this.init();
        }
    }
    //logout(){
    // storage.setItem("jwt-auth-token", "");
    // storage.setItem("login_eid", "");

    // this.mem_id = "";
    // this.mem_pw = "";
    //
    // this.message = "로그인해 주세요";
    // this.result = false;
    //
    // this.setDetailInfo("로그아웃 성공", "", "");

    //},
    //getInfo() {
    //         axios.post("/api/info", {
    //                 mem_id : this.mem_id,
    //                 mem_pw : this.mem_pw
    //             },
    //             {
    //                 headers : {
    //                     // "jwt-auth-token" : storage.getItem("jwt-auth-token")
    //                 }
    //             }
    //         ).then(res => {
    //             // this.setDetailInfo( "정보 조회 성공", storage.getItem("jwt-auth-token"), this.info, JSON.stringify(res.data));
    //         }).catch(e => {
    //             this.setDetailInfo("정보 조회 실패", "", e.response.data.msg);
    //         });
    //     },
    //     login(){
    //         // storage.setItem("jwt-auth-token", "");
    //         // storage.setItem("login_eid", "");
    //
    //         axios.post("/api/logincheck/signin", {
    //             mem_id : this.mem_id,
    //             mem_pw : this.mem_pw
    //         }).then(res =>{
    //             if(res.data.status){
    //
    //                 this.mem_id = "";
    //                 this.mem_pw = "";
    //                 this.message = res.data.data.mem_id + "로 로그인 되었습니다";
    //
    //                 console.dir(res.headers["jwt-auth-token"]);
    //                 console.log("//////////");
    //
    //                 //화면에 정보 출력
    //                 this.setInfo("로그인 성공", res.headers["jwt-auth-token"], JSON.stringify(res.data.data));
    //
    //                 //토큰 & mem_id 정보 저장
    //                 // storage.setItem("jwt-auth-token", res.headers["jwt-auth-token"]);
    //                 // storage.setItem("login_eid", res.data.data.mem_id);
    //
    //             }else{
    //
    //                 this.setInfo("", "", "");
    //                 this.message = "로그인 하세요";
    //                 alert("입력 정보를 확인");
    //
    //             }
    //
    //         }).catch(e => {
    //             this.setInfo("실패", "", JSON.stringify(e.response || e.message));
    //         });
    //     },
    //     init(){
    //         // if(storage.getItem("jwt-auth-token")){
    //         //     this.message = storage.getItem("login_eid") + "로 로그인 되었습니다";
    //         // }else{
    //         //     storage.setItem("jwt-auth-token", "");
    //         // }
    //     }//init()
    // }, mounted(){
    //     this.init();
    // }
    //    }
    //    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    @import url("https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css");
    @import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");


    body {
        font-family: 'Do Hyeon', sans-serif;
    @import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
        font-size:40px;
        font-color:white;


    }

    button,
    .register {
        width: 400px;
        margin: auto;
        font-size: 10px;
        font-family: 'Do Hyeon', sans-serif;
    }

    /* Reset top and bottom margins from certain elements */
    .login-header,

    button,
    .register p {
        color: white;
        font-family: 'Do Hyeon', sans-serif;
    }

    /* The triangle form is achieved by a CSS hack */
    .register-triangle {
        width: 0;
        margin-right: auto;
        margin-left: auto;
        align-content: center;
        border: 12px solid white;
        border-bottom-color: rgb(146, 139, 137);
    }

    .login-header {
        background: rgb(146, 139, 137);
        padding: 20px;
        font-size: 1.4em;
        font-weight: normal;
        text-align: center;
        text-transform: uppercase;
        color: #fff;
    }

    button,
    .register-container {
        background: grey;
        align-content: center;
        padding: 12px;
    }

    /* Every row inside .login-container is defined with p tags */
    button,
    .register p {
        padding: 12px;
        font-family: 'Do Hyeon', sans-serif;
    }

    button,
    .register input {
        box-sizing: border-box;
        display: block;
        width: 100%;
        border-width: 1px;
        border-style: solid;
        padding: 16px;
        outline: 0;
        font-family: inherit;
        font-size: 0.95em;
        align-content: center;
        background: #fff;
    }

    .register input[type="mem_id"],
    .register input[type="password"] {
        background: #fff;
        border-color: #bbb;
        color: #555;
    }

    /* Text fields' focus effect */
    .register input[type="mem_id"]:focus,
    .register input[type="password"]:focus {
        border-color: #888;
    }

    button,
    .register input[type="submit"] {
        background: rgb(146, 139, 137);
        border-color: white;
        color: #fff;
        cursor: pointer;
    }

    .register input[type="submit"]:hover {
        background: rgb(226, 226, 162);
    }

    .register input[type="submit"]:focus {
        border-color: rgb(226, 226, 162);
    }

    /*button{*/
    /*    width: 100%;*/
    /*    align-self: center;*/
    /*}*/

    /*.login input[type="submit"]{*/
    /*    width : 105%;*/
    /*    align-self: center;*/
    /*}*/

    .btn-r {
        background-color: tan;
        border: none;
        color: #ffffff;
        cursor: pointer;
        display: inline-block;
        font-family: 'Do Hyeon', sans-serif;
        font-size:40px;
        font-size: 1em;
        font-size: 22px;
        line-height: 1em;
        outline: none;
        padding: 12px 40px 10px;
        position: relative;
        text-transform: uppercase;
        /*font-weight: 700;*/

    }
    .btn-r:before,
    .btn-r:after {
        border-color: transparent;
        -webkit-transition: all 0.25s;
        transition: all 0.25s;
        border-style: solid;
        border-width: 0;
        content: "";
        height: 24px;
        position: absolute;
        width: 24px;
    }
    .btn-r:before {
        border-color: #c47135;
        border-right-width: 2px;
        border-top-width: 2px;
        right: -5px;
        top: -5px;
    }
    .btn-r:after {
        border-bottom-width: 2px;
        border-color: #c47135;
        border-left-width: 2px;
        bottom: -5px;
        left: -5px;
    }
    .btn-r:hover,
    .btn-r.hover {
        background-color: #c47135;
    }
    .btn-r:hover:before,
    .btn-r.hover:before,
    .btn-r:hover:after,
    .btn-r.hover:after {
        height: 100%;
        width: 100%;
    }
</style>
