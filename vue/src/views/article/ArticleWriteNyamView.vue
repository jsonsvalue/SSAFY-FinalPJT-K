<template>
    <div class="container">
        <div class="outline">
            <h3>냠냠 작성</h3>
            <div class="write-form">
                <BInputGroup class="mb-3">
                    <BInputGroupText>제목</BInputGroupText>
                    <BFormInput v-model="title" placeholder="제목을 입력하세요" />
                </BInputGroup>
                <BInputGroup class="mb-3 content">
                    <BInputGroupText>내용</BInputGroupText>
                    <BFormTextarea v-model="content" placeholder="내용을 입력하세요" />
                </BInputGroup>
            </div>
            <ImageUploadComp />
            <div v-for="(sub, index) in subArticle" :key="index">
                <ArticleWriteDetailComp :subArticle="sub" />
            </div>
            <div class="addSub">
                <BButton variant="outline-primary" @click="subArticle.push(newArticle)">추가</BButton>
            </div>
            <div class="button-box">
                <BButton variant="primary" @click="submitArticle">작성하기</BButton>
                <BButton variant="secondary" @click="$router.push('/article')">취소</BButton>
            </div>
        </div>
    </div>
</template>

<script setup>
    import { BFormInput, BButton,BInputGroup,BFormText,BInputGroupText,BFormTextarea } from 'bootstrap-vue-next';
    import { ref } from 'vue';
    import ImageUploadComp from '@/components/common/ImageUploadComp.vue';
    import ArticleWriteDetailComp from '@/components/article/ArticleWriteDetailComp.vue';

    const title = ref('');
    const content = ref('');
    const subArticle = ref([]);
    const newArticle = {
        content: 'new Content',
        image:null
    }

    const submitArticle = () => {
        // Submit article logic
        console.log('Title:', title.value);
        console.log('Content:', content.value);
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
        margin-top: 10px;
    }

    .button-box button {
        margin-left: 10px;
    }

    .addSub button {
        width: 100%;
        height: 50px;
    }
</style>
