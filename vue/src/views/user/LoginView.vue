<template>
<div>
    <div>
        <input type = "text" v-model = "userId" placeholder= "아이디">
        <input type = "password" v-model = "password" placeholder = "패스워드">
        <button @click="submit">로그인</button>
    </div>
</div>
</template>

<script setup>
    import { BFormInput, BButton, BInputGroup } from 'bootstrap-vue-next';
    import {useUserStore} from '@/stores/user';
    import {ref} from "vue";    
     
    const userId = ref('');
    const password = ref('');

    const store = useUserStore();
    
    // login시 요청을 처리한다.
    const submit = function(){
        store.userLogin(userId.value, password.value);
    }


</script>

<style scoped>

</style>