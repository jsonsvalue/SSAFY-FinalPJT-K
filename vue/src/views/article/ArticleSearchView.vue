<template>
    <div class="background" @scrollend="scrollEvent">
        <div class="search-header">
            <h1>검색 결과</h1>
            <p>검색어: {{ keyword }}</p>
            <p>타입: {{ type }}</p>
        </div>
        <template v-for="article in searchResult"  :key="article.id">
            <ArticleFeedComp :article="article"></ArticleFeedComp>
        </template>
    </div>
</template>

<script setup>
    import axios from 'axios';
    import {useRoute} from 'vue-router';
    import { ref, onMounted } from 'vue';
    import router from '@/router';
    import ArticleFeedComp from '@/components/article/ArticleFeedComp.vue';
    const searchResult = ref([]);
    let apiUrl = import.meta.env.VITE_API_URL;
    const imageUrl = import.meta.env.VITE_IMAGE_URL;
    const top = ref(0)
    const isEnd = ref(false);
    const route = useRoute();
    const keyword = route.query.keyword;
    const type = route.query.type;
    if (!keyword || keyword.trim() === ''||['recipe', 'eat', 'user'].includes(type) === false) {
        router.push({ name: 'main' });
    }
    const isPending = ref(false);
    onMounted(async () => {
        try {
            isPending.value = true;
            if (type === 'recipe'||type === 'eat') {
                apiUrl += '/article/search';
            } else {
                // user search url
            }
            const response = await axios.get(apiUrl, {
                params: {
                    type: type,
                    keyword: keyword.trim().replace(/ /g, '+'),
                    top: top.value
                }
            });
            
            if (response.data.length == 0) {
                isEnd.value = true;
            } else {
                top.value = response.data[response.data.length - 1].id;
            }
            response.data.forEach((article) => {
                article.imageUrl = imageUrl + article.imageUrl;
            });
            searchResult.value = response.data;
            isPending.value = false;
        } catch (error) {
            console.error('Error fetching articles:', error);
        }
    });
    const scrollEvent = (event) => {
        console.log('scrollTop:', scrollTop, 'scrollHeight:', scrollHeight, 'clientHeight:', clientHeight);
        getNext();
    };
    const getNext = async () => {
        if (isEnd.value || isPending.value) return;
        try {
            isPending.value = true;
            console.log('Fetching next articles with top:', top.value);
            axios.get(apiUrl + '/search', {
                params: {
                    keyword: useRoute().query.keyword.trim().replace(/ /g, '+'),
                    top: top.value
                }
            }).then((response) => {
                if (response.data.length == 0) {
                    isEnd.value = true;
                } else {
                    top.value = response.data[response.data.length - 1].id;
                }
                response.data.forEach((article) => {
                    article.imageUrl = imageUrl + article.imageUrl;
                });
                searchResult.value.push(...response.data);
                isPending.value = false;
            }).catch((error) => {
                console.error('Error fetching next articles:', error);
                isPending.value = false;
                isEnd.value = true;
            });
        } catch (error) {
            console.error('Error fetching next articles:', error);
        }
    };

</script>

<style scoped>
.background {
    padding: 20px;
    margin: 20px 50px;

}
.search-header {
    margin-bottom: 20px;
    padding: 10px;
}
</style>   