<template>
  <div>
    <h4>상세페이지</h4>
    <h5>{{blogs[$route.params.id].title}} </h5>
    <p>{{blogs[$route.params.id].content}}</p>
    <router-view></router-view>
    <br>
    <div>
        <h2>블로그 상세 정보</h2>
    {{blogs[$route.params.id]}}
    </div>
    <br>
    <div>
        <h2>$route 객체 정보</h2>
      {{$route}}
    </div>
  </div>
</template>

<script>
export default {
  name:'Detail',
  props:{
    blogs:Array
  }
}
</script>

<style>

</style>