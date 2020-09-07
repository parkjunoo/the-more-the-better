<template>
<div id="print">
  <div id="map" style="width:600px;height:500px;"></div>
  <div id="form-div">
    <form class="form" id="form1">
        <p5>가게이름</p5>
        <p class="name">
            <input name="name" type="text" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="Name" id="name" />
        </p>
        <p5>모집인원</p5>
        <select class="people" name = "people">
                        <optgroup label = "모집인원">
                            <option value = "one">1명</option>
                            <option value = "two">2명</option>
                            <option value = "three">3명</option>
                            <option value = "four">4명</option>
                        </optgroup>         
        </select>
        <p5 id="a">마감시간</p5>
        <p class="time"><input type="time"></p>
        <p5>수령장소</p5>
        <p class="email">
            <input name="email" type="text" class="validate[required,custom[email]] feedback-input" id="email" placeholder="Email" />
        </p>
        <p5>배달금액</p5>
        <p class="email">
            <input name="email" type="text" class="validate[required,custom[email]] feedback-input" id="email" placeholder="Email" />
        </p>
        <p5>설명</p5>
        <p class="text">
            <textarea name="text" class="validate[required,length[6,300]] feedback-input" id="comment" placeholder="Comment"></textarea>
        </p>
        <div class="submit">
            <input type="submit" value="SEND" id="button-blue"/>
            <div class="ease"></div>
        </div>
    </form>
</div>
  
</div>
</template>

<script>
export default {
  name: 'ServiceEroll',
  data(){
    return{
      locate : '남부터미널 맛집',
      storeName : '아직안정함',
      member :[{
        one : '1명',
        two : '2명',
        three : '3명',
        four : '4명'
      }],
      deliLocation:"",
      minCost:"",
      explanation:""
    }
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=fd19cf04fdf51376c09688db7233c214&libraries=services,clusterer,drawing";
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
          level: 6,
        };

      this.map = new kakao.maps.Map(mapContainer, mapOption);
      var geocoder = new kakao.maps.services.Geocoder();
      var ps = new kakao.maps.services.Places(); 
      ps.keywordSearch('남부터미널역 카페', this.placesSearchCB); 
      //geocoder.addressSearch('남부터미너 맛집', this.placesSearchCB);
    },
    placesSearchCB: function (data,status,position) {

      if (status === kakao.maps.services.Status.OK) {
        for (var i=0; i<data.length; i++) {
          console.log(data[i].place_name);
          this.coords = new kakao.maps.LatLng(data[i].y, data[i].x);
          var marker = new kakao.maps.Marker({
            map: this.map,
            position: this.coords,
            place: data[i].place_name
          });
       
          kakao.maps.event.addListener(marker, 'click', function() {
            console.log(marker.map);
          });
          var infowindow = new kakao.maps.InfoWindow({
            content:
              '<div style="width:150px;text-align:center;padding:6px 0;">'+ data[i].place_name +'</div>'
          });
          infowindow.open(this.map, marker);
        }
        this.map.setCenter(this.coords);
      }
    }
  },
}
  
</script>
<style scoped>
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
    left: 50%;
    position: relative;
    margin-top:30px;
    margin-left: -260px;
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
    padding: 13px 13px 13px 54px;
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
    background-image: url(http://rexkirby.com/kirbyandson/images/name.svg);
    background-size: 20px 20px;
    background-position: 11px 8px;
    background-repeat: no-repeat;
}

#email{
    background-image: url(http://rexkirby.com/kirbyandson/images/email.svg);
    background-size: 20px 20px;
    background-position: 11px 8px;
    background-repeat: no-repeat;
}

#comment{
    background-image: url(http://rexkirby.com/kirbyandson/images/comment.svg);
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