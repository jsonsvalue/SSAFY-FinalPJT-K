
import App from './App.vue'
import router from './router'
import { createApp } from 'vue'
import { createBootstrap } from 'bootstrap-vue-next'

import './assets/main.css'
import './assets/style.css'

const app = createApp(App)
app.use(router)
app.use(createBootstrap())
app.mount('#app')
