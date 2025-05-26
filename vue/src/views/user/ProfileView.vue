<template>
    <div class="container-fluid profile-container">
    <!-- Header -->
    <div class="profile-header p-3">
      
      <ProfileHeaderComp></ProfileHeaderComp>
      
      <!-- Profile Info -->
      <div class="d-flex align-items-start mb-3">
        <div class="profile-image-container me-3">
          <img src="https://hebbkx1anhila5yf.public.blob.vercel-storage.com/image-Fmnf1bpoTxrbACamfenrAD1Z8Y66np.png" alt="Profile" class="profile-image">
        </div>
        
        <div class="flex-grow-1">
          <div class="d-flex justify-content-around text-center mb-3">
            <div>
              <div v-if = "articleStore.articleList" class="font-weight-bold">
                {{ articleStore.articleList.length ? articleStore.articleList.length : 0 }}
              </div>
              <small class="text-muted">게시물</small>
            </div>
            <div>
              
              <div v-if = "followStore.followList" class="font-weight-bold">
                {{followStore.followList.length ? followStore.followList.length : 0}}
              </div>
              <small class="text-muted">
                팔로워
              </small>

            </div>
            <div>
              <div v-if = "followStore.followerList"class="font-weight-bold">
                {{followStore.followerList.length ? followStore.followerList.length : 0}}
              </div>
              <small class="text-muted">
                팔로우
               </small>
            </div>
          </div>
        </div>

      </div>

      <!-- User Details -->
      <div class="mb-3">
        <h6 v-if = "userStore.userInfo" class="mb-1 font-weight-bold">
            {{userStore.userInfo.name}}
        </h6>
        <small v-if = "userStore.userInfo" class="text-muted">
            {{userStore.userInfo.intro}}
        </small>
      </div>

      <!-- Story Highlights -->
      <!-- <div class="d-flex align-items-center mb-4">
        <div class="story-highlight me-3">
          <div class="story-circle d-flex align-items-center justify-content-center">
            <i class="fas fa-plus text-muted"></i>
          </div>
          <small class="d-block text-center mt-1 text-muted">신규</small>
        </div>
      </div> -->

    </div>

    <!-- Tabs -->
    <div class="profile-tabs">
      <b-tabs content-class="mt-3" justified>
        <b-tab active @click = "selectedType = 'recipe'">
          <template #title>
            <i class="fas fa-th me-1"></i>
            레시피
          </template>
        </b-tab>

        <b-tab @click = "selectedType = 'eat'">
          <template #title>
            <i class="far fa-bookmark me-1"></i>
            식당
          </template>
        </b-tab>
        
        <!-- <b-tab>
          <template #title>
            <i class="fas fa-user-tag me-1"></i>
            태그됨
          </template>
        </b-tab> -->

      </b-tabs>
    </div>

    <!-- Photo Grid -->
    <div class="photo-grid p-1">
      <b-row no-gutters>
        <b-col 
            v-for = "(article, index) in filteredArticleList"
            :key = article.id
            cols="4"
            class="p-1">
        >    
            <!-- 
              @click="articleStore.selectArticle(article.id)"
              "
              -->
            <RouterLink :to="{ name: 'ArticleMaster', params: { id: article.id } }">
              <div class="photo-item">
                <img 
                :src="`${imageUrl}${article.imageUrl}`"
                :alt= "`게시물 ${index + 1}`" 
                class="w-100"
                style = "object-fit:cover"
                >
                <div><strong>ID:</strong> {{ article.id }}</div>
                <div><strong>User:</strong> {{ article.userId }}</div>
                <div><strong>Type:</strong> {{ article.type }}</div>
                <div><strong>Image:</strong> {{ article.imageUrl }}</div>
                <div><strong>Content:</strong> {{ article.content }}</div>
            </div>
            </RouterLink>

        </b-col>
        
      </b-row>
    </div>
  </div>

    <!-- <div>
        <h1>
            Profile View
        </h1>
        
        <div>{{ userId }}</div>
        
    </div> -->
</template>

<script setup>
    import ProfileHeaderComp from '@/components/user/ProfileHeaderComp.vue';
    import { useRoute } from 'vue-router';
    import { useUserStore } from '@/stores/user';
    import { useFollowStore } from '@/stores/follow';
    import { useArticleStore} from '@/stores/article';
    import { ref, onMounted, computed } from 'vue';
    import { BButton, BTabs, BTab, BRow, BCol } from 'bootstrap-vue-next';

    const imageUrl = import.meta.env.VITE_IMAGE_URL;
    const route = useRoute();
    const userId = ref('');

    // 페이지가 rendering 되면서 Path에 있는 userInfo 정보를 가져온다.
    // 그리고 Path에 있는 정보를 getProfileInfo를 통해 GET 요청을 보낸다.
    const userStore = useUserStore();
    const followStore = useFollowStore();
    const articleStore = useArticleStore();
    onMounted(()=>{
        userId.value = route.params.userId;
        userStore.getProfileInfo(userId.value);
        followStore.getFollowList(userId.value);
        followStore.getFollowerList(userId.value);
        articleStore.getArticleList(userId.value);
    });

    // 레시피, 식당 종류에 따른 게시물 보이게 하기
    // selectedType에 해당하는 article만을 보이게 한다.
    const selectedType = ref('recipe');
    const filteredArticleList = computed(()=>{
        return articleStore.articleList?.filter(article => selectedType.value === article.type)|| [];
    })
    

</script>

<style scoped>
    .profile-container {
  max-width: 50%;
  margin: 0 auto;
  background: white;
  min-height: 100vh;
  padding: 1rem;
}

.profile-image-container {
  width: 80px;
  height: 80px;
}

.profile-image {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  object-fit: cover;
  border: 2px solid #e1e1e1;
}

.story-highlight {
  text-align: center;
}

.story-circle {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  border: 2px dashed #ccc;
  background: #f8f9fa;
}

.profile-tabs {
  border-top: 1px solid #e1e1e1;
}

.profile-tabs .nav-tabs {
  border-bottom: none;
}

.profile-tabs .nav-link {
  color: #8e8e8e;
  border: none;
  font-size: 14px;
  padding: 12px 8px;
}

.profile-tabs .nav-link.active {
  color: #262626;
  border-bottom: 1px solid #262626;
  background: none;
}

.photo-grid {
  background: white;
}

.photo-item {
  aspect-ratio: 1;
  overflow: hidden;
  background: #f8f9fa;
}

.photo-item img {
  height: 100%;
  object-fit: cover;
  cursor: pointer;
  transition: opacity 0.2s;
}

.photo-item img:hover {
  opacity: 0.8;
}

/* Mobile responsive adjustments */
@media (max-width: 576px) {
  .profile-header {
    padding: 1rem !important;
  }
  
  .profile-image-container {
    width: 70px;
    height: 70px;
  }
  
  .profile-image {
    width: 70px;
    height: 70px;
  }
  
  .story-circle {
    width: 50px;
    height: 50px;
  }
}

/* Custom button styles */
.btn-outline-secondary {
  border-color: #dbdbdb;
  color: #262626;
  font-size: 14px;
  font-weight: 600;
  padding: 5px 12px;
}

.btn-outline-secondary:hover {
  background-color: #f8f9fa;
  border-color: #dbdbdb;
  color: #262626;
}

/* Tab content styling */
.tab-content {
  border: none;
}

/* Stats styling */
.font-weight-bold {
  font-weight: 600 !important;
}

/* Text styling */
h5, h6 {
  color: #262626;
}

.text-muted {
  color: #8e8e8e !important;
}
</style>