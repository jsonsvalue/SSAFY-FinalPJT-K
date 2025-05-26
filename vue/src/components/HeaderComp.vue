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
                        <img src="../assets/img/hyunwook.png" style="height: 100%; border-radius: 50%;"/>
                        <p style="font-size: large; margin: 0px 10px 0px;">들들볶는현욱</p>
                    </div>
                </div>
            </div>
        </div>
    </header>
</template>

<script setup>
    import { BInputGroup,BFormInput,BButton,BFormSelect,BModal } from 'bootstrap-vue-next';
    import { onBeforeMount, ref } from 'vue';
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
    
    
</script>

<style scoped>
    #buttonBox>a {
        height: 50%;
    }
    #buttonBox>a>img {
        height: 100%;
    }
</style>