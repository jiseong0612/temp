<template>
  <div class="black-bg" v-if="isModalShow">
    <div class="white-bg">
      <h4>{{onerooms[clicked].title}}</h4>
      <p>{{onerooms[clicked].content}}</p>
      <p>{{onerooms[clicked].price}} 원</p>
      <!-- v-model : @input="month = $event.target.value" -->
      <input v-model="month"><br><br>
      <input type="range" name="" id="" min="1" max="12" value="1" v-model="month">
      <p>{{month}} 개월 선택함 {{onerooms[clicked].price * month}}</p>
      <button @click="$emit('closeModal')">닫기</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Modal',
  data(){
    return {
      month : 1,

    }
  },
  beforeUpdate() {
    if(this.month == 2 ){
      alert('2월 이상 ㄱㄱ')
    }else if(this.month == -2){
      this.month = 3;
    }
  },
  watch : {
    month(a){
      //정규식 표현
      if(/[A-Za-z]/ig.test(a)){
        alert('글자 입력 금지!');
        this.month = 1;
      }

      //es5
      if(isNaN(a)){
        alert('글자 입력 금지!');
        this.month = 1;
      }
    },
  },
  props:{
    onerooms : Array,
    clicked : Number,
    isModalShow : Boolean,
  },
}
</script>

<style>
  .black-bg{
    width: 100%; height: 100%;
    background: rgba(0,0,0,0.5);
    position: fixed; padding: 20px;
  }
  .white-bg{
    width: 100%; background: white;
    border-radius: 8px;
    padding: 20px;
  }
</style>