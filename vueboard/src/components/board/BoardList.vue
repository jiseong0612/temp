<template>
  <table class="table table-hover" style="cursor: pointer;">
    <thead>
      <tr>
        <th scope="col">글 번호</th>
        <th scope="col">제목</th>
        <th scope="col">작성자</th>
        <th scope="col">작성일</th>
      </tr>
    </thead>
    <tbody>
      <tr  v-for="(board, i) in boardList" :key="i" :board="board" @click="$router.push(`/board/${board.id}`)">
        <th scope="row">{{board.id}}</th>
        <td>{{board.title}}</td>
        <td>{{board.writer}}</td>
        <td>{{board.regdate}}</td>
      </tr>
    </tbody>
  </table>
  <div>
    <button type="button" class="btn btn-primary" @click="$router.push('/board')">글작성</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "BoardList",
  data(){
    return{
      boardList : [],
    }
  },
  created(){
    this.getBoardList();
  },
  methods: {
    getBoardList(){
      axios.get("/api/board")
        .then(result=>{
          this.boardList = [... result.data];
        }).catch((err)=>{
          console.log(err)
        });
    },
  },
};
</script>

<style>
</style>