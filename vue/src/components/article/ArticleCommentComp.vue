<template>
    <div class="container">
        <div class="outline">
            <p class="count">댓글 2개</p>
            <ArticleCommentSubComp></ArticleCommentSubComp>
            <ArticleCommentSubComp></ArticleCommentSubComp>
            <ArticleCommentSubComp></ArticleCommentSubComp>
            <ArticleCommentWriteComp></ArticleCommentWriteComp>
        </div>
    </div>
</template>

<script setup>
    import ArticleCommentSubComp from '@/components/article/ArticleCommentSubComp.vue';
    import ArticleCommentWriteComp from './ArticleCommentWriteComp.vue';
    import { ref } from 'vue';
    const comment = ref([]);

</script>

<style scoped>
.outline {
    width: 100%;
    height: 100%;
    padding: 20px;
    background-color: #f9f9f9;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
.count {
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 10px;
}
</style>