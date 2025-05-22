<template>
    <div ref = "box" class="uploader-box" @drop="dropEvent" @dragover.prevent>
        <input ref="input" type="file" accept="image/*" @change="uploadEvent" style="display: none;">
        <div v-show="isEmpty" class="empty-box" @click="openDialog" >
            <img src="../../assets/icon/download.svg" alt="upload image" style="height: 70px;width: 70px;">
            <p>Drag & Drop Image</p>
        </div>
        <div v-show="!isEmpty" class="image-box">
            <img ref="image" :src="url" alt="Uploaded Image">
            <button @click="removeImage">이미지삭제</button>
        </div>
    </div>
</template>

<script setup>
    import { ref,onMounted } from 'vue';
    import axios from 'axios'
    
    /* DOM */
    const input = ref(null)
    const image = ref(null)
    const box = ref(null)

    /* 사용자 정의 변수 */
    const isEmpty = ref(true)
    const url = ref(null)
    const uploadUrl = import.meta.env.VITE_API_URL + '/upload'

    const resourcePath = import.meta.env.VITE_RESOURCE_PATH
    
    /* 사용자 정의 함수 */
    const removeImage = () => {
        url.value = null
        isEmpty.value = true
    }
    
    const upload = newFile => {
        axios.post(uploadUrl, {file:newFile}, {
            headers: {
                'Content-Type': 'multipart/form-data',
            }
        })
        .then(res => {
            console.log(res)
        })
        .catch(err => {
            console.error(err); 
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
    const emits = defineEmits(['onFileUpload','onFileRemove'])
    const props = defineProps({
        image: {
            type: Object,
            required: false
        }
    })

    defineExpose({
        getFile: () => {
            return file.value
        },
        isEdited: () => {
            return isEdited
        }
    })

    onMounted(() => {
        if (box.value.offsetWidth <= 300) {
            box.value.classList.add('small-box')
        }
        if (props.image) {
            url.value = props.image
            isEmpty.value = false
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