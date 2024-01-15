<template>

  <!-- v-bind = : 콜론이 v-bind 이다.dd -->
  <Transition name="fade">
    <Modal @closeModal="isModalShow=false;" :onerooms="onerooms" :isModalShow="isModalShow" :clicked="clicked"></Modal>
</Transition>
  <div class="menu">
    <a v-for="(menu,i)  in menus" :key="i">
      {{menu}}
    </a>
  </div>

  <Discount></Discount>

  <Card @openModal="isModalShow =true; clicked = $event;" v-for=" (oneroom,i) in onerooms" :key="i" :oneroom="onerooms[i]" :count="count[i]"></Card>
  
</template>

<script>
import onerooms from './assets/oneroom.js';
import Discount from './Discount.vue';
import Modal from './Modal.vue';
import Card from './Card.vue';

export default {
  name: 'App',
  data(){
    return{
      clicked : 0,
      onerooms,
      isModalShow : false,
      count : [0,0,0,0,0,0],
      price1 : 60,
      price2 : 85,
      color : 'color : blue',
      products : ['역삼동 원룸','천호동 원룸','마포구 원룸'],
      menus : ['Home', 'Products','About'],
    }
  },
  methods :{
    increase(i){
      ++this.count[i];
    },
  },
  components: {
    Discount,
    Modal,
    Card,
  }
}
</script>

<style>
.fade-enter-from { opacity: 0; }
.fade-enter-active { transition: all 1s; }
.fade-enter-to { opacity: 1; }

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.menu{
  background: darkslateblue;
  padding: 15px;
  border-radius: 5px;
}
.menu a{
  color : white;
  padding: 10px;
}
.room-img{
  width: 100%;
  margin-top: 40px;
}

body{
  margin: 0;
}

div{
  box-sizing: border-box;
}
</style>
