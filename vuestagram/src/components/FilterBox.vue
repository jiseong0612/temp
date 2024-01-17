<template>
  <div :class="'filter-item ' + filter" :style="`background-image : url(${fileURL})`">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name:"filterBox",
  mounted(){
    console.log(this.filter)
  },
  props:{
    fileURL:String,
    filter:String,
  }
}
</script>

<style>
.filter-item {
  width: 100px;
  height: 100px;
  margin: 10px 10px 10px auto;
  padding: 8px;
  display: inline-block;
  color : white;
  background-size: cover;
  background-position : center;
}
</style>