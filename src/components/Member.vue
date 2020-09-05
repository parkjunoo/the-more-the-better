<template>
    <div class="page-wrapper bg-red p-t-180 p-b-100 font-robo">
        <div class="wrapper wrapper--w960">
            <div class="card card-2">
                <div class="card-heading"></div>
                <div class="card-body">
                    <h2 class="title">서비스 신규 회원가입</h2>
                    <form action="/signUp" method="POST">
                        <div class="input-group">
                            <input class="input--style-2" type="text" placeholder="아이디" name="id">
                        </div>
                        <div class="input-group">
                            <input class="input--style-2" type="text" placeholder="이름" name="name">
                        </div>
                        <div class="input-group">
                            <input class="input--style-2" type="password" placeholder="비밀번호" name="password">
                        </div>
                        <div class="input-group">
                            <input class="input--style-2" type="password" placeholder="비밀번호 확인" name="password_check">
                        </div>
                        <div class="input-group">
                            <input class="input--style-2" type="text" placeholder="휴대폰 번호 입력" name="phone_number">
                        </div>
                        <div class="row row-space ">
                            <div class="col-2 ">
                                <div class="input-group ">
                                    <div class="rs-select2 js-select-simple select--no-search ">
                                        <select name="gender ">
                                            <option disabled="disabled" selected="selected">성별</option>
                                            <option>남자</option>
                                            <option>여자</option>
                                        </select>
                                        <div class="select-dropdown"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <div class="rs-select2 js-select-simple select--no-search">
                                <select name="class ">
                                    <option disabled="disabled " selected="selected ">수강중인 수업 선택</option>
                                    <option>인공지능 P반</option>
                                    <option>인공지능 A반</option>
                                    <option>데이터과학 영등포</option>
                                    <option>데이터과학 서초</option>
                                </select>
                                <div class="select-dropdown "></div>
                            </div>
                        </div>
                        <div class="row row-space ">
                            <di`v class="col-2 ">
                            <div class="input-group ">
                                <input class="input--style-2 " type="file" placeholder="Registration Code " name="res_code ">
                            </div>
                        </div>
                </div>
                </form>
                <div class="p-t-30 ">
                    <button class="btn btn--radius btn--green" type="submit" id="btn_memberJoin">회원가입</button>
                </div>
            </div>
        </div>
    </div>
    </div>
</template>

<script>
    // const axios = require('axios').default;

    export default {
        name: 'HelloWorld',
        props: {
            msg: String
        },
        data: function() {
            return {
                eid : "",
                pw : "",
                message: " 다다익선(多多益善) 회원이신가요? ",
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
            logout(){
                // storage.setItem("jwt-auth-token", "");
                // storage.setItem("login_eid", "");

                // this.eid = "";
                // this.pw = "";
                //
                // this.message = "로그인해 주세요";
                // this.result = false;
                //
                // this.setDetailInfo("로그아웃 성공", "", "");

            },
            getInfo() {
                //         axios.post("/api/info", {
                //                 eid : this.eid,
                //                 pw : this.pw
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
            },
            login(){
                //         // storage.setItem("jwt-auth-token", "");
                //         // storage.setItem("login_eid", "");
                //
                //         axios.post("/api/logincheck/signin", {
                //             eid : this.eid,
                //             pw : this.pw
                //         }).then(res =>{
                //             if(res.data.status){
                //
                //                 this.eid = "";
                //                 this.pw = "";
                //                 this.message = res.data.data.eid + "로 로그인 되었습니다";
                //
                //                 console.dir(res.headers["jwt-auth-token"]);
                //                 console.log("//////////");
                //
                //                 //화면에 정보 출력
                //                 this.setInfo("로그인 성공", res.headers["jwt-auth-token"], JSON.stringify(res.data.data));
                //
                //                 //토큰 & eid 정보 저장
                //                 // storage.setItem("jwt-auth-token", res.headers["jwt-auth-token"]);
                //                 // storage.setItem("login_eid", res.data.data.eid);
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
            },
            init(){
                //         // if(storage.getItem("jwt-auth-token")){
                //         //     this.message = storage.getItem("login_eid") + "로 로그인 되었습니다";
                //         // }else{
                //         //     storage.setItem("jwt-auth-token", "");
                //         // }
                //     }//init()
                // }, mounted(){
                //     this.init();
            }
        }
    }
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
    .login {
        width: 400px;
        margin: 16px auto;
        font-size: 16px;
        font-family: 'Do Hyeon', sans-serif;
    }

    /* Reset top and bottom margins from certain elements */
    .login-header,

    button,
    .login p {
        color: white;
        font-family: 'Do Hyeon', sans-serif;
    }

    /* The triangle form is achieved by a CSS hack */
    .login-triangle {
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
    .login-container {
        background: grey;
        align-content: center;
        padding: 12px;
    }

    /* Every row inside .login-container is defined with p tags */
    button,
    .login p {
        padding: 12px;
        font-family: 'Do Hyeon', sans-serif;
    }

    button,
    .login input {
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

    .login input[type="eid"],
    .login input[type="password"] {
        background: #fff;
        border-color: #bbb;
        color: #555;
    }

    /* Text fields' focus effect */
    .login input[type="eid"]:focus,
    .login input[type="password"]:focus {
        border-color: #888;
    }

    button,
    .login input[type="submit"] {
        background: rgb(146, 139, 137);
        border-color: white;
        color: #fff;
        cursor: pointer;
    }

    .login input[type="submit"]:hover {
        background: rgb(226, 226, 162);
    }

    .login input[type="submit"]:focus {
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
</style>
