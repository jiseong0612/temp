<template>
  <div>
    <Post :posts="post" v-for="(post, i ) in posts" :key="i"></Post>
  </div>

</template>

<script>
import Post from './Post.vue';

export default {
  name:"Containner",
  data(){

  },
  props:{
    posts: Array,
  },
  components:{
    Post
  }
}
</script>

<style>

</style>