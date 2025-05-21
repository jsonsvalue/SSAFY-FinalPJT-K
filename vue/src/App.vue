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
  
</script>

<style module>

</style>