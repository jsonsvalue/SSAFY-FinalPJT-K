<template>
    <div>
        <h2>Feed View vue</h2>
        <template v-for="article in articleList"  :key="article.id">
            <ArticleFeedComp :article="article"></ArticleFeedComp>
        </template>
        
    </div>
</template>

<script setup>
    import ArticleFeedComp from '@/components/article/ArticleFeedComp.vue'

    import axios from 'axios';
    import { ref,onBeforeMount, onMounted } from 'vue';
    const articleList = ref([]);
    const url = import.meta.env.VITE_API_URL;
    const imageUrl = import.meta.env.VITE_IMAGE_URL;
    onMounted(async () => {
        try {
            const response = await axios.get(import.meta.env.VITE_API_URL + '/feed');
            response.data.forEach((article) => {
                article.imageUrl = imageUrl + article.imageUrl;
            });
            articleList.value = response.data;
        } catch (error) {
            console.error('Error fetching articles:', error);
        }
    });
</script>

<style scoped>

</style>   