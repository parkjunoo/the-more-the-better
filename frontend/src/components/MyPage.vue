<template>
	<!DOCTYPE html>
<html lang="en">
<head>
<title>Page Title</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

<div class="header">
  <h1>{{mem_name}}님 안녕하세요!</h1>
  <p> 🤍 배달료 줄이고 행복을 더하세요! 多多益善 🤍</p>
</div>

<div class="row">
  <div class="side">
    <h2>나의 현재 주문정보</h2>
    <div class="orderimg" style="height:200px;">
      <img src="" style="heigh : 100%; width : 100%; object-fit : contain;"/>
    </div>
    <p>Some text about me in culpa qui officia deserunt mollit anim..</p>
    <h3>More Text</h3>
    <p>Lorem ipsum dolor sit ame.</p>
    <div class="fakeimg" style="height:60px;">Image</div><br>
    <div class="fakeimg" style="height:60px;">Image</div><br>
    <div class="fakeimg" style="height:60px;">Image</div>
  </div>
  <div class="main">
    <h2>TITLE HEADING</h2>
    <h5>Title description, Dec 7, 2017</h5>
    <div class="fakeimg" style="height:200px;">Image</div>
    <p>Some text..</p>
    <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
    <br>
    <h2>TITLE HEADING</h2>
    <h5>Title description, Sep 2, 2017</h5>
    <div class="fakeimg" style="height:200px;">Image</div>
    <p>Some text..</p>
    <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
  </div>
</div>

</body>
</html>

</template>

<script>

	import axios from 'axios';
  const storage = window.sessionStorage; 
	export default {
		name: 'MyPage',
        
        data: function() {
			return {
				mem_no : "",
        mem_name : "",
        order_name : "",
        st_name : "",
        st_picture : "",
				get_mem_info_address : "",
				get_order_info_address : "",
			}
		},
		methods:{
			getMemberInfo() {
				axios.get(this.get_mem_info_address)
				.then(res => {
					if(res.data.status) {
						console.log("search status : true");
            this.mem_name = res.data.member.name;
            this.get_order_info_address = "/order/info/" + this.mem_no;
            this.getOrderInfo();
					}
				}).catch(e => {
          console.log("error : " + e.response.data.message);
        });
      },
      getOrderInfo() {
        axios.get(this.get_order_info_address)
				.then(res => {
					if(res.data.status) {
						console.log("search status : true");
            this.order_name = res.data.member.name;
            this.st_name = res.data.store.name;
            this.st_picture = res.data.store.name;
					}
				}).catch(e => {
          console.log("error : " + e.response.data.message);
        });
      },
			init() {
				console.log("mypage data initialize");

				if(storage.getItem("member")) {
          
					console.log("login state : true");
					this.mem_no = storage.getItem("member");
          this.get_mem_info_address = "/members/info/" + this.mem_no;

					this.getMemberInfo();
        }
			}
		}, mounted() {
			this.init();
		}
	}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

/* Style the body */
body {
  font-family: 'Do Hyeon', sans-serif;
  margin: 0;
}

/* Header/logo Title */
.header {
  padding: 80px;
  text-align: center;
  background: #1abc9c;
  color: white;
}

/* Increase the font size of the heading */
.header h1 {
  font-size: 40px;
}

/* Style the top navigation bar */
.navbar {
  overflow: hidden;
  background-color: #333;
}

/* Style the navigation bar links */
.navbar a {
  float: left;
  display: block;
  color: white;
  text-align: center;
  padding: 14px 20px;
  text-decoration: none;
}

/* Right-aligned link */
.navbar a.right {
  float: right;
}

/* Change color on hover */
.navbar a:hover {
  background-color: #ddd;
  color: black;
}

/* Column container */
.row {  
  display: -ms-flexbox; /* IE10 */
  display: flex;
  -ms-flex-wrap: wrap; /* IE10 */
  flex-wrap: wrap;
}

/* Create two unequal columns that sits next to each other */
/* Sidebar/left column */
.side {
  -ms-flex: 30%; /* IE10 */
  flex: 30%;
  background-color: #f1f1f1;
  padding: 20px;
}

/* Main column */
.main {   
  -ms-flex: 70%; /* IE10 */
  flex: 70%;
  background-color: white;
  padding: 20px;
}

/* Fake image, just for this example */
.fakeimg {
  background-color: #aaa;
  width: 100%;
  padding: 20px;
}

/* Footer */
.footer {
  padding: 20px;
  text-align: center;
  background: #ddd;
}

/* Responsive layout - when the screen is less than 700px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 700px) {
  .row {   
    flex-direction: column;
  }
}

/* Responsive layout - when the screen is less than 400px wide, make the navigation links stack on top of each other instead of next to each other */
@media screen and (max-width: 400px) {
  .navbar a {
    float: none;
    width: 100%;
  }
}

</style>