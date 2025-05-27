import { defineStore } from "pinia";
import { ref } from "vue";

export const useImageStore = defineStore("image", ()=>{
  const modal = ref(null);
  const openModal = (url)=>{
    modal.value.openModal(url)
  }
  const closeModal = ()=>{
    modal.value.closeModal()
  }
  const setModal = (value) =>{
    modal.value = value;
  }
  return {modal,openModal,closeModal, setModal};
})
