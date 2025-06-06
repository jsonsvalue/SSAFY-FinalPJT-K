import ArticleFeedView from '@/views/article/ArticleFeedView.vue'
import ArticleWriteNyamView from '@/views/article/ArticleWriteNyamView.vue'
import ArticleWriteRecipeView from '@/views/article/ArticleWriteRecipeView.vue'
import MainView from '@/views/common/MainView.vue'
import SampleView from '@/views/SampleView.vue'
import LoginView from '@/views/user/LoginView.vue'
import RegistView from '@/views/user/RegistView.vue'
import ArticleMasterView from '@/views/article/ArticleMasterView.vue'
import ArticleSearchView from '@/views/article/ArticleSearchView.vue'
import ArticleEditNyamView from '@/views/article/ArticleEditNyamView.vue'
import ArticleEditRecipeView from '@/views/article/ArticleEditRecipeView.vue'
import { createRouter, createWebHistory } from 'vue-router'

import MyPageView from '@/views/user/MyPageView.vue'
import ProfileView from '@/views/user/ProfileView.vue'


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
      ]
    },
    {
      path: '/article/:id',
      name: 'ArticleMaster',
      component: ArticleMasterView,
      
    },
    {
      path: '/article/:id/edit-nyam',
      name: 'ArticleEditNyam',
      component: ArticleEditNyamView,
    },
    {
      path: '/article/:id/edit-recipe',
      name: 'ArticleEditRecipe',
      component: ArticleEditRecipeView,
    },
    {
      path: '/search',
      name: 'ArticleSearch',
      component: ArticleSearchView,
    },
    {
      path: '/write/nyam',
      name: 'ArticleWriteNyam',
      component: ArticleWriteNyamView,
    },
    {
      path: '/write/recipe',
      name: 'ArticleWriteRecipe',
      component: ArticleWriteRecipeView,
    },
    {
      path: '/sample',
      name: 'sample',
      component: SampleView,
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,
    },
    {
      path: '/regist',
      name: 'regist',
      component: RegistView,
    },
    {
      path: '/profile/:userId',
      name: 'profile',
      component: ProfileView,
    }
    ,
    {
      path: '/myPage',
      name: 'myPage',
      component: MyPageView,
    }

  ],
  scrollBehavior(to, from, savedPosition) {
    // 항상 최상단으로 스크롤.
    return { top: 0 }
  },
})

export default router
