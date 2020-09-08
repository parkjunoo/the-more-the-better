<template>
<div>
  <div id="box1">
    <div id="map"></div>
  </div>
  <div id="box2">
    <EnrollForm></EnrollForm>
  </div>
</div>
</template>


<script>
import EnrollForm from './ServiceEnrollForm.vue'
export default {
  name: 'Service-Eroll',
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
      ps.keywordSearch('남부터미널역 카페', this.placesSearchCB);
    },
    placesSearchCB (data, status, pagination) {
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
  displayMarker(place) {
    // 마커를 생성하고 지도에 표시합니다
    var marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(place.y, place.x) 
    });
    // 마커에 클릭이벤트를 등록합니다
    this.infowindow.open(this.map, this.marker);
    kakao.maps.event.addListener(marker, 'click', function() {changeText(place.place_name);});
  },
  changeText(s){
        var updatedText = s;
        this.storeName = updatedText;
    },
  },

  //서버전송
  onSubmit(){
		axios.post("/order", {
      storeName: this.storeName,    //가게 이름 
      people: this.people,          //인원
      time: this.time,              //모집마감시간
      deliPlace: this.deliPlace,    //배달수령장소
      minCost: this.minCost,        //최소주문가격
      text: this.text               //부가설명
		}).then(res =>{
		  console.log("성공");
		}).catch(e => {
		  this.setInfo("실패", "", JSON.stringify(e.response || e.message));
    });
  }
}
  
</script>
<style scoped>

#box1{
  float: left;
  width: 500px;
  height: 500px;
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
</style>