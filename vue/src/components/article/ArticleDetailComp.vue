<template>
    <div class="detail-box">
        <div class="detail-image-box">
            <img :src="sub.imageUrl"/>
        </div>
        <div class="detail">
            <pre>{{ sub.content }}</pre>
        </div>
    </div>
</template>

<script setup>
    import { ref } from 'vue';
    const props = defineProps({
        sub: {
            type: Object,
            required: true,
            default: {
                id:null,
                article_id:null,
                content: '',
                imageId: null,
                order: null,
                imageUrl: null
            }
        }
    });
</script>

<style scoped>
    .detail-box {
        display: flex;
        margin-bottom: 10px;
        margin-top: 10px;
        background-color: #DDD;
        padding: 10px;
        border-radius: 15px;
        align-items: stretch;
    }
    .detail {
        flex: 1;
        margin-right: 10px;
        min-height: 100%;
        border: #AAA solid 1px;
        border-radius: 10px;
        display: flex;
    }

    .detail-image-box {
        display: flex;
        flex-direction: column;
        width: 300px;
        margin-right: 20px;
        background-color: #BBB;
        border-radius: 10px;
    }

    .detail-image-box img {
        flex: 1;
        width: 100%;
        height: auto;
        max-height: 400px;
        object-fit: contain;
        border-radius: 10px;
    }

    .detail textarea {
        width: 100%;
        height: 100%;
        border: none;
        background-color: #DDD;
        border-radius: 10px;
        padding: 10px;
        font-size: 1.2em;
    }

    .detail textarea:focus {
        outline: none;
    }

</style>