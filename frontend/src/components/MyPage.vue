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
    <h2 class="order">나의 현재 주문정보</h2>

    <!-- 주문이 존재할 경우 -->
    <div v-if="my_order_state" class="col-25">
      <div class="container">
        <h4>가게 이름<span class="price" style="color:black"><i class="fa fa-shopping-cart"></i> <b>{{st_name}}</b></span></h4>
        <hr>
        <p>시작 시간<span class="price">{{order_start_date}}</span></p>
        <p>마감 시간<span class="price">{{order_dead_date}}</span></p>
        <p>주문 인원<span class="price">{{order_standby}}/{{order_min_pers}}
            <div class="dropdown">
              <button @click="detailMember" class="dropbtn">자세히보기</button>
              <div id="myDropdown" class="dropdown-content">
                <p>주문생성자 : {{order_host}}</p>
                <p><대기멤버></p>
                <p v-for="member in members" v-bind:key="member.id">{{member.name}}</p>
              </div>
            </div>
          </span>
        </p>
        <p>배달 수령 장소<span class="price">{{order_meetplace}}</span></p>
        <p>최소 주문 가격<span class="price">{{order_min_pay}}</span></p>
        <p>부가 설명<span class="price">{{order_text}}</span></p>
        <hr>        
        <button @click="cancelOrder" class="btn success">주문취소</button>
      </div>
    </div>

    <!-- 주문이 존재하지 않을 경우 -->
    <div v-else class="header">
        <p>주문이 존재하지 않습니다.</p>
        <!-- 게시판으로 이동! -->
        <a href="">주문하러 가기</a>
    </div>
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

          order_no : "",
          order_start_date : "",
          order_dead_date : "",
          order_standby : "",
          order_min_pers : "",
          order_meetplace : "",
          order_min_pay : "",
          order_text : "",
          order_host : "",
          order_members : "",

          st_name : "",

          get_mem_info_address : "",
          get_order_info_address : "",
          members: [],
          my_order_state: false,
        }
		},
		methods:{
      detailMember() {
        document.getElementById("myDropdown").classList.toggle("show");
        window.onclick = function(event) {
          if (!event.target.matches('.dropbtn')) {
            var dropdowns = document.getElementsByClassName("dropdown-content");
            var i;
            for (i = 0; i < dropdowns.length; i++) {
              var openDropdown = dropdowns[i];
              if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
              }
            }
          }
        }
      },
      cancelOrder() {
        var result = confirm("정말 주문을 취소하시겠습니까?");
        
        if(result) {
          axios.delete("/order/" + this.order_no + "/" + this.mem_no)
          .then(res => {
            if(res.data.status) {
            console.log("delete : success");
            alert("주문이 취소되었습니다.");
              this.init();
            }
          }).catch(e => {
            console.log("delete : fail");
            alert(JSON.stringify(e.response.data.message));
          });
        }
      },
      setArray() {
        var length = JSON.parse(this.order_standby);
        for(var i=0; i<length; i++) {
          this.members.push({
            id: i,
            name: this.order_members[i].name,
          })
        }
      },
			getMemberInfo() {
				axios.get(this.get_mem_info_address)
				.then(res => {
					if(res.data.status) {
						console.log("member search status : true");
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
            this.my_order_state = true;
            console.log("order search status : true");
            this.order_no = res.data.order.no;
            this.order_start_date = res.data.order.createdDate;
            this.order_dead_date = res.data.order.closetime;
            this.order_standby = res.data.order.standby;
            this.order_min_pers = res.data.order.minperson;
            this.order_meetplace = res.data.order.meetplace;
            this.order_min_pay = res.data.order.mincost;
            this.order_text = res.data.order.text;
            this.order_host = res.data.order.host.name;
            this.order_members = res.data.order.waitingmems;                      
            this.setArray();
            
            this.st_name = res.data.store.name;
					}
				}).catch(e => {
          console.log("error : " + e.response.data.message);
        });
      },
			init() {
				console.log("mypage data initialize");
        
          this.mem_name = "";

          this.order_no = "";
          this.order_start_date = "";
          this.order_dead_date = "";
          this.order_standby = "";
          this.order_min_pers = "";
          this.order_meetplace = "";
          this.order_min_pay = "";
          this.order_text = "";
          this.order_host = "";
          this.order_members = "";

          this.st_name = "";

          this.get_mem_info_address = "";
          this.get_order_info_address = "";
          this.members= [];
          this.my_order_state = false;

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
.notexist {
  overflow: hidden;
  background-color: #f1f1f1;
  padding: 20px 10px;
}

.notexist a {
  float: left;
  color: black;
  text-align: center;
  padding: 12px;
  text-decoration: none;
  font-size: 18px; 
  line-height: 25px;
  border-radius: 4px;
}

.notexist a.logo {
  font-size: 25px;
  font-weight: bold;
}

.notexists a:hover {
  background-color: #ddd;
  color: black;
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

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  overflow: auto;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content p {
  color: black;
  padding: 3px 16px;
  text-decoration: none;
  display: block;
}

.dropdown a:hover {background-color: #ddd;}

.show {display: block;}

.success {
  background-color: #4CAF50;
} /* Green */

.success:hover {
  background-color: #46a049;
}

.btn {
  border: none;
  color: white;
  padding: 14px 28px;
  font-size: 16px;
  cursor: pointer;
}

.col-25 {
  -ms-flex: 25%; /* IE10 */
  flex: 25%;
  padding: 0 16px;
  margin-bottom: 20px;
}

.container {
  background-color: #f2f2f2;
  padding: 5px 20px 15px 20px;
  border: 1px solid lightgrey;
  border-radius: 3px;
}

.order {
  text-align: center;
}

hr {
  border: 1px solid lightgrey;
}

span.price {
  float: right;
  color: grey;
}

/* Style the body */
body {
  font-family: 'Do Hyeon', sans-serif;
  margin: 40px;
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