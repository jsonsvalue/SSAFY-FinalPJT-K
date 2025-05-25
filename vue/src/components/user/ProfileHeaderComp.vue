<template>

    <div class="d-flex justify-content-between align-items-center mb-3">
        <!-- <span class="text-muted small">메모...</span> -->
        <h5 v-if = "store.userInfo" class="mb-0 font-weight-bold">
            {{store.userInfo.userId}}
        </h5>
        
        <div class="d-flex align-items-center">
            <router-link :to = "`/mypage`">
                <b-button variant="outline-secondary" size="sm" class="me-2">
                    프로필 편집
                </b-button>
           </router-link>
          
           <!-- <b-button variant="outline-secondary" size="sm" class="me-2">
            메시지 보내기
          </b-button> -->
          
          <b-button variant="link" class="p-0">
            <i class="fas fa-cog"></i>
          </b-button>
        </div>

      </div>
</template>

<script setup>
    import { BButton, BTabs, BTab, BRow, BCol } from 'bootstrap-vue-next';
    import { useRoute } from 'vue-router';
    import { useUserStore } from '@/stores/user';
    import { ref, onMounted} from 'vue';
    
    const route = useRoute();
    const userId = ref('');

    const store = useUserStore();
    onMounted(()=>{
        userId.value = route.params.userId;
        store.getProfileInfo(userId.value);
    })
</script>

<style lang="scss" scoped>

</style>