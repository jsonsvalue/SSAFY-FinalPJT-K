<template>
  <div class="h-100 d-flex flex-column">
    <HeaderComp/>
    <RouterView/>
    <ImageModalComp ref="imageModal"/>
  </div>
</template>

<script setup>
  import axios from "axios";
  import { onBeforeMount,ref,onMounted } from 'vue';
  import HeaderComp from './components/HeaderComp.vue';
  import ImageModalComp from './components/common/ImageModalComp.vue';
  import router from './router';
  import { useImageStore } from './stores/image.js';

  const REST_API_URL = import.meta.env.VITE_API_URL;

  const image = useImageStore();
  const imageModal = ref(null);

  onMounted(() => {
    image.setModal(imageModal.value);
  });

  // 비동기 함수를 선언하는 asyc를 이용하면 promise를 반환한다.
  // 그래서 await를 써서 promise가 반환될때까지 기다리면, 비동기 함수를 동기함수처럼 쓸 수 있는 것이다.
  onBeforeMount(async() => {
    // axios 동기로 호출해서 결과를 받고 나서 화면을 그리도록 한다.
    // 동기를 호출해야 로그인 했는지 여부에 따라,
    // 서로 다른 페이지를 보여줄 수 있기 때문이다.
    
    // CORS를 적절히 처리하고 나서 withCredentials:true를 해야,
    // Cookie 내 JSessionID를 이용해서 서버의 Session 정보를 제대로 확인할 수 있다.
    try{
      const response = await axios.get(`${REST_API_URL}/getUserInfo`,{
        withCredentials : true
      });
      console.log("App.vue", response.data);
      
      router.push('');
    }catch(error){
      console.log(error);

      router.push('/login');
    }

  });

  
</script>

<style module>

</style>