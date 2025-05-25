<template>
    <div class="container">
        <div class="outline">
            <h3>냠냠 작성</h3>
            <div class="write-form">
                <BInputGroup class="mb-3">
                    <BInputGroupText>제목</BInputGroupText>
                    <BFormInput v-model="article.title" placeholder="제목을 입력하세요" />
                </BInputGroup>
                <BInputGroup class="mb-3 content">
                    <BInputGroupText>내용</BInputGroupText>
                    <BFormTextarea v-model="article.content" placeholder="내용을 입력하세요" />
                </BInputGroup>
            </div>
            <ImageUploadComp style="height: 500px;" :image="article"/>
            <div v-for="(sub, index) in subArticle" :key="index">
                <ArticleWriteDetailComp :subArticle="sub" @on-delete="onDetailDelete" @on-move-up="onMoveUp" @on-move-down="onMoveDown"/>
            </div>
            <div class="addSub">
                <BButton variant="outline-primary" @click="addSubArticle">추가</BButton>
            </div>
            <div class="button-box">
                <BButton variant="primary" @click="submitArticle">작성하기</BButton>
                <BButton variant="secondary" @click="$router.push('/article')">취소</BButton>
            </div>
            
            <ArticleMapComp></ArticleMapComp>
        </div>
    </div>
</template>

<script setup>
    import ArticleMapComp from '@/components/article/ArticleMapComp.vue';
    import { BFormInput, BButton,BInputGroup,BFormText,BInputGroupText,BFormTextarea } from 'bootstrap-vue-next';
    import { ref, watch } from 'vue';
    import ImageUploadComp from '@/components/common/ImageUploadComp.vue';
    import ArticleWriteDetailComp from '@/components/article/ArticleWriteDetailComp.vue';
    import axios from 'axios';

    const url = import.meta.env.VITE_API_URL + '/article';

    const article = ref({
        id: null,
        userId: null,
        type: 'eat',
        title: '',
        content: '',
        ingredient: '',
        imageId: null,
        imageUrl: null
    });
    const subArticle = ref([]);

    const addSubArticle = () => {
        subArticle.value.push({
            id:null,
            article_id:null,
            content: '',
            imageId:null,
            order: subArticle.value.length+1,
            imageUrl: null
        });
        orderSubArticle();
    }

    const orderSubArticle = () => {
        subArticle.value.sort((a, b) => a.order - b.order);
        subArticle.value.forEach((sub, index) => {
            sub.order = index + 1;
        });
    }

    const submitArticle = () => {
        const params = {
            article: article.value,
            subArticle: subArticle.value
        }
        axios.post(url,params)
        .then(res => {
            console.log(res)
        })
        .catch(err => {
            console.error(err); 
        })
    }
    /* 이벤트 리스너 */
    const onDetailDelete = (order) => {
        subArticle.value = subArticle.value.filter(sub => sub.order !== order);
        orderSubArticle();
    }

    const onMoveUp = (order) => {
        const index = subArticle.value.findIndex(sub => sub.order === order);
        if (index > 0&& index < subArticle.value.length) {
            const temp = subArticle.value[index].order;
            subArticle.value[index].order = subArticle.value[index - 1].order;
            subArticle.value[index - 1].order = temp;
            orderSubArticle();
        }
    }

    const onMoveDown = (order) => {
        const index = subArticle.value.findIndex(sub => sub.order === order);
        if (index >= 0 && index < subArticle.value.length - 1) {
            const temp = subArticle.value[index].order;
            subArticle.value[index].order = subArticle.value[index + 1].order;
            subArticle.value[index + 1].order = temp;
            orderSubArticle();
        }
    }
</script>

<style scoped>

    .outline {
        border: 1px solid #EEE;
        border-radius: 5px;
        padding: 15px;
        box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
        -webkit-box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
        -moz-box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
        display: flex;
        flex-direction: column;
        margin-top: 30px;
    }

    .content {
        height: 300px;
    }

    .content textarea {
        resize: none;
    }
    
    .button-box {
        display: flex;
        justify-content: flex-end;
        margin-top: 30px;
        margin-bottom: 50px;
    }

    .button-box button {
        margin-left: 10px;
    }

    .addSub button {
        width: 100%;
        height: 50px;
        margin-top: 10px;
    }
</style>
