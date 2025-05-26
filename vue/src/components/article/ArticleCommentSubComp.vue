<template>
    <div class="comment">
        <img src="../../assets/img/DeepSleepCho.jpg" style=""/>
        <div class="comment-box">
            <p class="name">DeepMindSuin</p>
            <div class="detail">
                <pre>맛있어요!!!
ㅇㄴㅁㄹㅇㄴㅁㄹㄴㅇㅁㄹㄴㅁㅇㄹ
ㅇㄴㅁㄹㅇㄴㅁㄹㄴㅇㅁㄻㄴㅇㄹㄴㅁㅇㄹㅇㄴㄹㄴㅇㅁㄹㄴㅇㅁㄹㄴㅇㅁㄹㄴㅇㅁㄹㄴㅁㅇㄹㄴㅇㄴㅁㄹㅇㄴㅁㄹㄴㅇㄻㄴㅇㄻㄴㅇㄹ</pre>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.comment {
    display: flex;
    align-items: start;
    margin-bottom: 10px;
}
.comment img {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-right: 10px;
    margin-top: 10px;
}
.comment-box {
    background-color: #e1e1e1;
    padding: 10px;
    border-radius: 5px;
    max-width: 700px;
}
.comment-box .name {
    font-weight: bold;
    margin-bottom: 5px;
    font-size: 0.9rem;
    margin-left: 10px;
}
.comment-box .content {
    margin: 0;
    font-size: 0.9em;
}
.comment-box p {
    margin: 0;
}

</style>