<template>
  <div>
    <img :src="oneroom.image" class="room-img">
    <h4 @click="send">{{oneroom.title}}</h4>
    <p>{{oneroom.price}} 만원</p>
    <button @click="increase(i)">허위매물 신고</button>  
    <span>신고수 : {{count[i]}}</span>
  </div>
</template>

<script>
export default {
  name : 'Card',

  props:{
    oneroom : Object,
    count : Number,
  },
  methods:{
    send(){
      this.$emit('openModal', this.oneroom.id);
    },
    
  },
}
</script>

<style>

</style>