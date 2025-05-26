<template>
    <BModal id="image-modal" v-model="showModal"></BModal>
</template>

<script setup>
    import { BModal } from 'bootstrap-vue-next';
    import { ref } from 'vue';
    const showModal = ref(false);
    const imageUrl = ref('');
    const openModal = (url) => {
        imageUrl.value = url;
        showModal.value = true;
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

</style>