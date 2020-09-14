<template>

    <div class="page-wrapper bg-red p-t-180 p-b-100 font-robo">
        <title>다다익선 - 신규 회원가입</title>
        <div class="wrapper wrapper--w960">
            <div class="card card-2">
                <div class="card-heading"></div>
                <div class="card-body">
                    <h2 class="title" align="center">다다익선 간편 회원가입</h2>

                    <div class="input-group">
                        <input class="input--style-2" type="text" placeholder="아이디" name="mem_id" id="eid"
                               v-model="mem_id">
                        <p style="color:red">{{id_check}}</p>
                    </div>

                    <div class="input-group">
                        <input class="input--style-2" type="text" placeholder="이름" name="mem_name" id="name"
                               v-model="mem_name">
                    </div>

                    <div class="input-group">
                        <input class="input--style-2" type="password" placeholder="비밀번호" name="mem_pw" id="pass"
                               v-model="mem_pw">
                    </div>

                    <div class="input-group">
                        <input class="input--style-2" type="password" placeholder="비밀번호 확인" name="password_check"
                               v-model="mem_pw_check">
                        <p style="color:red">{{pw_check}}</p>
                    </div>

                    <div class="input-group">
                        <input class="input--style-2" type="text" placeholder="휴대폰 번호 입력" name="phone_number"
                               id="phone" v-model="mem_phone">
                        <p style="color:red">{{phone_check}}</p>
                    </div>

                    <!-- validate phone number -->
                    <button @click="sendNum()" class="btn2 warning">인증번호 보내기</button>
                    <div display id="myDIV" style="display:none">
                        <p>인증번호가 전송되었습니다.</p>
                        <input class=" input-group" style="margin:3px" type="text" name="fname" placeholder="ex)8282" v-model="mem_vali_num">
                        <button class="btn2 warning" @click="checkValiNum()">확인</button>
                    </div>
                    <div display id="myDIV2" style="display:none">
                        <p>{{vali_message}}</p>
                    </div>

                    <div class="input-group">
                        <div class="rs-select2 js-select-simple select--no-search">
                            <select name="mem_class" v-model="class_code">
                                <!-- <select mem_name="mem_class" id="class" v-model="">-->
                                <option disabled="disabled" selected="selected ">수강중인 수업 선택</option>
                                <option value="IP">인공지능 P반</option>
                                <option value="IA">인공지능 A반</option>
                                <option value="DSY">데이터과학 영등포</option>
                                <option value="DSS">데이터과학 서초</option>
                            </select>
                            <div class="select-dropdown "></div>
                        </div>
                    </div>

                    <div class="p-t-30 ">
                        <button class="btn btn--radius btn--green" id="btn_memberJoin"
                                @click="submitForm">회원가입
                        </button>
                    </div>
                </div>
            </div>
        </div>
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
        data: function () {
            return {
                mem_id: "",
                mem_pw: "",
                mem_pw_check: "",
                mem_name: "",
                mem_phone: "",
                class_code: "",
                validate_num: "",
                mem_vali_num: "",
                mem_certify: false,
                vali_message: "",
                id_check: "",
                phone_check: "",
                pw_check: "",
            }
        },
        //post axios
        methods: {
            sendNum() {
                var x = document.getElementById("myDIV");
                if (x.style.display === "none") {
                    x.style.display = "block";
                    axios.post("members/phone/" + this.mem_phone)
                        .then(res => {
                            console.log("send message status : true")
                            this.validate_num = JSON.stringify(res.data.validate_num);
                        }).catch(e => {
                        console.log("fail send message");
                        //controller에서 넘어온 에러 문구 출력
                        alert(JSON.stringify(e.response.data.message));
                    });
                }
            },
            checkValiNum() {
                var x = document.getElementById("myDIV");
                var x2 = document.getElementById("myDIV2");
                if(this.validate_num === this.mem_vali_num) {
                    this.mem_certify = true;
                    this.vali_message = "인증이 완료되었습니다.";
                    if(x.style.display === "block") {
                        x.style.display = "none";
                    }
                } else {
                    this.vali_message = "인증번호가 일치하지 않습니다.";
                    x2.style.display = "block";
                }
                x2.style.display = "block";
            },
            submitForm() {

                console.log("vue : start submitForm");

                axios.post("/members/new", {
                    //pw check -> server
                    mem_id: this.mem_id,
                    mem_pw: this.mem_pw,
                    mem_pw_check: this.mem_pw_check,
                    mem_name: this.mem_name,
                    mem_phone: this.mem_phone,
                    class_code: this.class_code,
                    mem_certify: this.mem_certify,
                }).then(res => {

                    if(res.data.status) {
                        console.log("status : true");
                        alert(this.mem_name + "님이 가입되었습니다.");
                        this.$router.push({ name: 'Home' });
                    }
                }).catch(e => {
                    console.log("sign in fail");
                    //controller에서 넘어온 에러 문구 출력
                    alert(JSON.stringify(e.response.data.message));
                });
            },
            init() {
                console.log("data initialize");
                this.mem_id = "";
                this.mem_pw = "";
                this.mem_name = "";
                this.mem_phone = "";
                this.class_no = "";
                this.validate_num = "";
                this.mem_vali_num = "";
                this.mem_certify = false;
                this.vali_message = "";
                this.id_check = "";
                this.phone_check = "";
                this.pw_check = "";
            }
        },
        watch: {
            mem_id: function(val) {
                axios.post("members/check/id/" + val)
                    .then(res => {
                        console.log("duplication check status : true");
                        this.id_check = "";
                    }).catch(e => {
                    this.id_check = e.response.data.message;
                    if(this.id_check === "No message available") {
                        this.id_check = "";
                    }
                });
            },
            mem_phone: function(val) {
                axios.post("members/check/phone/" + val)
                    .then(res => {
                        console.log("duplication check status : true");
                        this.phone_check = "";
                    }).catch(e => {
                    this.phone_check = e.response.data.message;
                    if(this.phone_check === "No message available") {
                        this.phone_check = "";
                    }
                });
            },
            mem_pw: function(val) {
                axios.post("/members/check/pw", {
                    mem_pw: val,
                    mem_pw_check: this.mem_pw_check,

                }).then(res => {
                    console.log("password same check status : true");
                    this.pw_check = "";
                }).catch(e => {
                    this.pw_check = e.response.data.message;
                    if(this.pw_check === "No message available") {
                        this.pw_check = "";
                    }
                });
            },
            mem_pw_check: function(val) {
                axios.post("/members/check/pw", {
                    mem_pw: this.mem_pw,
                    mem_pw_check: val,

                }).then(res => {
                    console.log("password same check status : true");
                    this.pw_check = "";
                }).catch(e => {
                    this.pw_check = e.response.data.message;
                    if(this.pw_check === "No message available") {
                        this.pw_check = "";
                    }
                });
            }
        }, mounted() {
            this.init();
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    @import url("https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css");
    @import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");

    .btn2 {
        border: 2px solid black;
        background-color: white;
        color: black;
        padding: 1px 10px;
        font-size: 14px;
        cursor: pointer;
    }
    .warning {
        border-color: #ff9800;
        color: orange;
    }
    .warning:hover {
        background: #ff9800;
        color: white;
    }
    .font-robo {
        font-family: 'Do Hyeon', sans-serif;
    }
    .row {
        display: -webkit-box;
        display: -webkit-flex;
        display: -moz-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-flex-wrap: wrap;
        -ms-flex-wrap: wrap;
        flex-wrap: wrap;
    }
    .row-space {
        -webkit-box-pack: justify;
        -webkit-justify-content: space-between;
        -moz-box-pack: justify;
        -ms-flex-pack: justify;
        justify-content: space-between;
    }
    .col-2 {
        width: -webkit-calc((100% - 60px) / 2);
        width: -moz-calc((100% - 60px) / 2);
        width: calc((100% - 60px) / 2);
    }
    @media (max-width: 767px) {
        .col-2 {
            width: 100%;
        }
    }
    /* ==========================================================================
       #BOX-SIZING
       ========================================================================== */
    /**
     * More sensible default box-sizing:
     * css-tricks.com/inheriting-box-sizing-probably-slightly-better-best-practice
     */
    html {
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }
    * {
        padding: 0;
        margin: 0;
    }
    *,
    *:before,
    *:after {
        -webkit-box-sizing: inherit;
        -moz-box-sizing: inherit;
        box-sizing: inherit;
    }
    /* ==========================================================================
       #RESET
       ========================================================================== */
    /**
     * A very simple reset that sits on top of Normalize.css.
     */
    body,
    h1,
    h2,
    h3,
    h4,
    h5,
    h6,
    blockquote,
    p,
    pre,
    dl,
    dd,
    ol,
    ul,
    figure,
    hr,
    fieldset,
    legend {
        margin: 0;
        padding: 0;
    }
    /**
     * Remove trailing margins from nested lists.
     */
    li > ol,
    li > ul {
        margin-bottom: 0;
    }
    /**
     * Remove default table spacing.
     */
    table {
        border-collapse: collapse;
        border-spacing: 0;
    }
    /**
     * 1. Reset Chrome and Firefox behaviour which sets a `min-width: min-content;`
     *    on fieldsets.
     */
    fieldset {
        min-width: 0;
        /* [1] */
        border: 0;
    }

    /* ==========================================================================
       #PAGE WRAPPER
       ========================================================================== */
    .page-wrapper {
        min-height: 100vh;
    }
    body {
        font-family: "Roboto", "Arial", "Helvetica Neue", sans-serif;
        font-weight: 400;
        font-size: 14px;
    }
    h1,
    h2,
    h3,
    h4,
    h5,
    h6 {
        font-weight: 400;
    }
    h1 {
        font-size: 36px;
    }
    h2 {
        font-size: 30px;
    }
    h3 {
        font-size: 24px;
    }
    h4 {
        font-size: 18px;
    }
    h5 {
        font-size: 15px;
    }
    h6 {
        font-size: 13px;
    }
    /* ==========================================================================
       #BACKGROUND
       ========================================================================== */
    .bg-blue {
        background: #d59763;
    }
    .bg-red {
        background: #fadd91;
    }
    /* ==========================================================================
       #SPACING
       ========================================================================== */
    .p-t-100 {
        padding-top: 100px;
    }
    .p-t-180 {
        padding-top: 180px;
    }
    .p-t-20 {
        padding-top: 20px;
    }
    .p-t-30 {
        padding-top: 30px;
    }
    .p-b-100 {
        padding-bottom: 100px;
    }
    /* ==========================================================================
       #WRAPPER
       ========================================================================== */
    .wrapper {
        margin: 0 auto;
    }
    .wrapper--w960 {
        max-width: 960px;
    }
    .wrapper--w680 {
        max-width: 680px;
    }
    /* ==========================================================================
       #BUTTON
       ========================================================================== */
    .btn {
        line-height: 40px;
        display: inline-block;
        padding: 0 25px;
        cursor: pointer;
        color: #fff;
        font-family: "Roboto", "Arial", "Helvetica Neue", sans-serif;
        -webkit-transition: all 0.4s ease;
        -o-transition: all 0.4s ease;
        -moz-transition: all 0.4s ease;
        transition: all 0.4s ease;
        font-size: 14px;
        font-weight: 700;
    }
    .btn--radius {
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
    }
    .btn--green {
        background: #57b846;
    }
    .btn--green:hover {
        background: #4dae3c;
    }
    /* ==========================================================================
       #DATE PICKER
       ========================================================================== */
    td.active {
        background-color: #2c6ed5;
    }
    input[type="date" i] {
        padding: 14px;
    }
    .table-condensed td,
    .table-condensed th {
        font-size: 14px;
        font-family: "Roboto", "Arial", "Helvetica Neue", sans-serif;
        font-weight: 400;
    }
    .daterangepicker td {
        width: 40px;
        height: 30px;
    }
    .daterangepicker {
        border: none;
        -webkit-box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
        -moz-box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
        box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
        display: none;
        border: 1px solid #e0e0e0;
        margin-top: 5px;
    }
    .daterangepicker::after,
    .daterangepicker::before {
        display: none;
    }
    .daterangepicker thead tr th {
        padding: 10px 0;
    }
    .daterangepicker .table-condensed th select {
        border: 1px solid #ccc;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
        font-size: 14px;
        padding: 5px;
        outline: none;
    }
    /* ==========================================================================
       #FORM
       ========================================================================== */
    input {
        outline: none;
        margin: 0;
        border: none;
        -webkit-box-shadow: none;
        -moz-box-shadow: none;
        box-shadow: none;
        width: 100%;
        font-size: 14px;
        font-family: inherit;
    }
    /* input group 1 */
    /* end input group 1 */
    .input-group {
        position: relative;
        margin-bottom: 32px;
        border-bottom: 1px solid #e5e5e5;
    }
    .input-icon {
        position: absolute;
        font-size: 18px;
        color: #ccc;
        right: 8px;
        top: 50%;
        -webkit-transform: translateY(-50%);
        -moz-transform: translateY(-50%);
        -ms-transform: translateY(-50%);
        -o-transform: translateY(-50%);
        transform: translateY(-50%);
        cursor: pointer;
    }
    .input--style-2 {
        padding: 9px 0;
        color: #666;
        font-size: 16px;
        font-weight: 500;
    }
    .input--style-2::-webkit-input-placeholder {
        /* WebKit, Blink, Edge */
        color: #808080;
    }
    .input--style-2:-moz-placeholder {
        /* Mozilla Firefox 4 to 18 */
        color: #808080;
        opacity: 1;
    }
    .input--style-2::-moz-placeholder {
        /* Mozilla Firefox 19+ */
        color: #808080;
        opacity: 1;
    }
    .input--style-2:-ms-input-placeholder {
        /* Internet Explorer 10-11 */
        color: #808080;
    }
    .input--style-2:-ms-input-placeholder {
        /* Microsoft Edge */
        color: #808080;
    }
    /* ==========================================================================
       #SELECT2
       ========================================================================== */
    .select--no-search .select2-search {
        display: none !important;
    }
    .rs-select2 .select2-container {
        width: 100% !important;
        outline: none;
    }
    .rs-select2 .select2-container .select2-selection--single {
        outline: none;
        border: none;
        height: 36px;
    }
    .rs-select2 .select2-container .select2-selection--single .select2-selection__rendered {
        line-height: 36px;
        padding-left: 0;
        color: #808080;
        font-size: 16px;
        font-family: inherit;
        font-weight: 500;
    }
    .rs-select2 .select2-container .select2-selection--single .select2-selection__arrow {
        height: 34px;
        right: 4px;
        display: -webkit-box;
        display: -webkit-flex;
        display: -moz-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-pack: center;
        -webkit-justify-content: center;
        -moz-box-pack: center;
        -ms-flex-pack: center;
        justify-content: center;
        -webkit-box-align: center;
        -webkit-align-items: center;
        -moz-box-align: center;
        -ms-flex-align: center;
        align-items: center;
    }
    .rs-select2 .select2-container .select2-selection--single .select2-selection__arrow b {
        display: none;
    }
    .rs-select2 .select2-container .select2-selection--single .select2-selection__arrow:after {
        font-family: "Material-Design-Iconic-Font";
        content: '\f2f9';
        font-size: 18px;
        color: #ccc;
        -webkit-transition: all 0.4s ease;
        -o-transition: all 0.4s ease;
        -moz-transition: all 0.4s ease;
        transition: all 0.4s ease;
    }
    .rs-select2 .select2-container.select2-container--open .select2-selection--single .select2-selection__arrow::after {
        -webkit-transform: rotate(-180deg);
        -moz-transform: rotate(-180deg);
        -ms-transform: rotate(-180deg);
        -o-transform: rotate(-180deg);
        transform: rotate(-180deg);
    }
    .select2-container--open .select2-dropdown--below {
        border: none;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
        -webkit-box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
        -moz-box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
        box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
        border: 1px solid #e0e0e0;
        margin-top: 5px;
        overflow: hidden;
    }
    /* ==========================================================================
       #TITLE
       ========================================================================== */
    .title {
        text-transform: uppercase;
        font-weight: 700;
        margin-bottom: 37px;
    }
    /* ==========================================================================
       #CARD
       ========================================================================== */
    .card {
        overflow: hidden;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
        background: #fff;
    }
    .card-2 {
        -webkit-box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
        -moz-box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
        box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
        -webkit-border-radius: 10px;
        -moz-border-radius: 10px;
        border-radius: 10px;
        width: 100%;
        display: table;
    }
    .card-2 .card-heading {
        background: url("../assets/sun.png") top left/cover no-repeat;
        width: 29.1%;
        display: table-cell;
    }
    .card-2 .card-body {
        display: table-cell;
        padding: 80px 90px;
        padding-bottom: 88px;
    }
    @media (max-width: 767px) {
        .card-2 {
            display: block;
        }
        .card-2 .card-heading {
            width: 100%;
            display: block;
            padding-top: 300px;
            background-position: left center;
        }
        .card-2 .card-body {
            display: block;
            padding: 60px 50px;
        }
    }
</style>