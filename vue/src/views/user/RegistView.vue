<template>
  <div class="container">
    <div class="outline">
      <h1 class="center instagram-logo">Nyam Road</h1>
      
      <p class="center description">
        친구들의 맛있는 레시피와 식당을 공유하려면 <br>
        가입하세요.
      </p>
      
      <!-- <BButton variant="primary" class="mb-3">
        <i class="bi bi-facebook me-2"></i> Google로 로그인
      </BButton> -->
      
      <div class="divider">
        <span>또는</span>
      </div>
      
      <div class="write-form">
        <BInputGroup class="mb-3 content">
          <BFormInput v-model="emailOrPhone" placeholder="이메일 주소" />
        </BInputGroup>
        
        <BInputGroup class="mb-3 content">
          <BFormInput v-model="name" placeholder="성명" />
        </BInputGroup>
        
        <BInputGroup class="mb-3 content">
          <BFormInput v-model="username" placeholder="사용자 아이디" />
        </BInputGroup>
        
        <BInputGroup class="mb-3 content">
          <BFormInput v-model="password" type="password" placeholder="비밀번호" />
        </BInputGroup>
      </div>
      
      <!-- <p class="terms-text center">
        저희 서비스를 이용하는 사람이 회원님의 연락처 정보를 Instagram에 업로드했을 수도 있습니다. 
      </p> -->
      
      <div class="button-box center">
        <BButton variant="primary" @click="signup" class="signup-btn">가입</BButton>
      </div>
    </div>
    
    <div class="outline login-link mt-3">
      <p class="center mb-0">
        계정이 있으신가요? <a href="/login" variant = "primary" class="login-text">로그인</a>
      </p>
    </div>
  </div>
</template>

<script setup>
    import { BFormInput, BButton, BInputGroup } from 'bootstrap-vue-next';
    import {useUserStore} from '@/stores/user';
    import { ref } from 'vue';

    const email = ref('');
    const name = ref('');
    const username = ref('');
    const password = ref('');

    const signup = function(){
        
    //   console.log('Signing up with:', {
    //     email: email.value,
    //     name: name.value,
    //     username: username.value,
    //     password: password.value
    //   });
    };
</script>

<style scoped>
.container {  
  max-width: 350px;
  margin: 0 auto;
  padding: 50px 0;
}

.outline {
  border: 1px solid #EEE;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
  -webkit-box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
  -moz-box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
  display: flex;
  flex-direction: column;
  background-color: white;
}

.center {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.instagram-logo {
  font-family: 'Brush Script MT', cursive;
  font-size: 42px;
  margin-bottom: 10px;
  font-weight: 500;
}

.description {
  color: #8e8e8e;
  font-size: 14px;
  margin-bottom: 20px;
  line-height: 1.4;
  text-align: center;
}


.divider {
  display: flex;
  align-items: center;
  margin: 10px 0 18px;
}

.divider::before,
.divider::after {
  content: "";
  flex: 1;
  border-bottom: 1px solid #dbdbdb;
}

.divider span {
  padding: 0 10px;
  color: #8e8e8e;
  font-size: 13px;
  font-weight: 600;
}

.write-form {
  margin-bottom: 15px;
}

.content input {
  background-color: #fafafa;
  border: 1px solid #dbdbdb;
  border-radius: 3px;
  padding: 9px 8px;
  font-size: 14px;
}

.terms-text {
  color: #8e8e8e;
  font-size: 12px;
  line-height: 1.4;
  margin: 10px 0;
  text-align: center;
}

.more-link {
  color: #0095f6;
  text-decoration: none;
  font-weight: 600;
}

.signup-btn {
  border: none;
  border-radius: 4px;
  width: 100%;
  padding: 7px 0;
  font-weight: 600;
  font-size: 14px;
  margin-top: 10px;
}

.login-link {
  padding: 15px;
}

.login-text {
  text-decoration: none;
  font-weight: 600;
  margin-left: 5px;
}
</style>