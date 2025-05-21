<template>
    <div class="uploader-box" @drop="dropEvent" @dragover.prevent>
        <input ref="input" type="file" accept="image/*" @change="uploadEvent" style="display: none;">
        <div v-show="isEmpty">
            <img src="../../assets/icon/download.svg" alt="upload image" @click="openDialog" style="width: 100%; height: auto;">
        </div>
        <div v-show="!isEmpty">
            <img ref="image" :src="url" alt="Uploaded Image" style="width: 100%; height: auto;">
            <button @click="">이미지삭제</button>
        </div>
    </div>
</template>

<script setup>
    import { ref } from 'vue';
    const input = ref(null)
    const image = ref(null)
    const file = ref(null)
    const isEmpty = ref(true)
    let url = ref(null)
    const uploadEvent = (event) => {
        const selectedFile = event.target.files[0]
        if (selectedFile) {
            upload(selectedFile)
        }
    }
    const upload = newFile => {
        if (url.value) {
            URL.revokeObjectURL(url.value)
        }
        if (newFile) {
            file.value = newFile
            url = URL.createObjectURL(newFile)
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
</style>