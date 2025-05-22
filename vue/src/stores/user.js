import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

const REST_API_URL = import.meta.env.VITE_API_URL;

export const useUserStore = defineStore("user", ()=>{
  const loginUser = ref(null);

  



})
