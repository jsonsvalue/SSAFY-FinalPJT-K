<template>
    <div class="container">
        <div class="outline">
            <p class="count">댓글 {{count}}개</p>
            <template v-for="(c, index) in comment" :key="index">
                <ArticleCommentSubComp></ArticleCommentSubComp>
            </template>
            <ArticleCommentWriteComp :article-id="props.articleId"></ArticleCommentWriteComp>
        </div>
    </div>
</template>

<script setup>
    import ArticleCommentSubComp from '@/components/article/ArticleCommentSubComp.vue';
    import ArticleCommentWriteComp from './ArticleCommentWriteComp.vue';
    import { onMounted, ref } from 'vue';
    const comment = null;
    const count = ref(0);
    const props = defineProps({
        articleId: {
            type: Number,
            required: true
        },
        comment: {
            type: Array,
            default: () => []
        }
    });

    onMounted(() => {
        comment = props.comment;
        count.value = comment.length;

    });
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