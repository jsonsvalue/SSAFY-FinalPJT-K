import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

const REST_API_URL = import.meta.env.VITE_API_URL;

export const useUserStore = defineStore("user", ()=>{
  const registUser = ref(null);

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
      console.log(response);

      const userData = response.data;
      sessionStorage.setItem("user", JSON.stringify(userData));

      router.push('/');
    })  
    .catch((err)=>{
      console.log(err);
      router.push('/login');
    })

  }


  return { userRegister, userLogin};
})
