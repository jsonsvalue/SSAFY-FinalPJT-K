<template>
    <div class="background">
        <template v-for="article in articleList"  :key="article.id">
            <ArticleFeedComp :article="article"></ArticleFeedComp>
        </template>
    </div>
    <div class="end">
        <p v-if="isEnd">더이상 불러올 게시물이 없습니다</p>
    </div>
</template>

<script setup>
    import ArticleFeedComp from '@/components/article/ArticleFeedComp.vue'
    import axios from 'axios';
    import { ref,onBeforeMount, onMounted } from 'vue';
    const articleList = ref([]);
    const url = import.meta.env.VITE_API_URL;
    const imageUrl = import.meta.env.VITE_IMAGE_URL;
    const isEnd = ref(false);
    const isPending = ref(false);
    const top = ref(0);
    const refreshFeed = async () => {
        if (isEnd.value || isPending.value) return;
        if (document.documentElement.scrollHeight - window.scrollY - window.innerHeight >= 500) return;
        isPending.value = true;
        try {
            const response = await axios.get(url + '/feed', {
                params: {
                    top: top.value
                }
            });
            if (response.data.length === 0) {
                isEnd.value = true;
            } else {
                response.data.forEach((article) => {
                    article.imageUrl = imageUrl + article.imageUrl;
                });
                articleList.value.push(...response.data);
                top.value = articleList.value[articleList.value.length - 1]?.id || 0;
            }
        } catch (error) {
            console.error('Error fetching articles:', error);
        } finally {
            isPending.value = false;
        }
    };
    onMounted(async () => {
        try {
            const response = await axios.get(import.meta.env.VITE_API_URL + '/feed', {
                params: {
                    top: 0
                }
            });
            response.data.forEach((article) => {
                article.imageUrl = imageUrl + article.imageUrl;
            });
            articleList.value = response.data;
            top.value = articleList.value[articleList.value.length - 1]?.id || 0;
        } catch (error) {
            console.error('Error fetching articles:', error);
        }
        window.addEventListener('scroll', refreshFeed, { passive: true });
    });
</script>

<style scoped>
    .end {
        text-align: center;
        margin-top: 50px;
        font-size: 1.2em;
        color: #888;
        margin-bottom: 100px;
    }
</style>   