<template>
    <div class="bg-light min-vh-100">
    <div class="container-md p-0 mt-4">
      <!-- Header -->
      <div class="bg-light p-3">
        <h4 class="mb-0 fw-bold">프로필 편집</h4>
      </div>

      <!-- <ProfileImageComp 
                :src="user.imageUrl" 
                :user-id="user.userId" 
                :class="rounded-circle"
                style="width: 60px; height: 60px; object-fit: cover;">
      </ProfileImageComp> -->

      <!-- Profile Section -->
       <!-- :src= "`${imageUrl}${user.imageUrl}`|| defaultImage"  -->
      <div class="bg-light p-3">
        <div class="bg-white rounded-3 p-3 d-flex align-items-center justify-content-between">
          <div class="d-flex align-items-center">
            <div class="position-relative me-3">
              <img 
                 :src= "`${imageUrl}${store.userInfo?.imageUrl}` || defaultImage"  
                alt="Profile" 
                class="rounded-circle"
                style="width: 60px; height: 60px; object-fit: cover"
              >
              
            </div>
            
            <div>
                <div v-if="store.userInfo" class="fw-bold text-dark">
                    {{ store.userInfo.userId }}
                </div>

                <div v-if="store.userInfo" class="text-muted">
                    {{ store.userInfo.name }}
                </div>
            </div>
          </div>
          <b-button 
          variant="primary" 
          size="sm" 
          class="rounded-pill px-3"
          @click = "updateImage"
          >
            사진 변경
          </b-button>
          <!-- Modal창을 띄우고 사진을 업로드 한 다음,
           img id를 갖고 user의 정보를 업데이트한다. --> 
          <!-- <ImageUploadComp style="height: 200px;" :image="article"/> -->

        </div>
      </div>

      <!-- E-mail Section -->
      <div class="p-3">
        <h5 class="fw-bold mb-3">이메일</h5>
        <div class="bg-white rounded-3 p-3">
          <div class="d-flex align-items-center justify-content-between">
            
            <div v-if="store.userInfo" class="text-muted">
                <span>{{store.userInfo.email}}</span>
            </div>
            
        </div>
            
        </div>
      </div>

      <!-- Introduction Section -->
      <div class="p-3">
        <h5 class="fw-bold mb-3">소개</h5>
        <div class="bg-white rounded-3 p-3">
          <b-form-textarea
            v-if = "store.userInfo"
            v-model = "store.userInfo.intro"
            placeholder="소개"
            rows="3"
            max-rows="6"
            class="border-0 bg-light rounded-2 p-3"
            maxlength="150"
          />
          <div v-if = "store.userInfo" class="text-end mt-2">
            <small class="text-muted">{{ (store.userInfo.intro||'').length }} / 150</small>
          </div>

        </div>

        <div class = "text-end mt-2">
            <b-button 
            variant="primary"
            size="sm"
            class="rounded-pill px-3"
            @click = "update">
                수정
            </b-button>
        </div>

      </div>

      <!-- Feed Type Section -->
      <div class="p-3">
        <h5 class="fw-bold mb-3">기본 피드 설정</h5>
        <div class="bg-white rounded-3 p-3">
          <div class="d-flex align-items-center justify-content-between">
            

            <span>기본 피드 설정</span>
            
            <div class="d-flex align-items-center gap-3">
                <!-- feedType 드롭다운 -->
                <b-form-select
                v-if="store.userInfo"
                v-model="store.userInfo.feedType"
                :options = "feedTypeOptions"
                class="form-select-sm"
                @change = "update"
                />

                
            </div>
        </div>
            
        </div>
      </div>

      <!-- Deleting account -->
      <div class="p-3 mt-4">
        <h5 class="fw-bold mb-3">회원 탈퇴</h5>
        <div class="bg-white rounded-3 p-3">
          <div class="d-flex align-items-center justify-content-between">
            

            <span>회원 탈퇴</span>
            
            <div class="d-flex align-items-center gap-3">
                <b-button 
                  variant="secondary"
                  size="sm"
                  class="rounded-pill px-3"
                  @click = "deleteAccount">
                  탈퇴
              </b-button>

                
            </div>
        </div>
            
        </div>
      </div>

      <!-- Profile Recommendations Section -->
      <!-- <div class="p-3">
        <h5 class="fw-bold mb-3">프로필에 계정 추천 표시</h5>
        <div class="bg-white rounded-3 p-3">
          <div class="d-flex align-items-start justify-content-between">
            <div class="flex-grow-1 me-3">
              <div class="fw-medium mb-1">프로필에 계정 추천 표시</div>
              <small class="text-muted">
                사람들이 회원님의 프로필에서 비슷한 계정 추천을 볼 수 있는지와 회원님의 계정이 다른 프로필에서 추천될 수 있는지를 선택하세요.
              </small>
            </div>
            <b-form-checkbox
              v-model="showRecommendations"
              switch
              size="lg"
              class="mb-0 flex-shrink-0"
            />
          </div>
        </div>
      </div> -->
    </div>

    
  </div>
    <!--  <div>
        axios가 비동기 처리이기 때문에 UserInfo가 Rendering되고 나면,
        template이 rendering 될 때 userInfo = null 값일때 실행 안되게 조건문을 걸고 Rendering한다.
        <div v-if="store.userInfo">
            <h2 >{{ store.userInfo.userId }}</h2>
        </div>        
    </div>-->
    
    <BModal id="editProfileModal" title="프로필 편집" size="lg" v-model="modalShow">
          <image-upload-comp style="min-height: 250px;"
          :ref="imageUpload" :image="editableImage" @onFileUpload = "receiveFileUpload"
          >
          </image-upload-comp>

        <template #footer>
            <b-button variant="primary" @click="updateUserPicture">수정</b-button>
            <b-button variant="secondary" @click="modalShow = false">닫기</b-button>
        </template>
    </BModal>

    
</template>

<script setup>
    import ImageUploadComp from '@/components/common/ImageUploadComp.vue';
    import ProfileImageComp from '@/components/user/ProfileImageComp.vue';
    import {  BFormInput, BFormTextarea, BFormCheckbox, BFormSelect, BButton} from 'bootstrap-vue-next';
    import { useUserStore } from '@/stores/user';
    import { useRoute,useRouter } from 'vue-router';
    import {ref, computed, onMounted} from 'vue';
    import profileDefault from '@/assets/img/profile.png'
    import {BModal} from 'bootstrap-vue-next';
    import axios from 'axios';
    
    const REST_API_URL = import.meta.env.VITE_API_URL;
    const image_url = import.meta.env.VITE_IMAGE_URL;

    const router = useRouter();
    const modalShow = ref(false);
    const imageUpload = ref(null);

    // User 정보 Session에서 받아온다.
    const user = ref(JSON.parse(sessionStorage.getItem("user")));
    
    // 기본 사진 설정
    const editableImage = ref({
      imageId: user.value.imageId,
      imageUrl: user.value.imageUrl
    });

    const updateImage = ()=>{
      editableImage.value = {
        imageId:user.value?.imageId || null, 
        imageUrl:user.value?.imageUrl ? `${imageUrl}${user.value.imageUrl}` : null
      };
      modalShow.value= true;
    }

    // Upload Component에서 emit된 imageId 및 imageUrl 값을 수신한다.
    const receiveFileUpload = (id, url) => {
        editableImage.value.imageId = id;
        editableImage.value.imageUrl = url;
        //console.log(editableImage.value.imageId);
        //console.log(editableImage.value.imageUrl);
    };

    // 수정 버튼을 누르면 editableImage에서 받은 정보를, 
    // userStore에서 객체 형태로 보내서 User의 정보를 업데이트한다.
    const updateUserPicture = () =>{
      const temp = {
        ...store.userInfo,
        imageId: editableImage.value.imageId,
      }

      axios.patch(`${REST_API_URL}/update`, temp)
      .then((response)=>{
        user.value.imageId = editableImage.value.imageId;
        user.value.imageUrl = editableImage.value.imageUrl;

        sessionStorage.setItem("user", JSON.stringify(user.value));

        router.go();
      })
      .catch((error)=>{
        console.log("Failed to update Information", error);
      })

      //console.log(temp);
      
      modalShow.value = false;
    }
   

    const store = useUserStore();
    onMounted(()=>{
        store.getUserInfo();
        //console.log(imageUrl, user.value.imageUrl);
    })

    
    const imageUrl = import.meta.env.VITE_IMAGE_URL;
    const defaultImage = profileDefault;
    
    const feedTypeOptions = [
    { value: 'rec', text: '레시피' },
    { value: 'plc', text: '식당' },
    { value: 'all', text: '모두' }
    ];
    
    // myPageData 객체를 만들고, intro 값 null 값에 대한 처리를 해준다.
    const myPageData = computed(()=>({
        userId : store.userInfo.userId,
        intro : store.userInfo?.intro ?? '',
        feedType: store.userInfo.feedType,
    }))

    const update = async function(){
        store.updateInfo(myPageData.value); 
    }
    
    const jsonUserId = ref({});
    const deleteAccount = function(){
        const typedConfirmation = prompt("회원 탈퇴를 원하시면 아이디를 정확하게 입력하세요.") 
        
        if(typedConfirmation === store.userInfo.userId){
           jsonUserId.value = {
              userId: store.userInfo.userId
           }
           store.deleteUser(jsonUserId.value);
           alert("회원 탈퇴가 정상적으로 완료됐습니다.");  
        }else{
           alert("회원 탈퇴에 실패했습니다.");
        }      
    }


</script>

<style scoped>
    .min-vh-100 {
  min-height: 100vh;
}

.form-control:focus,
.form-select:focus {
  border-color: #0d6efd;
  box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.25);
}

.form-check-input:checked {
  background-color: #0d6efd;
  border-color: #0d6efd;
}

.form-switch .form-check-input {
  width: 3em;
  height: 1.5em;
}

.form-switch .form-check-input:checked {
  background-position: right center;
}
</style>