<template>
    <div ref = "box" class="uploader-box" @drop="dropEvent" @dragover.prevent>
        <input ref="input" type="file" accept="image/*" @change="uploadEvent" style="display: none;">
        <div v-show="isEmpty" class="empty-box" @click="openDialog" >
            <img src="../../assets/icon/download.svg" alt="upload image" style="height: 70px;width: 70px;">
            <p>Drag & Drop Image</p>
        </div>
        <div v-show="!isEmpty" class="image-box">
            <img ref="image" alt="Uploaded Image" :src="image.imageUrl" />
            <button @click="removeImage">이미지삭제</button>
        </div>
    </div>
</template>

<script setup>
    import { ref,onMounted } from 'vue';
    import axios from 'axios'
    
    /* DOM */
    const input = ref(null)
    const box = ref(null)

    /* 사용자 정의 변수 */
    const isEmpty = ref(true)
    const uploadUrl = import.meta.env.VITE_API_URL + '/upload'

    const resourceUrl = import.meta.env.VITE_IMAGE_URL
    
    /* 사용자 정의 함수 */
    const removeImage = () => {
        props.image.imageUrl = null
        props.image.imageId = null
        isEmpty.value = true
        input.value.value = ""
        emits('onFileUpload', null)
    }
    
    const upload = newFile => {
        axios.post(uploadUrl, {file:newFile}, {
            headers: {
                'Content-Type': 'multipart/form-data',
            }
        })
        .then(res => {
            isEmpty.value = false
            props.image.imageUrl = resourceUrl + res.data.orgFile
            props.image.imageId = res.data.id
            emits('onFileUpload',res.data.id,resourceUrl + res.data.orgFile)
        })
        .catch(err => {
            console.error(err);
            removeImage()
        })
    }

    
    
    const openDialog = () => {
        input.value.click()
    }

    /* 이벤트 리스너 */
    const dropEvent = (event) => {
        event.preventDefault()
        const droppedFiles = event.dataTransfer.files
        if (droppedFiles.length > 0) {
            const selectedFile = droppedFiles[0]
            upload(selectedFile)
        }
    }

    const uploadEvent = (event) => {
        const selectedFile = input.value.files[0]
        if (selectedFile) {
            upload(selectedFile)
        }
    }
    
    /* 컴포넌트 정의 */
    const emits = defineEmits(['onFileUpload'])
    const props = defineProps({
        image: {
            type: Object,
            required: false,
            default: {
                imageId: null,
                imageUrl: null
            }
        }
    })

    onMounted(() => {
        if (box.value.offsetWidth <= 300) {
            box.value.classList.add('small-box')
        }
    })
</script>

<style scoped>
    .uploader-box {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-bottom: 20px;
        width: 100%;
        height: 100%;
    }

    .empty-box {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        width: 100%;
        height: 100%;
        border: 2px dashed #999;
        border-radius: 10px;
        background-color: #f9f9f9;
    }

    .image-box {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        width: 100%;
        height: 100%;
        border-radius: 15px;
        background-color: #DDD;
        position: relative;
    }

    .image-box img {
        width: 100%;
        height: 100%;
        object-fit: contain;
        border-radius: 15px;
    }

    .image-box button {
        margin-top: 10px;
        padding: 5px 10px;
        background-color: #f44336;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
        position: absolute;
        top: 10px;
        right: 10px;
    }

    .small-box p {
        display: none;
    }
</style>