<template>
    <BModal id="image-modal" v-model="showModal" no-footer size="xl">
        <div class="image-container" ref="imageContainer">
            <img ref="image" :src="imageUrl" class="img-fluid" alt="이미지" @load="onLoad"/>
        </div>
    </BModal>
</template>

<script setup>
    import { BModal } from 'bootstrap-vue-next';
    import { ref } from 'vue';
    const showModal = ref(false);
    const imageUrl = ref('');
    const imageContainer = ref(null);
    const image = ref(null);
    const openModal = (url) => {
        imageUrl.value = url;
        showModal.value = true;
    };
    const onLoad = () => {
        let height = image.value.naturalHeight;
        imageContainer.value.style.height = height + 'px';
    };
    const closeModal = () => {
        imageUrl.value = '';
        showModal.value = false;
    };

    defineExpose({
        openModal,
        closeModal
    });
</script>

<style scoped>
    .image-container {
        display: flex;
        justify-content: center;
        min-height: 400px;
        max-height: 900px;
        max-width: 100%;
    }
    img {
        max-width: 100%;
        object-fit: contain;
    }
</style>