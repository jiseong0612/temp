<template>
  <div>
    <div class="header">
        <ul class="header-button-left">
          <li>Cancel</li>
        </ul>
        <ul class="header-button-right">
          <li v-if="step==1" @click="step++;">Next</li>
            <li v-if="step==2" @click="publish();">발행</li>
        </ul>
        <img src="./assets/logo.png" class="logo" />
      </div>

      <h4>안녕  {{$store.state.name}}</h4>
      <button @click="$store.state.name='에에'">버튼</button>
      <Container :posts="posts" :step="step" :fileURL="fileURL" @write="content = $event;"></Container>
      <button @click="more" v-if="step == 0">더보기</button>
      <div class="footer">
        <ul class="footer-button-plus" v-if="step == 0">
          <input @change="upload" type="file" id="file" class="inputfile" />
          <label for="file" class="input-plus">+</label>
        </ul>
    </div>
  </div>
</template>

<script>
import Container from './components/Container.vue';
import posts from './components/posts.js';
import axios from 'axios';

export default {
  name: "App",
  data(){
    return{
      posts,
      step : 0,
      postCount : 0,
      fileURL : '',
      content : '',
    }
  },
  methods:{
    more(){
      axios.get(`https://codingapple1.github.io/vue/more${this.postCount}.json`)
        .then(result => {
          this.posts.push(result.data);
          this.postCount ++;
      })
    },
    upload(e){
      let selectedFile  = e.target.files[0];
      
      if (selectedFile){
        this.fileURL = URL.createObjectURL(selectedFile);
        this.step = 1;
      }
    },
    publish(){
      var myContent =  {
        name: "Kim Hyun",
        userImage: "https://picsum.photos/100?random=3",
        postImage: this.fileURL,
        likes: 36,
        date: "May 15",
        liked: false,
        content: this.content,
        filter: "perpetua"
      };

      this.posts.unshift(myContent);
      console.log("myContent: ", myContent.content);
      this.step = 0;
    },
  },
  components: {
    Container
  },
};
</script>

<style>
body {
  margin: 0;
}
ul {
  padding: 5px;
  list-style-type: none;
}
.logo {
  width: 22px;
  margin: auto;
  display: block;
  position: absolute;
  left: 0;
  right: 0;
  top: 13px;
}
.header {
  width: 100%;
  height: 40px;
  background-color: white;
  padding-bottom: 8px;
  position: sticky;
  top: 0;
}
.header-button-left {
  color: skyblue;
  float: left;
  width: 50px;
  padding-left: 20px;
  cursor: pointer;
  margin-top: 10px;
}
.header-button-right {
  color: skyblue;
  float: right;
  width: 50px;
  cursor: pointer;
  margin-top: 10px;
}
.footer {
  width: 100%;
  position: sticky;
  bottom: 0;
  padding-bottom: 10px;
  background-color: white;
}
.footer-button-plus {
  width: 80px;
  margin: auto;
  text-align: center;
  cursor: pointer;
  font-size: 24px;
  padding-top: 12px;
}
.sample-box {
  width: 100%;
  height: 600px;
  background-color: bisque;
}
.inputfile {
  display: none;
}
.input-plus {
  cursor: pointer;
  padding: 20px;
  background-color: lightgray;
  border-radius: 20px;
}

.input-plus:hover{
  background-color: aqua;
}
#app {
  box-sizing: border-box;
  font-family: "consolas";
  margin-top: 60px;
  width: 100%;
  max-width: 460px;
  margin: auto;
  position: relative;
  border-right: 1px solid #eee;
  border-left: 1px solid #eee;
}
</style>
