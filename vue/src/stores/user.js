import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

const REST_API_URL = import.meta.env.VITE_API_URL;

export const useUserStore = defineStore("user", ()=>{
  // 회원 가입, 로그인, 사용자 정보를 불러올 때의 정보를, 
  // userInfo에 저장한다.
  const userInfo = ref(null);
  const result = ref(null);

  const deleteUser = function(userData){
    axios.patch(`${REST_API_URL}/delete`, userData)
    .then((response)=>{
      result.value = response.data;
      console.log(result.value);
    })
    .catch((err)=>{
      console.log(err);
    })
  }

  const getProfileInfo = function(userId){
    axios.get(`${REST_API_URL}/profile/${userId}`)
    .then((response)=>{
      userInfo.value = response.data;
      // console.log("Get Profile Info", response.data);
    }).catch((err)=>{
      console.log(err);
    })    

  }

  // 사용자의 정보를 response에서 가져온다.
  const getUserInfo = function(){
      axios.get(`${REST_API_URL}/getUserInfo`)
      .then((response)=>{
        userInfo.value = response.data;
        // console.log("getUserInfo", userInfo.value);
      })
      .catch((err)=>{
        console.log(err);
      })
  }

  // 사용자의 정보를 요청을 보내고 response를 받아온다.
  const updateInfo = function(myPageData){
    axios.patch(`${REST_API_URL}/update`, myPageData)
    .then((response)=>{
      userInfo.value = response.data;
      
      console.log("update Info", userInfo.value);
    })
    .catch((error)=>{
      console.log("Failed to update Information", error);
    })

  }

  // 회원 가입 시 실행할 함수
  const userRegister = function(userId, password, email, name){
    // console.log(userId, password, email, name);
    // axios를 통해서 요청을 보낼 백엔드 api
    axios.post(`${REST_API_URL}/regist`,{
      userId, 
      password,
      email,
      name
    },
    {
      withCredentials:true
    })
    // response에서 받은 데이터를 통해서 session정보를 설정해준다.
    // 저장한 session정보를 이용해서, 사용자의 정보를 띄운다.
    .then((response)=>{
      console.log(response.data);

      // sessionStorage에 user의 데이터를 json 형태 데이터로 저장한다.
      const userData = JSON.stringify(response.data);
      sessionStorage.setItem("user", userData);
      
      // userInfo에 userData를 저장한다.
      userInfo.value = userData;

      router.push("/");
    })
    // error가 났을 때의 코드를 처리한다.
    // 이메일 주소랑 사용자 아이디 동일할 때, 실행할 코드를 처리한다.
    .catch((err)=>{
      console.log(err);

    })
  }

  // 로그인하고 session Storage에 정보를 저장한다.
  const userLogin = function(userId, password){
    axios.post(`${REST_API_URL}/login`,{
      userId,
      password
    },
    {
      withCredentials:true
    })
    // 로그인 성공 시 session Storage에 해당 정보 저장하고,
    // 다른 페이지로 이동한다.
    .then((response)=>{
      console.log("Successfully Logged in", response);

      const userData = response.data;
      sessionStorage.setItem("user", JSON.stringify(userData));

      // userInfo에 userData를 저장한다.
      userInfo.value = userData;

      router.push('/');
    })  
    .catch((err)=>{
      console.log(err);
      router.push('/login');
    })

  }


  return { userRegister, userLogin, getUserInfo, updateInfo, getProfileInfo, deleteUser, userInfo};
})
