<template>
  <Header></Header>
  <router-view/>
</template>

<script>
import Header from './components/includes/Header.vue';

export default {
  name: 'App',
  data(){
    return {
    }
  },
  components: {
    Header,
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
