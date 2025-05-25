<template>
    <div>
        <div class="container">
            <div class="outline">
                <div class="profile">
                    <img src="../../assets/img/DeepSleepCho.jpg" alt="프로필 이미지" class="profile-image">
                    <span class="profile-name">사용자 이름</span>
                </div>
                <textarea rows="5" class="comment-input" placeholder="댓글을 입력하세요..."></textarea>
                <button class="btn btn-secondary">등록</button>
            </div>
        </div>
    </div>
</template>

<script setup>
    import { ref } from 'vue';
    const comment = ref({
        
    });
    const url = import.meta.env.VITE_API_URL + '/article';
    const handleCommentSubmit = () => {
        if (comment.value.trim() === '') {
            alert('댓글을 입력해주세요.');
            return;
        }
        axios.post(url,params)
            .then(res => {
                console.log(res.data);
                comment.value = '';
            })
            .catch(err => {
                console.error(err); 
            })
        
    };
</script>

<style scoped>
.outline {
    width: 100%;
    height: 100%;
    padding: 20px;
    background-color: #ffffff;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    position: relative;
}
.comment-input {
    width: 100%;
    min-height: 80px;
    height: auto;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 14px;
    resize: vertical;
    overflow-y: visible;
}
.outline button {
    width: 70px;
    height: 35px;
    font-size: 0.8em;
    align-self: flex-end;
    margin-top: 10px;
    margin-right: 5px;
}
.profile {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
}
.profile-image {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-right: 10px;
}
.profile-name {
    font-size: 16px;
    font-weight: bold;
    color: #333;
}

</style>