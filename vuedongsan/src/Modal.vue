<template>
  <div class="black-bg" v-if="isModalShow">
    <div class="white-bg">
      <h4>{{onerooms[clicked].title}}</h4>
      <p>{{onerooms[clicked].content}}</p>
      <p>{{onerooms[clicked].price}} 원</p>
      <!-- v-model : @input="month = $event.target.value" -->
      <input v-model="month">
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