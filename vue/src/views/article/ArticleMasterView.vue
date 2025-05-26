<template>
    <div class="container">
        <div class="outline">
            <div class="profile">
                <img src="../../assets/img/DeepSleepCho.jpg" style=""/>
                <div class="name-box">
                    <p class="name">DeepMindSuin</p>
                    <p class="category" v-if="article.type=='eat'">냠냠</p>
                    <p class="category" v-else>레시피</p>
                </div>
            </div>
            <div class="img-box">
                <img :src="article.imageUrl" alt="Article Image"/>
            </div>
            <div class="content-box" v-if="article.content">
                <pre>{{ article.content }}</pre>
            </div>
            <div class="detail-box">
                <template v-for="sub in subArticle" :key="sub.id">
                    <ArticleDetailComp :sub="sub"></ArticleDetailComp>
                </template>
            </div>
            <div class="button-box">
                <a class="fav" href="">
                    <img src="../../assets/icon/heart-fill.svg"/>
                </a>
                <span>20</span>
                <a class="fav" href="">
                    <img src="../../assets/icon/heart.svg"/>
                </a>
                <span>20</span>
                <a href=""><img src="../../assets/icon/comment.svg"/></a>
                <a onclick=""><img src="https://www.svgrepo.com/show/512829/share-1100.svg"/></a>
            </div>
        </div>
        <ArticleCommentComp :article-id="Number(articleId)" :comment="comment"></ArticleCommentComp>
    </div>
</template>

<script setup>
    import axios from 'axios';
    import { ref,onMounted } from 'vue';
    import { useRouter,useRoute } from 'vue-router';
    import ArticleDetailComp from '@/components/article/ArticleDetailComp.vue';
    import ArticleCommentComp from '@/components/article/ArticleCommentComp.vue';
    const article = ref({});
    const subArticle = ref([]);
    const comment = ref([]);
    const url = import.meta.env.VITE_API_URL;
    const imageUrl = import.meta.env.VITE_IMAGE_URL;
    const router = useRouter();
    const route = useRoute();
    const articleId = route.params.id;
    onMounted(async () => {
        try {
            const response = await axios.get(url + '/article/' + articleId);
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
            comment.value = response.data.comment;
        } catch (error) {
            console.error('Error fetching articles:', error);
            alert('유효하지 않은 접근입니다.');
            router.push('/');
        }
    });
</script>

<style scoped>

    .container {
        padding-top: 50px;
        padding-bottom: 100px;
    }
    .outline {
        border: 1px solid #EEE;
        border-radius: 5px;
        padding: 15px;
        box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
        -webkit-box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
        -moz-box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
        display: flex;
        flex-direction: column;
    }

    .content-box {
        margin-top: 10px;
        display: flex;
        border-radius: 15px;
        padding: 10px;
        border: #AAA solid 1px;
        margin-bottom: 50px;
    }
</style>   