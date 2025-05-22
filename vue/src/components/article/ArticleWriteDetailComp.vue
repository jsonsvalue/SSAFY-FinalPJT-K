<template>
    <div class="detail-box">
        <div class="detail-image-box">
            <ImageUploadComp />
        </div>
        <div class="detail">
            <p>{{props.subArticle.content}}</p>
        </div>
        <div class="detail-button-box">
            <button class="btn btn-primary" @click="moveUp">Up</button>
            <button class="btn btn-warning" @click="deleteDetail">Delete</button>
            <button class="btn btn-primary" @click="moveDown">Down</button>
        </div>
    </div>
</template>

<script setup>
    import { BFormInput, BButton,BInputGroup,BFormText,BInputGroupText,BFormTextarea } from 'bootstrap-vue-next';
    import { ref,defineProps } from 'vue';
    import ImageUploadComp from '@/components/common/ImageUploadComp.vue';

    const props = defineProps({
        subArticle: {
            type: Object,
            required: true
        }
    });

    const submitArticle = () => {
        console.log('Content:', content.value);
    }

    const deleteDetail = () => {
        console.log('Delete detail');
    }

    const moveUp = () => {
        console.log('Move up');
    }
    const moveDown = () => {
        console.log('Move down');
    }
</script>

<style scoped>
    .detail-box {
        display: flex;
        margin-bottom: 10px;
        margin-top: 10px;
    }
    .detail {
        flex: 1;
        padding: 10px;
        border: 1px solid #EEE;
        border-radius: 5px;
        box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
        -webkit-box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
        -moz-box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
        margin-right: 10px;
    }
    .detail-button-box {
        display: flex;
        flex-direction: column;
        margin-top: 10px;
    }
</style>