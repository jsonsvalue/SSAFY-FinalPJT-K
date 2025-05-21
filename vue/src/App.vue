<template>
  <div class="container">
    <h2 class="title">
      Hi there, Find your favorite places to dine
    </h2>

    <div class="search-bar">
      <input type="text" v-model="keyword" @keyup.enter="submit" placeholder="검색어를 입력하세요" />
      <button @click="submit">검색</button>
    </div>

    <div class="content">
      <!-- 검색된 장소 리스트 -->
      <div class="sidebar">
        <ul>
          <li 
            v-for="(marker, index) in markerList" 
            :key="index"
            @mouseover="hoverMarker(index)"
            @mouseleave="closeInfowindow"
            @click="clickMarker(index)"
          >
            <h4 @click = saveInfo(marker)>{{ marker.infoWindow.content }}</h4>
            <p>{{ marker.address }}</p>
            <p class="tel">{{ marker.phone }}</p>
          </li>
        </ul>
      </div>

      <!-- 지도 -->
      <div class="map-wrapper">
        <KakaoMap :lat="37.566826" :lng="126.9786567" @onLoadKakaoMap="onLoadKakaoMap">
          <KakaoMapMarker
            v-for="(marker, index) in markerList"
            :key="index"
            :lat="marker.lat"
            :lng="marker.lng"
            :infoWindow="marker.infoWindow"
            :clickable="true"
            @onClickKakaoMapMarker="onClickMapMarker(marker)"
          />
        </KakaoMap>
      </div>
    </div>
  </div>
</template>


<script setup>
  import {ref} from 'vue';
  import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
  
    const keyword = ref("역삼역 맛집");
    //라이브러리 사용 방법을 반드시 참고하여 주시기 바랍니다.
    const map = ref();
    const markerList = ref([]);
    const savedPlace = ref(null);

    let infowindow; // 직접 열고 닫을 인포윈도우 컨트롤

    // 클릭한 곳의 정보를, savedPlace 객체를 만들어 넣는다.
    const saveInfo = function(markerItem){
      savedPlace.value = {
        name: markerItem.infoWindow.content,
        lat: markerItem.lat,
        lng: markerItem.lng
      }

      console.log(savedPlace);
    }

    const submit = function(){
      if (!keyword.value || !map.value)
        return;

      markerList.value = []; // 기존 마커 제거
      const ps = new kakao.maps.services.Places();
      ps.keywordSearch(keyword.value, placesSearchCB);

      keyword.value = ''; 

    }

    const onLoadKakaoMap = (mapRef) => {
      map.value = mapRef;
      infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      // 장소 검색 객체를 생성합니다
      const ps = new kakao.maps.services.Places();
      // 키워드로 장소를 검색합니다
      ps.keywordSearch(keyword.value, placesSearchCB);
    };

    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    const placesSearchCB = (data, status) => {
      if (status === kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        const bounds = new kakao.maps.LatLngBounds();

        for (let marker of data) {
          const markerItem = {
            lat: marker.y,
            lng: marker.x,
            infoWindow: {
              content: marker.place_name,
              visible: false
            }
          };
          console.log(markerItem);
          //console.log(marker);
          markerList.value.push(markerItem);
          bounds.extend(new kakao.maps.LatLng(Number(marker.y), Number(marker.x)));
        }

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.value?.setBounds(bounds);
      }
    };

    //마커 클릭 시 인포윈도우의 visible 값을 반전시킵니다
    const onClickMapMarker = (markerItem) => {
      if (markerItem.infoWindow?.visible !== null && markerItem.infoWindow?.visible !== undefined) {
        markerItem.infoWindow.visible = !markerItem.infoWindow.visible;
      } else {
        markerItem.infoWindow.visible = true;
      }
    };

    // 리스트에서 마커 클릭 시 인포윈도우 열기
    const clickMarker = (index) => {
      const markerData = markerList.value[index];
      const position = new kakao.maps.LatLng(markerData.lat, markerData.lng);
      infowindow.setContent(`<div style="padding:5px;">${markerData.infoWindow.content}</div>`);
      infowindow.open(map.value, new kakao.maps.Marker({ position }));
    };

    // 리스트 hover 시 인포윈도우 표시
    const hoverMarker = (index) => {
      const markerData = markerList.value[index];
      const position = new kakao.maps.LatLng(markerData.lat, markerData.lng);
      infowindow.setContent(`<div style="padding:5px;">${markerData.infoWindow.content}</div>`);
      infowindow.open(map.value, new kakao.maps.Marker({ position }));
    };

    const closeInfowindow = () => {
      infowindow.close();
    };

</script>


<style>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.title {
  text-align: center;
  margin-bottom: 10px;
}

.search-bar {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 8px;
  margin-bottom: 10px;
}

.search-bar input {
  padding: 8px 12px;
  width: 250px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.search-bar button {
  padding: 6px 12px;
  font-size: 14px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.search-bar button:hover {
  background-color: #45a049;
}

.content {
  display: flex;
  width: 100%;
  max-width: 1100px;
  /* gap 제거해서 지도와 리스트 붙이기 */
}

.sidebar {
  width: 280px;
  max-height: 70vh;
  overflow-y: auto;
  background: #f9f9f9;
  padding: 10px;
  border-radius: 8px 0 0 8px;
  box-shadow: 1px 0 5px rgba(0,0,0,0.1);
  /* 마진 제거 */
}

.map-wrapper {
  flex: 1;
  height: 70vh;
  /* 왼쪽 라운드 제거 */
  border-radius: 0 8px 8px 0;
  overflow: hidden;
}

.sidebar li {
  border-bottom: 1px solid #ccc;
  padding: 10px;
  cursor: pointer; /* ✅ 마우스 모양 변경 */
  transition: background-color 0.2s;
}

.sidebar li:hover {
  background-color: #e6f7ff;
}


</style>