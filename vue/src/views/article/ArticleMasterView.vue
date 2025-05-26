<template>
    <div class="container">
        <div class="outline">
            <div class="profile">
                <ProfileImageComp :src="article.userImage" :user-id="article.userId"></ProfileImageComp>
                <div class="name-box">
                    <p class="name" style="cursor:pointer" @click="profile">{{article.userName}}</p>
                    <p class="category" v-if="article.type=='eat'">냠냠</p>
                    <p class="category" v-else>레시피</p>
                </div>
            </div>
            <div class="img-box" @click="openModal(article.imageUrl)">
                <img :src="article.imageUrl" alt="Article Image"/>
            </div>
            <div class="content-box" v-if="article.content">
                <pre>{{ article.content }}</pre>
            </div>
            <div class="ingredient-box" v-if="article.type == 'recipe'">
                <div class="label"><span>재료</span></div>
                <div class="ingredient">
                    <pre>{{article.ingredient}}</pre>
                </div>
            </div>
            <div class="detail-box">
                <template v-for="sub in subArticle" :key="sub.id">
                    <ArticleDetailComp :sub="sub"></ArticleDetailComp>
                </template>
            </div>
            <div class="button-box">
                <div :class="{dislike:isLike,like:!isLike}" @click="like">
                    <img/>
                    <span>{{article.likeCount}}</span>
                </div>
                <div class="comment" @click="toComment"><img src="../../assets/icon/comment.svg"/></div>
                <div class="share" @click="share"><img src="https://www.svgrepo.com/show/512829/share-1100.svg"/></div>
            </div>
        </div>
        <ArticleCommentComp :article-id="Number(articleId)" :comment="comment"></ArticleCommentComp>
    </div>
    <BToast ref="toast" id="toast" class="bottom-0 end-0 m-3 position-fixed toast hide p-2" auto-hide-delay="500">
        클립보드에 복사되었습니다!
    </BToast>
</template>

<script setup>
    import axios from 'axios';
    import { ref,onMounted,watch } from 'vue';
    import { useRouter,useRoute } from 'vue-router';
    import ArticleDetailComp from '@/components/article/ArticleDetailComp.vue';
    import ArticleCommentComp from '@/components/article/ArticleCommentComp.vue';
    import ProfileImageComp from '@/components/user/ProfileImageComp.vue';
    import { BToast,BInputGroup, BInputGroupText,BFormTextarea } from 'bootstrap-vue-next';
    import { useImageStore } from '@/stores/image.js';
    const article = ref({});
    const subArticle = ref([]);
    const comment = ref([]);
    const url = import.meta.env.VITE_API_URL;
    const imageUrl = import.meta.env.VITE_IMAGE_URL;
    const router = useRouter();
    const route = useRoute();
    const articleId = route.params.id;
    const image = useImageStore();
    const profile = () => {
        router.push({
            name: 'profile',
            params: { userId: article.value.userId }
        });
    };
    const openModal = (url) => {
        image.openModal(url);
    };
    const toast = ref(null);
    onMounted(async () => {
        if (route.query.comment) {
            setTimeout(() => {
                toComment();
            }, 300);
        }
        try {
            const response = await axios.get(url + '/article/' + articleId);
            response.data.article.imageUrl = imageUrl + response.data.article.imageUrl;
            article.value = response.data.article;
            response.data.subArticle.forEach((sub) => {
                if (sub.imageUrl) {
                    sub.imageUrl = imageUrl + sub.imageUrl;
                } else {
                    sub.imageUrl = null;
                }
            });
            response.data.comment.forEach((comm) => {
                if (comm.imageUrl) {
                    comm.imageUrl = imageUrl + comm.imageUrl;
                } else {
                    comm.imageUrl = null;
                }
            });
            subArticle.value = response.data.subArticle;
            comment.value = response.data.comment;
            isLike.value = article.value.isLike === 'true';
            console.log(article);
        } catch (error) {
            console.error('Error fetching articles:', error);
            alert('유효하지 않은 접근입니다.');
            router.push('/');
        }
    });
    const isLike = ref(false);

    watch(() => article.value.isLike, (newVal) => {
        isLike.value = newVal === 'true';
    });
    
    const like = () => {
        
        const likeurl = import.meta.env.VITE_API_URL + `/article/${article.value.id}/like`;
        if (isLike.value) {
            axios.delete(likeurl)
            .then(res => {
                article.value.isLike = 'false';
                article.value.likeCount--;
            })
            .catch(err => {
                console.error(err); 
            })
        } else {
            axios.put(likeurl)
            .then(res => {
                article.value.isLike = 'true';
                article.value.likeCount++;
            })
            .catch(err => {
                console.error(err); 
            })
        }
    };
    const toComment = () => {
        // scroll to the comment section
        const commentSection = document.querySelector('#comment');
        if (commentSection) {
            commentSection.scrollIntoView({ behavior: 'smooth' });
            commentSection.focus();
        }
    };
    const share = () => {
        const shareUrl = `${window.location.origin}/article/${articleId}`;
        if (navigator.share) {
            navigator.share({
                title: 'Check out this article!',
                url: shareUrl
            }).then(() => {
                console.log('Share successful');
            }).catch((error) => {
                console.error('Error sharing:', error);
            });
        } else {
            navigator.clipboard.writeText(shareUrl)
            .then(() => {
                toast.value.show();
                setTimeout(() => {
                    toast.value.hide();
                }, timeout = 800);
            })
        }
    };
</script>

<style scoped>

    .container {
        padding-top: 50px;
        padding-bottom: 100px;
    }
    .outline {
        border: 1px solid #EEE;
        border-radius: 5px;
        padding: 15px;
        box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
        -webkit-box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
        -moz-box-shadow: 0px 3px 5px -2px rgba(0,0,0,0.42);
        display: flex;
        flex-direction: column;
    }

    .content-box {
        margin-top: 10px;
        display: flex;
        border-radius: 15px;
        padding: 10px;
        border: #AAA solid 1px;
        margin-bottom: 50px;
    }

    .like,.dislike,.comment,.share {
        cursor: pointer;
    }

    .like img {
        content: url('../../assets/icon/heart.svg');
        border: none;
    }

    .dislike img {
        content: url('../../assets/icon/heart-fill.svg');
        border: none;
    }

    .ingredient-box {
        display: flex;
        min-height: 60px;
    }

    .label {
        font-weight: bold;
        width: 50px;
        background-color: #AAA;
        border-top-left-radius: 10px;
        border-bottom-left-radius: 10px;
        text-align: center;
        display: flex;
        align-items: center;
    }
    .label span {
        margin-left: 10px;
    }
    .ingredient {
        flex: 1;
        padding: 10px;
        border: #AAA solid 1px;
        border-top-right-radius: 10px;
        border-bottom-right-radius: 10px;
        overflow-y: auto;
    }
    .img-box {
        cursor:pointer
    }
</style>   