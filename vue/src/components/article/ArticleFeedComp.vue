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
            <div class="img-box">
                <img src="../../assets/img/bob.jpg">
            </div>
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
                <a onclick=""><img src="https://www.svgrepo.com/show/512829/share-1100.svg"/></a>
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

    .profile {
        display: flex;
        margin-bottom: 20px;
    }

    .profile p {
        margin-top: 2px;
        margin-bottom: 2px;
    }

    .profile img {
        border-radius: 50%;
        width: 65px;
        height: 65px;
    }
    .profile .name-box {
        display: flex;
        flex-direction: column;
        margin-left: 15px;
    }

    .profile .name {
        font-size: 1.3em;
        font-weight: 700;
    }
    .profile .category {
        font-size: 1.0em;
        color: #AAA;
    }

    .img-box {
        width: 100%;
    }

    .img-box img {
        max-width: 100%;
        min-width: 200px;
        max-height: 500px;
        object-fit: contain;
        border-radius: 15px;
    }

    .button-box {
        margin-top: 10px;
        display: flex;
        align-items: center;
    }

    .button-box a {
        cursor: pointer;
    }

    .button-box img {
        height: 35px;
        margin: 5px;
    }

    .button-box span {
        font-size: 1.2em;
        font-weight: 700;
        margin-left: 3px;
        margin-right: 7px;
    }
</style>
