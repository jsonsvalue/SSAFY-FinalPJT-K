<template>

    <div v-if = "loginUser.userId === userIdRoute" class="d-flex justify-content-between align-items-center mb-3">
        <!-- <span class="text-muted small">메모...</span> -->
        <!-- Session 정보 != Router정보 일때 팔로잉, 메시지 기능을 보여준다. -->
        <h5 v-if = "userStore.userInfo" class="mb-0 font-weight-bold">
            {{userStore.userInfo.userId}}
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
    <div v-else class="d-flex justify-content-between align-items-center mb-3">
        <h5 v-if = "userStore.userInfo" class="mb-0 font-weight-bold">
            {{userStore.userInfo.userId}}
        </h5>

        <div class="d-flex align-items-center">
            <b-button @click = "toggleFollow" 
            :variant=" isFollowing ? 'outline-secondary' : 'outline-primary'" 
            size="sm" 
            class="me-2">
                {{ !isFollowing ? '팔로우' : '팔로잉' }}
            </b-button>

            <!-- <b-button variant="outline-secondary" size="sm" class="me-2">
                메시지 보내기
            </b-button> -->
        </div>
        
    </div>



</template>

<script setup>
    import { BButton, BTabs, BTab, BRow, BCol } from 'bootstrap-vue-next';
    import { useRoute } from 'vue-router';
    import { useUserStore } from '@/stores/user';
    import { useFollowStore } from '@/stores/follow';
    import { ref, onMounted, computed} from 'vue';

    // 로그인한 이용자 정보
    const loginUser = ref(JSON.parse(sessionStorage.getItem("user")));

    // 검색한 사용자에 대한 정보
    const route = useRoute();
    const userIdRoute = ref('');

    // 사용자의 user 및 follow 정보를 가져온다.
    const userStore = useUserStore();
    const followStore = useFollowStore();
    onMounted(()=>{
        userIdRoute.value = route.params.userId;
        userStore.getProfileInfo(userIdRoute.value);
        followStore.getFollowList(userIdRoute.value.userId);
        followStore.getFollowerList(userIdRoute.value.userId);
    })

    // 상태 변수를 이용한 follow 처리.
    // followerUser와 loginUserId가 같으면 isFollowing = true로 처리한다.
    const isFollowing = computed(()=>{
        return followStore.followerList?.some(
            (followerUser) => followerUser.userId === loginUser.value.userId
        )
    });

    // isFollowing 값에 따라 처리하는 함수를 다르게 한다.
    const toggleFollow = async()=>{
        const userId = loginUser.value.userId;
        const profileId = userIdRoute.value;

        if( !isFollowing.value ){
            const success = await followStore.followUser(userId, profileId);
            
            if(success){
                followStore.followerList.push({userId : userId})
            }
        }else{
            const success = await followStore.unfollowUser(userId, profileId);
            
            if(success){
                followStore.followerList = followStore.followerList.filter(
                    (user) => user.userId !== userId
                );

            }
        }

        await followStore.getFollowerList(profileId);
    }

    /* 
        const followStore = useFollowStore();
        const follow = function(){
            // console.log(loginUser.value.userId, userIdRoute.value);
            followStore.followUser(loginUser.value.userId, userIdRoute.value);
        }

        const unfollow = function(){
            // console.log(loginUser.value.userId, userIdRoute.value);
            followStore.unfollowUser(loginUser.value.userId, userIdRoute.value);
        }
    */

</script>

<style scoped>

</style>