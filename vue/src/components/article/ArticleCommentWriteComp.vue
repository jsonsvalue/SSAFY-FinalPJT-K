<template>
    <div>
        <div class="container">
            <div class="outline">
                <div class="profile">
                    <ProfileImageComp :src="user.imageUrl" :user-id="user.userId"></ProfileImageComp>
                    <span class="profile-name">{{ user.name }}</span>
                </div>
                <textarea rows="5" class="comment-input" placeholder="댓글을 입력하세요..." @keypress:enter="handleCommentSubmit" v-model="comment.content"></textarea>
                <button class="btn btn-secondary" @click="handleCommentSubmit">등록</button>
            </div>
        </div>
    </div>
</template>

<script setup>
    import router from '@/router';
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import ProfileImageComp from '../user/ProfileImageComp.vue';
    

    const comment = ref({
        userId:null,
        content:'',
        articleId: null
    });
    const user = ref(JSON.parse(sessionStorage.getItem("user")))
    const url = import.meta.env.VITE_API_URL + '/article';
    const handleCommentSubmit = () => {
        if (comment.value.content.trim() === '') {
            alert('댓글을 입력해주세요.');
            return;
        }
        comment.value.userId = user.userId;
        comment.value.articleId = props.articleId;

        axios.post(`${url}/${props.articleId}/comment`,comment.value)
            .then(res => {
                console.log(res.data);
                comment.value = '';
                router.go()
                // router.push({ name: 'ArticleMaster', params: { id: props.articleId }})
            })
            .catch(err => {
                console.error(err); 
            })
        
    };

    const props = defineProps({
        articleId: {
            type: Number,
            required: true
        }
    });
</script>

<style scoped>
.outline {
    width: 100%;
    height: 100%;
    padding: 20px;
    background-color: #ffffff;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    position: relative;
}
.comment-input {
    width: 100%;
    min-height: 80px;
    height: auto;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 14px;
    resize: vertical;
    overflow-y: visible;
}
.outline button {
    width: 70px;
    height: 35px;
    font-size: 0.8em;
    align-self: flex-end;
    margin-top: 10px;
    margin-right: 5px;
}
.profile {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
}
.profile-image {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-right: 10px;
}
.profile-name {
    font-size: 16px;
    font-weight: bold;
    color: #333;
}

</style>