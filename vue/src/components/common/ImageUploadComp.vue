<template>
    <div class="uploader-box" @drop="dropEvent" @dragover.prevent>
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
    import { ref,defineProps,defineExpose } from 'vue';
    const input = ref(null)
    const image = ref(null)
    const file = ref(null)
    const isEmpty = ref(true)
    defineExpose({
        getFile: () => {
            return file.value
        },
        isEdited: () => {
            return isEdited
        }
    })
    let isEdited = false
    let url = ref(null)
    const props = defineProps({
        imageUrl: {
            type: String,
            default: null
        }
    })
    const removeImage = () => {
        if (url.value) {
            URL.revokeObjectURL(url.value)
        }
        file.value = null
        url.value = null
        isEmpty.value = true
    }
    const uploadEvent = (event) => {
        const selectedFile = input.value.files[0]
        if (selectedFile) {
            upload(selectedFile)
        }
    }
    const upload = newFile => {
        isEdited = true
        if (url.value) {
            URL.revokeObjectURL(url.value)
        }
        if (newFile) {
            file.value = newFile
            url.value = URL.createObjectURL(newFile)
            image.value.src = url
            isEmpty.value = false
        }
    }
    const dropEvent = (event) => {
        event.preventDefault()
        const droppedFiles = event.dataTransfer.files
        if (droppedFiles.length > 0) {
            const selectedFile = droppedFiles[0]
            upload(selectedFile)
        }
    }
    const openDialog = () => {
        input.value.click()
    }

    if (props.imageUrl) {
        url.value = props.imageUrl
        isEmpty.value = false
    }
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
</style>