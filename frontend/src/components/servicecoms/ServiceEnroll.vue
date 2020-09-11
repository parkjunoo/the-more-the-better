<template>
<div>
  <div><h1>aaaaaa</h1></div>
  <div id="divBox">
      <div id="box1" style="background-color: #FFFFF0" >
        <div id="map"></div>
      </div>
      <div id="box2">
        <div id="form-div">
          <p1> 모아보자 </p1>
          <div class="form" id="form1">
          <p5> 모집장소 </p5>
          <p class="name">
            <input name="name" type="text" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" :placeholder="storeName" id="name" />
          </p>
          <p5>모집인원</p5>
          <select class="people" name = "people" v-model="people">
              <optgroup label = "모집인원">
                <option value = 1>1명</option>
                <option value = 2>2명</option>
                <option value = 3>3명</option>
                <option value = 4>4명</option>
              </optgroup>         
        </select>
        <p5 id="a">마감시간</p5>
        <p class="time"><input v-model="time" type="time"></p>
        <p5>수령장소</p5>
        <p class="email">
            <input v-model="deliPlace" name="email" type="text" class="validate[required,custom[email]] feedback-input" id="email" placeholder="수령할 장소를 정해주세요!" />
        </p>
        <p5>배달금액</p5>
        <p class="email">
            <input v-model="minCost" name="email" type="text" class="validate[required,custom[email]] feedback-input" id="email" placeholder="최소금액을 입력해주세요!" />
        </p>
        <p5>설명</p5>
        <p class="text">
            <textarea v-model="text" name="text" class="validate[required,length[6,300]] feedback-input" id="comment" placeholder="추가설명"></textarea>
        </p>
        <div class="submit">
            <input v-on:click="onSubmit()" type="submit" value="SEND" id="button-blue"/>
            <div class="ease"></div>
        </div>
    </div>
    </div>
  </div>
</div>
</div>
</template>


<script>

import axios from 'axios'
const storage = window.sessionStorage; 
export default {
  name: 'ServiceEroll',
  data(){
          return{
            storeName: '임시',    //가게 이름 
            people: 0,          //인원
            time: '',              //모집마감시간
            deliPlace: '',    //배달수령장소
            minCost: 0,        //최소주문가격
            text: '',
            storeAddress: '서울시..',
            storeImg: ''           
          }
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
      
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.src = "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=fd19cf04fdf51376c09688db7233c214&libraries=services,clusterer,drawing";
      script.onload = () => kakao.maps.load(this.initMap);
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      this.infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      var mapContainer = document.getElementById("map"),
        mapOption = {
          center: new kakao.maps.LatLng(37.3968027500691, 126.920941421328), //고객이 등록한 정보가 default
          level: 3,
        };

      this.map = new kakao.maps.Map(mapContainer, mapOption);
      var geocoder = new kakao.maps.services.Geocoder();
      var ps = new kakao.maps.services.Places(); 

      ps.keywordSearch('남부터미널역 카페', this.placesSearchCB)
    },
    placesSearchCB: function (data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLngBounds();

        for (var i=0; i<data.length; i++) {
            this.displayMarker(data[i]);    
            coords.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }       
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        
        this.map.setBounds(coords);
    }
  },
    // 마커를 생성하고 지도에 표시합니다
  displayMarker: function (place) {
    var self = this;
    var marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(place.y, place.x) 
        
    });
    // 마커에 클릭이벤트를 등록합니다
    this.infowindow = new kakao.maps.InfoWindow({
      content: '<div style="width:100px;text-align:center;padding:6px 0;">' + place.place_name +'</div>',
    });
    this.infowindow.open(this.map, marker);
    kakao.maps.event.addListener(marker, 'click', function() {
      self.storeName = place.place_name;
    });
  },
  onSubmit(){
            alert(
                    " 상점: "+ this.storeName +
                    " 인원: "+ this.people +
                    " 시간: "+ this.time +
                    " 배달장소:" + this.deliPlace +
                    " 최소금액: " + this.minCost +
                    " 추가설명: " + this.text
                 )
            axios.post('/order', {
                storeName: this.storeName,    //가게 이름 
                people: this.people,          //인원
                time: this.time,              //모집마감시간
                deliPlace: this.deliPlace,    //배달수령장소
                minCost: this.minCost,        //최소주문가격
                text: this.text,               //부가설명
                storeAddress: this.storeAddress,
                storeImg: this.storeImg
            },
            {
                headers: {
                  "mem_no" : storage.getItem("member")
                }
            }
            ).then(res =>{
                console.log("성공");
            })//.catch(e => {
            //     alert("실패");
            //     alert(
            //         " 상점: "+ this.storeName +
            //         " 인원: "+ this.people +
            //         " 시간: "+ this.time +
            //         " 배달장소:" + this.deliPlace +
            //         " 최소금액: " + this.minCost +
            //         " 추가설명: " + this.text
            //     )
            // });
        }
  }

  //서버전송
  
}
  
</script>
<style scoped>

#divBox{
  padding: 35px 135px 50px;

}

#box1{
  float: left;
  width: 500px;
  height: 500px;
  padding: 35px 35px 50px;
}
#map{
  width:600px;
  height:500px;
}
#box2{
  float: left;
  width: 500px;
  height: 500px;
}
@import url('https://fonts.googleapis.com/css?family=Montserrat:400,700');
p5 {
  color : white;
  font-weight: bold;
}
#a {
  display: inline;
}

html{
    height:100%;
    
}
#map{
  width:600px;
  height:500px;
}

#form-div {
    margin-left: 100px;
    background-color: #bbb;
    padding:35px 35px 50px;
    width: 450px;
    left: 20%;
    position: relative;
    -moz-border-radius: 7px;
    -webkit-border-radius: 7px;
}

.feedback-input {
    color:#3c3c3c;
    font-family: Helvetica, Arial, sans-serif;
    font-weight:500;
    font-size: 18px;
    border-radius: 0;
    line-height: 22px;
    background-color: #ffffff;
    margin-bottom: 10px;
    width:100%;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    -ms-box-sizing: border-box;
    box-sizing: border-box;
    border: 3px solid rgba(0,0,0,0);
}

.feedback-input:focus{
    background: #fff;
    box-shadow: 0;
    /*border: 3px solid #3498db;*/
    border-color: #3498db;
    color: #3498db;
    outline: none;
    /*padding: 13px 13px 13px 54px;*/
}

.focused {
    color:#30aed6;
    border:#30aed6 solid 3px;
}

/* Icons */
#name{
    background-size: 20px 20px;
    background-position: 11px 8px;
    background-repeat: no-repeat;
}

#email{
    background-size: 20px 20px;
    background-position: 11px 8px;
    background-repeat: no-repeat;
}

#comment{
    background-size: 20px 20px;
    background-position: 11px 8px;
    background-repeat: no-repeat;
}

textarea {
    width: 100%;
    height: 150px;
    line-height: 150%;
    resize:vertical;
}

input:hover, textarea:hover,
input:focus, textarea:focus {
    background-color:white;
}

#button-blue{
    font-family: 'Montserrat', Arial, Helvetica, sans-serif;
    float:left; /* 플롯 중요(::after 가상요소 이용)*/
    width: 100%;
    border: #fbfbfb solid 4px;
    cursor:pointer;
    background-color: #3498db;
    color:white;
    font-size:24px;
    padding-top:22px;
    padding-bottom:22px;
    -webkit-transition: all 0.3s;
    -moz-transition: all 0.3s;
    transition: all 0.3s;
    margin-top:-4px;
    font-weight:700;

}

#button-blue:hover{
    background-color: rgba(0,0,0,0);
    color: #0493bd;
}

.ease {
    width: 0;
    height: 74px;
    background-color: #fbfbfb;
    -webkit-transition: .3s ease;
    -moz-transition: .3s ease;
    -o-transition: .3s ease;
    -ms-transition: .3s ease;
    transition: .3s ease;
}

.submit:hover .ease{
    width:100%;
    background-color:white;
}

.cover{
  text-align: center;
}


</style>