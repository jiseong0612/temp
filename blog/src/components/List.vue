<template>
  <div v-for="(blog,i) in blogs" :key="i" :blog="blog">
    <h5 @click="$router.push('/detail/'+i)">{{blog.title}}</h5>
    <p>{{blog.date}}</p>
  </div>
</template>

<script>

export default {
  name : "List",
  props: {
    blogs : Array,
  }
}
</script>

<style>

</style>