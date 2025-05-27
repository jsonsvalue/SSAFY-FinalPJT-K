<template>
    <div class="container">
        <div class="outline">
            <h3>냠냠 수정</h3>
            <div class="write-form">
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
            <ArticleMapComp></ArticleMapComp>
            <div class="button-box">
                <BButton variant="primary" @click="saveArticle">저장</BButton>
                <BButton variant="secondary" @click="$router.push('/')">취소</BButton>
            </div>
        </div>
    </div>
</template>

<script setup>
    import ArticleMapComp from '@/components/article/ArticleMapComp.vue';
    import { BButton,BInputGroup,BFormText,BInputGroupText,BFormTextarea } from 'bootstrap-vue-next';
    import { onMounted, ref, watch } from 'vue';
    import ImageUploadComp from '@/components/common/ImageUploadComp.vue';
    import ArticleWriteDetailComp from '@/components/article/ArticleWriteDetailComp.vue';
    import axios from 'axios';
    import { useRouter,useRoute} from 'vue-router';
    const router = useRouter();
    const url = import.meta.env.VITE_API_URL + '/article';
    const imageUrl = import.meta.env.VITE_IMAGE_URL;
    const route = useRoute();
    const articleId = route.params.id;
    const article = ref({
        id: null,
        userId: null,
        type: 'eat',
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

    const saveArticle = () => {
        const params = {
            article: article.value,
            subArticle: subArticle.value
        }
        axios.patch(url,params)
        .then(res => {
            console.log(res)
            router.push(`/article/${article.value.id}`);
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

    onMounted(async () => {
        if (route.query.comment) {
            setTimeout(() => {
                toComment();
            }, 300);
        }
        try {
            const response = await axios.get(url + '/' + articleId);
            response.data.article.imageUrl = imageUrl + response.data.article.imageUrl;
            article.value = response.data.article;
            response.data.subArticle.forEach((sub) => {
                if (sub.imageUrl) {
                    sub.imageUrl = imageUrl + sub.imageUrl;
                } else {
                    sub.imageUrl = null;
                }
            });
            response.data.comment.forEach((comm) => {
                if (comm.imageUrl) {
                    comm.imageUrl = imageUrl + comm.imageUrl;
                } else {
                    comm.imageUrl = null;
                }
            });
            subArticle.value = response.data.subArticle;
        } catch (error) {
            console.error('Error fetching articles:', error);
            alert('유효하지 않은 접근입니다.');
            router.push('/');
        }
    });
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
