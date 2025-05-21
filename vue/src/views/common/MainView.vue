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
</style>