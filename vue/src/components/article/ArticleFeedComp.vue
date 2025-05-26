<template>
    <div class="container">
        <div class="outline">
            <div class="profile">
                <ProfileImageComp :src="article.userImage" :user-id="article.userId"></ProfileImageComp>
                <div class="name-box">
                    <p class="name" style="cursor: pointer;" @click="profile">{{article.userName}}</p>
                    <p class="category">{{article.type=='eat'?'냠냠':'레시피'}}</p>
                </div>
            </div>
            <RouterLink :to="{ name: 'ArticleMaster', params: { id: article.id } }">
                <div class="img-box">
                    <img :src="article.imageUrl" alt="Article Image"/>
                </div>
            </RouterLink>
            <div class="button-box">
                <div :class="{dislike:isLike,like:!isLike}" @click="like">
                    <img/>
                    <span>{{article.likeCount}}</span>
                </div>
                <RouterLink :to="{ name: 'ArticleMaster', params: { id: article.id },query: { comment: true } }">
                    <img src="../../assets/icon/comment.svg"/>
                </RouterLink>
            </div>
        </div>
    </div>
</template>

<script setup>
    import { defineProps, onBeforeMount,watch, ref } from 'vue';
    import ProfileImageComp from '../user/ProfileImageComp.vue';
    import { useRouter } from 'vue-router';
    import axios from 'axios';
    
    const props = defineProps({
        'article' : {
            type:Object,
            required:true
        }
    })

    const isLike = ref(false);

    watch(() => props.article.isLike, (newVal) => {
        isLike.value = newVal === 'true';
    });



    const router = useRouter();

    const profile = () => {
        if (props.article.userId) {
            router.push({
                name: 'profile',
                params: { userId: props.article.userId }
            });
        }
    };

    const url = import.meta.env.VITE_API_URL + `/article/${props.article.id}/like`;

    const like = () => {
        if (isLike.value) {
            axios.delete(url)
            .then(res => {
                props.article.isLike = 'false';
                props.article.likeCount--;
            })
            .catch(err => {
                console.error(err); 
            })
        } else {
            axios.put(url)
            .then(res => {
                props.article.isLike = 'true';
                props.article.likeCount++;
            })
            .catch(err => {
                console.error(err); 
            })
        }
    };
    onBeforeMount(()=>{
        if (!props.article) {
            throw new Error("article is empty")
        }
        isLike.value = props.article.isLike === 'true';
    })
</script>

<style scoped>
    .container {
        margin-bottom: 20px;
        margin-top: 10px;
        background-color: #FFF;
        padding:0;
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
    .like,.dislike {
        cursor: pointer;
    }
    .like img {
        content: url('../../assets/icon/heart.svg');
        border: none;
    }

    .dislike img {
        content: url('../../assets/icon/heart-fill.svg');
        border: none;
    }
</style>
