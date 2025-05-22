<template>
  <div class="h-100 d-flex flex-column">
    <HeaderComp/>
    <RouterView/>
  </div>
</template>

<script setup>
  import HeaderComp from './components/HeaderComp.vue';
  import SampleView from './views/SampleView.vue';
  import {createRouter,createMemoryHistory} from 'vue-router'
  import { onBeforeMount } from 'vue';
  onBeforeMount(() => {
    // axios 동기로 호출해서 결과를 받고 나서 화면을 그리도록 한다.
  });
</script>

<style module>

</style>