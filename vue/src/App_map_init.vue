<template>
  <div>
    <p>hello vue</p>
  </div>

  <br>
  
  <KakaoMap :lat="coordinate.lat" :lng="coordinate.lng" :draggable="true">
    <KakaoMapMarker :lat="coordinate.lat" :lng="coordinate.lng"></KakaoMapMarker>
  </KakaoMap>
  

</template>

<script setup>
  import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
  const coordinate = {
    lat: 37.5012860931305,
    lng: 127.039604663862
  };

</script>

<style module>

</style>