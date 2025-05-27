import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

const REST_API_URL = import.meta.env.VITE_API_URL;

export const useFollowStore = defineStore("follow", ()=>{
    const followList = ref(null);
    const followerList = ref(null);

    const unfollowUser = function(userId, followId){
        axios.delete(`${REST_API_URL}/unfollow/${userId}?followId=${followId}`, userId, followId)
        .then((response)=>{
            console.log(response.data);
            return true;
        })
        .catch((err)=>{
            console.log(err);
            return false;
        })
    }

    const followUser = function(userId, followId){
        axios.post(`${REST_API_URL}/follow/${userId}?followId=${followId}`, userId, followId)
        .then((response)=>{
            console.log(response.data);
            return true;
        })
        .catch((err)=>{
            console.log(err);
            return false;
        })
    }

    const getFollowList = function(userId){
        axios.get(`${REST_API_URL}/follow/getFollow/${userId}`)
        .then((response)=>{
            followList.value = response.data;
            console.log("FollowList retrieved", followList.value)
        })
        .catch((err)=>{
            console.log("FollowList retrieval failed", err);
        })
    }


    const getFollowerList = function(userId){
        axios.get(`${REST_API_URL}/follow/getFollower/${userId}`)
        .then((response)=>{
            followerList.value = response.data;
            console.log("FollowerList retrieved", followerList.value)
        })
        .catch((err)=>{
            console.log("FollowerList retrieval failed", err);
        })
    }

    return {followList, followerList, getFollowList, getFollowerList, followUser, unfollowUser};
})