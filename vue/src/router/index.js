import { createRouter, createWebHistory } from 'vue-router'
import MainView from '@/views/common/MainView.vue'
import SampleView from '@/views/SampleView.vue'
import ArticleFeedView from '@/views/article/ArticleFeedView.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainView,
      children: [
        {
          path: '',
          name: 'feed',
          component:ArticleFeedView
        },
        {
          // TODO 예시 컴포넌트. 대체 해야 함.
          path: 'login',
          component:ArticleFeedView
        },
      ]
    },
    {
      path: '/sample',
      name: 'sample',
      component: SampleView,
    },
  ]
})

export default router
