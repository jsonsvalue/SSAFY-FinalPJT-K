<template>
    <img ref="img" @error="noImage" :alt="alt" :class=props.class @click="click" style="cursor: pointer"/>
</template>

<script setup>
    import router from '@/router';
import { ref,defineProps } from 'vue';
    import { onMounted } from 'vue';
    const noImage = (event) => {
        img.value.src = "/src/assets/icon/no-image.svg";
    };
    const click = () => {
        router.push({
            name: 'UserProfile',
            params: { userId: props.userId }
        });
    };
    const img = ref(null);
    const props = defineProps({
        userId: {
            type: String,
            required: true
        },
        src: {
            type: String,
            required: false
        },
        alt: {
            type: String,
            default: '프로필 이미지'
        },
        class: {
            type: String,
            default: 'profile-image'
        }
    });
    onMounted(() => {
        if (props.src != null && props.src.indexOf('undefined') != -1) {
            img.value.src = props.src;
        }
    });
</script>

<style scoped>

</style>