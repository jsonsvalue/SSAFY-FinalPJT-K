<template>
  <div class="container-fluid min-vh-100 d-flex justify-content-center custom-margin">
    <div class="row w-100 justify-content-center">
      <div class="col-md-10 col-lg-8">
        <div class="row align-items-center">
          <!-- Left side with logo and text -->
          <div class="col-md-6 mb-4 mb-md-0 text-md-start text-center">
            <h1 class="logo mb-3">Nyam Road</h1>
            <p class="lead">
              친구들과 맛있는 레시피와 식당을
              <br>
              공유하세요.
            </p>
          </div>
          
          <!-- Right side with login form -->
          <div class="col-md-6">
            <div class="card shadow-sm p-3 bg-white rounded">
              <div class="card-body">
                <b-form @submit.prevent="onSubmit">
                  <b-form-group>
                    <b-form-input
                      v-model="userId"
                      type="text"
                      placeholder="아이디"
                      class="mb-3 p-3"
                    ></b-form-input>
                  </b-form-group>
                  
                  <b-form-group>
                    <b-form-input
                      v-model="password"
                      type="password"
                      placeholder="비밀번호"
                      class="mb-3 p-3"
                    ></b-form-input>
                  </b-form-group>
                  
                  <b-button
                    variant="primary"
                    class="w-100 py-2 mb-3 fw-bold"
                    size="lg"
                    @click="onSubmit"
                  >
                    로그인
                  </b-button>
                </b-form>
                
                <!-- <div class="text-center mb-3">
                  <a href="#" class="text-decoration-none">비밀번호를 잊으셨나요?</a>
                </div> -->
                
                <hr>
                
                <router-link to="/regist">
                    <div class="text-center">
                        <b-button
                            variant="primary"
                            class="px-3 py-2 fw-bold"
                        >
                            새 계정 만들기
                        </b-button>
                    </div>
                </router-link>
              </div>
            </div>
            
            <!-- <div class="text-center mt-3 small">
              <p>유명인, 브랜드 또는 비즈니스를 위한 <a href="#" class="text-decoration-none fw-bold">페이지 만들기</a>.</p>
            </div> -->
          </div>
        </div>
      </div>
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
    const onSubmit = function(){
        store.userLogin(userId.value, password.value);
    }


</script>

<style scoped>
.logo {
  font-family: 'Brush Script MT', cursive;  
  font-size: 5rem;
  font-weight: bold;
  letter-spacing: -1px;
}


.btn-success {
  background-color: #42b72a;
  border-color: #42b72a;
}

/* Responsive adjustments */
@media (max-width: 767.98px) {
  .facebook-logo {
    font-size: 3rem;
  }
}

.custom-margin{
    margin-top:200px;
}
</style>