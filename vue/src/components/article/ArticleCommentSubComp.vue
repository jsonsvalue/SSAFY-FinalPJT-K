<template>
    <div class="comment">
        <ProfileImageComp :src="comment.imageUrl" :user-id="comment.userId"></ProfileImageComp>
        <div class="comment-box">
            <p class="name" @click="profile" style="cursor: pointer;">{{ comment.userName }}</p>
            <div class="detail">
                <pre>{{ comment.content }}</pre>
            </div>
        </div>
    </div>
</template>

<script setup>
import { defineProps } from 'vue';
import ProfileImageComp from '../user/ProfileImageComp.vue';
import router from '@/router';
const props = defineProps({
    comment: {
        type: Object,
        required: true
    }
});
// console.log(props.comment)
const profile = () => {
    router.push({
        name: 'profile',
        params: { userId: props.comment.userId }
    });
};
</script>

<style scoped>
.comment {
    display: flex;
    align-items: start;
    margin-bottom: 10px;
}

.comment img {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-right: 10px;
    margin-top: 10px;
}

.comment-box {
    background-color: #e1e1e1;
    padding: 10px;
    border-radius: 5px;
    max-width: 700px;
}

.comment-box .name {
    font-weight: bold;
    margin-bottom: 5px;
    font-size: 1rem;
    margin-left: 10px;
}

.comment-box .content {
    margin: 0;
    font-size: 0.9em;
}

.comment-box p {
    margin: 0;
}
</style>