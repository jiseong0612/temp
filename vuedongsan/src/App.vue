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

  <Discount v-if="showDiscount" :discountPercent="discountPercent"></Discount>

  <button v-for="(a,i) in sortType" :key="i" @click="priceSort(i)" class="sortBtn">{{sortType[i]}}</button>
  <button @click="sortBack" class="sortBtn">원본 정렬</button>

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
      discountPercent : 30,
      showDiscount : true,
      clicked : 0,
      originalOnerooms : [...onerooms],
      onerooms,
      isModalShow : false,
      count : [0,0,0,0,0,0],
      price1 : 60,
      price2 : 85,
      color : 'color : blue',
      sortType : ['가격낮은순정렬', '가격높은순정렬', '가나다순정렬'],
      products : ['역삼동 원룸','천호동 원룸','마포구 원룸'],
      menus : ['Home', 'Products','About'],
    }
  },
  methods :{
    increase(i){
      ++this.count[i];
    },
    sortBack(){
      this.onerooms = [...this.originalOnerooms];
    },
    priceSort(sortType){
      if(sortType == 0){  //가격낮은순정렬
        this.sortType[sortType];

        this.onerooms.sort(function(a, b){
          return a.price- b.price;
        }); 
      }else if(sortType == 1){  //가격높은순정렬
        this.sortType[sortType];
        
        this.onerooms.sort(function(a, b){
          return b.price - a.price;
        }); 
      }else if(sortType == 2){  //가나다순정렬
        this.sortType[sortType];

        this.onerooms.sort(function(a, b) {
        var titleA = a.title.toUpperCase(); // 대소문자 구분 없이 정렬하려면 대문자로 변환
        var titleB = b.title.toUpperCase();

        if (titleA < titleB) {
          return -1;
        } else if (titleA > titleB) {
          return 1;
        } else {
          return 0;
        }
      });
      }
    },
    decressPersend(){
      setTimeout(() => {
        clearInterval(interval);
        this.discountPercent = 0;
      }, this.discountPercent * 1000);

      let interval = setInterval(()=>{
        this.discountPercent = this.discountPercent-1;
      }, 1000);
      interval;
    },
  },
  mounted() {
    this.decressPersend();
  },
  components: {
    Discount,
    Modal,
    Card,
  },
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

.sortBtn{
  margin: 0 10px;
  padding: 10px;
  background-color: dodgerblue;
  color : ivory;
  font-weight: bold;
  border-radius: 5px;
  border: 2px solid blue;
}

.sortBtn:hover{
  background-color: blue;
}



body{
  margin: 0;
}

div{
  box-sizing: border-box;
}
</style>
