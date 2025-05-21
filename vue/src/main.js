
import App from './App.vue'
import { createApp } from 'vue'
import { createBootstrap } from 'bootstrap-vue-next'

import './assets/main.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-next/dist/bootstrap-vue-next.css'

import {useKakao} from 'vue3-kakao-maps/@utils';

// appKey를 이용해서, services라이브러리를 다운 받는다.
const appKey = import.meta.env.VITE_KAKAO_MAP_API_KEY;
useKakao(appKey, ['services']);

const app = createApp(App)
app.use(createBootstrap())
app.mount('#app')
