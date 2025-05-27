<template>
    <RouterLink :to="{name:'profile',params :{userId:props.userId}}">
        <img ref="img" @error="noImage" :alt="alt" :class=props.class class="profile-image" />
    </RouterLink>
</template>

<script setup>
    import router from '@/router';
    import { ref,defineProps } from 'vue';
    import { onMounted } from 'vue';
    const image_url = import.meta.env.VITE_IMAGE_URL;
    const noImage = (event) => {
        img.value.src = "/src/assets/icon/no-image.svg";
    };
    const click = () => {
        router.push({
            name: 'profile',
            params: { userId: props.userId}
        });
    };
    const img = ref(null);
    const props = defineProps({
        userId: {
            type: String,
            default: ''
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
        if (props.src&& props.src.indexOf('undefined') == -1) {
            if(props.src.indexOf('http') == -1){
                img.value.src = image_url + props.src;
            }else{
                img.value.src = props.src;
            }
        }
    });
</script>

<style scoped>
    .profile-image {
        max-height:50px;
        cursor:pointer;
        width: 100%;
        height: 100%; 
        border-radius: 50%;
        object-fit : cover;
    }

    
</style>