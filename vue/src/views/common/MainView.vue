<template>
    <div class="main">
        <div class="side">
            <p>사이드</p>
        </div>
        <div class="center">
            <p>센터</p>
            <RouterView/>
        </div>
        <div class="side">
            <p>사이드</p>
        </div>
    </div>
    <div class="float-button">
        <RouterLink :to="{name:'ArticleWriteNyam'}">냠냠 작성</RouterLink>
        <RouterLink :to="{name:'ArticleWriteRecipe'}">레시피 작성</RouterLink>
    </div>
</template>

<script setup>
</script>

<style scoped>
.main {
    display: flex;
    flex: 1 1 100px;
}

div.main>div {
    height: 100%;
}

.side {
    flex : 1 1 100px;
    background-color: antiquewhite;
}

.center {
    flex : 0 0 700px;
}

.float-button {
    position: fixed;
    right: 50px;
    bottom: 50px;
    display: flex;
    flex-direction: column;
}
</style>