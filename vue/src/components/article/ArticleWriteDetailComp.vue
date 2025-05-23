<template>
    <div class="detail-box">
        <div class="detail-image-box">
            <ImageUploadComp :image=subArticle @on-file-upload="onFileUpload"/>
        </div>
        <div class="detail">
            <textarea class="form-control" v-model="props.subArticle.content" rows="5"></textarea>
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
    import { ref } from 'vue';
    import ImageUploadComp from '@/components/common/ImageUploadComp.vue';

    const props = defineProps({
        subArticle: {
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

    /* 사용자 정의 함수 */
    const onFileUpload = (fileId) => {
        props.subArticle.imageId = fileId;
    }

    const deleteDetail = () => {
        emits('onDelete', props.subArticle.order);
    }

    const moveUp = () => {
        emits('onMoveUp', props.subArticle.order);
    }
    const moveDown = () => {
        emits('onMoveDown', props.subArticle.order);
    }
    /* 컴포넌트 정의 */
    const emits = defineEmits(['onDelete', 'onMoveUp', 'onMoveDown']);

</script>

<style scoped>
    .detail-box {
        display: flex;
        margin-bottom: 10px;
        margin-top: 10px;
        height: 200px;
    }
    .detail {
        flex: 1;
        margin-right: 10px;
    }

    .detail textarea {
        width: 100%;
        height: 100%;
        resize: none;
        border-radius: 5px;
        padding: 10px;
        border: 1px solid #ccc;
        box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
    }
    .detail-button-box {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .detail-button-box button {
        margin-bottom: 10px;
    }

    .detail-image-box {
        width: 300px;
        margin-right: 20px;
    }
</style>