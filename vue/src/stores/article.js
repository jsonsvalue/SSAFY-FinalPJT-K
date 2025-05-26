import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

const REST_API_URL = import.meta.env.VITE_API_URL;

export const useArticleStore = defineStore("article", ()=>{
    const articleList = ref(null);    

    const getArticleList = function(userId){
        axios.get(`${REST_API_URL}/articleUser/${userId}`)
        .then((response)=>{
            articleList.value = response.data;
            //console.log(articleList.value);
        })
        .catch((err)=>{
            //console.log(err);
        })

    }

    return { getArticleList, articleList };
})