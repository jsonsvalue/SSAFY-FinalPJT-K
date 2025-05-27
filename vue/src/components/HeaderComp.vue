<template>
    <header class="text-bg-primary sticky-top justify-content-center" style="height: 80px;">
        <div class="pt-3 pb-3 ps-5 pe-5">
            <div class="row align-items-center">
                <a id="homeIcon" href="/" class="d-block w-auto h-100 no-decoration">
                    <div class="bg-white rounded-4 h-100 d-flex align-items-center ps-2 pe-2">
                        <img src="../assets/img/nyamnyam.png" style="height: 50px;"/>
                        <span class="text-black ms-1 font3" style="font-size: 30px;">냠냠로드</span>
                    </div>
                </a>
                <div id="searchBox" class="col-5 position-absolute translate-middle-x" style="left:50%">
                    <BInputGroup class="w-100">
                        <BFormSelect v-model="searchOption" :options="options" style="flex: 0 0 150px;"></BFormSelect>
                        <BFormInput/>
                        <BButton class="btn-success" @click='search'><img src="../assets/icon/search.svg" alt="search" width="30px"></BButton>
                    </BInputGroup>
                </div>
                <div id="buttonBox" class="position-absolute col-3 h-100 d-flex align-items-center" style="right: 0%;">
                    <a><img src="../assets/icon/heart.svg"/></a>
                    <!-- <a class="h-50"><img src="../assets/icon/user.svg" class="h-100"/></a> -->
                     <!-- 예시 컴포넌트. 프로필 컴포넌트로 대체해야 함. -->
                    <div style="height: 60%; display: flex; align-items: center; margin-left: 10px;">
                        <!-- <img :src="`${image_url}${user.imageUrl}`" 
                        style="height: 100%; 
                        border-radius: 50%;"/> -->
                        <div style = "width : 50px; height:50px;">
                            <ProfileImageComp
                            :src = "user.imageUrl"
                            :user-id = "user.userId"
                            >
                            </ProfileImageComp>
                        </div>
                        
                        
                        <p style="font-size: large; margin: 0px 10px 0px">
                            {{user.userId}}
                        </p>

                        <b-button variant = "secondary" class = "btnClass" @click = "logout">
                            로그아웃
                        </b-button>

                    </div>
                </div>
            </div>
        </div>
    </header>
</template>

<script setup>
    import ProfileImageComp from '@/components/user/ProfileImageComp.vue';
    import { BInputGroup,BFormInput,BButton,BFormSelect, BFormSelectOption } from 'bootstrap-vue-next';
    import { onBeforeMount, onMounted, ref } from 'vue';
    import { useUserStore } from '@/stores/user';
    import { useRouter} from 'vue-router';
    import router from '@/router';
    import axios from 'axios';

    const userStore = useUserStore();

    onMounted(()=>{
        userStore.getUserInfo();
    })

    const image_url = import.meta.env.VITE_IMAGE_URL;
    const user = ref(JSON.parse(sessionStorage.getItem("user")))

    const searchOption = ref('recipe');
    const options = ref([
        { value: 'recipe', text: '레시피' },
        { value: 'nyam', text: '냠냠' },
        { value: 'user', text: '사용자' }
    ]);

    const search = () => {
        // 검색 로직 구현
        console.log(`Searching for ${searchOption.value}`);
    }

    const logout = async function(){
        try{
            // 어차피 Session에 있는 정보를 없애고, 쿠키에서 JSessionId를 가져오는 처리를 해주면 된다.
            await axios.post('/logout', {}, {withCredentials:true});
            
            console.log(response.data);
        }
        catch{
            console.log('로그아웃 실패');
        }


        router.push("/login");
    }
    
    
</script>

<style scoped>
    #buttonBox>a {
        height: 50%;
    }
    #buttonBox>a>img {
        height: 100%;
    }

    .btnClass{
        padding: 6px 12px;
        font-size: 16px;
        border: none;
        border-radius: 4px;
        cursor: pointer;

    } 
</style>