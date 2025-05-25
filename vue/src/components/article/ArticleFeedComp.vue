<template>
    <div class="container">
        <div class="outline">
            <div class="profile">
                <img src="../../assets/img/DeepSleepCho.jpg" style=""/>
                <div class="name-box">
                    <p class="name">DeepMindSuin</p>
                    <p class="category">냠냠</p>
                </div>
            </div>
            <RouterLink :to="{ name: 'ArticleMaster', params: { id: article.id } }">
                <div class="img-box">
                    <img :src="article.imageUrl" alt="Article Image"/>
                </div>
            </RouterLink>
            <div class="button-box">
                <a class="fav" href="">
                    <img src="../../assets/icon/heart-fill.svg"/>
                </a>
                <span>20</span>
                <a class="fav" href="">
                    <img src="../../assets/icon/heart.svg"/>
                </a>
                <span>20</span>
                <a href=""><img src="../../assets/icon/comment.svg"/></a>
            </div>
        </div>
    </div>
</template>

<script setup>
    import { defineProps, onBeforeMount } from 'vue';
    const props = defineProps({
        'article' : {
            type:Object,
            required:true
        }
    })

    
    /*
     *  개발시에 활성화 시키기. 글 내용이 없으면 렌더링 하지 않음
     */

    // onBeforeMount(()=>{
    //     if (!props.article) {
    //         throw new Error("article is empty")
    //     }
    // })
</script>

<style scoped>
    .container {
        margin-bottom: 20px;
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
</style>
