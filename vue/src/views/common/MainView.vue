<template>
    <div class="main">
        <div class="side">
        </div>
        <div class="center">
            <RouterView/>
        </div>
        <div class="side">
        </div>
    </div>
    <div class="float-button">
        <RouterLink :to="{name:'ArticleWriteNyam'}"><div class="nyam"><img src="../../assets/icon/bowl-food.svg"></div></RouterLink>
        <RouterLink :to="{name:'ArticleWriteRecipe'}"><div class="recipe"><img src="../../assets/icon/recipe.svg"></div></RouterLink>
    </div>
</template>

<script setup>
</script>

<style scoped>
.main {
    display: flex;
    flex: 1 1 100px;
    background-color: #FFFAEE;
}

div.main>div {
    height: 100%;
}

.side {
    flex : 1 1 100px;
    background-color: #FFFAEE;
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

.float-button .nyam {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 65px;
    height: 65px;
    margin-bottom: 10px;
    border-radius: 50%;
    background-color: #f19121;
}

.float-button .nyam img {
    width: 50px;
    height: 50px;
}

.float-button .recipe {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 65px;
    height: 65px;
    margin-bottom: 10px;
    border-radius: 50%;
    background-color: #80C134;
}

.float-button .recipe img {
    width: 40px;
    height: 40px;
}

</style>