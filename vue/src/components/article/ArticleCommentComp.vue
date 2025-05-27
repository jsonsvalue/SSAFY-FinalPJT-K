<template>
    <div class="container">
        <div class="outline">
            <p class="count">댓글 {{count}}개</p>
            <template v-for="(c, index) in props.comment" :key="index">
                <ArticleCommentSubComp :comment="c"></ArticleCommentSubComp>
            </template>
            <template v-if="isLogin">
                <ArticleCommentWriteComp :article-id="props.articleId"></ArticleCommentWriteComp>
            </template>
        </div>
    </div>
</template>

<script setup>
    import ArticleCommentSubComp from '@/components/article/ArticleCommentSubComp.vue';
    import ArticleCommentWriteComp from './ArticleCommentWriteComp.vue';
    import { computed, onMounted, ref} from 'vue';
    const count = ref(0);
    const isLogin = ref(false)
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
        isLogin.value = sessionStorage.getItem('user') !== null;
    });
    count.value = computed(() => props.comment.length);
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