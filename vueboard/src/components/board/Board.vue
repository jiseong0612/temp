<template>
  <div class="container">
    <div class="mb-3">
      <label for="exampleFormControlInput1" class="form-label">작성자</label>
      <input type="text" v-model="writer" ref="writer" class="form-control" id="exampleFormControlInput1" placeholder="이름을 입력하세요.">
    </div>
    <div class="mb-3">
      <label for="exampleFormControlInput1" class="form-label">제목</label>
      <input type="text" v-model="title" ref="title"  class="form-control" id="exampleFormControlInput1" placeholder="제목을 입력하세요">
    </div>
    <div class="mb-3">
      <label for="exampleFormControlTextarea1" class="form-label">내용</label>
      <textarea v-model="content" ref="content"  class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
    </div>
    <div class=''>
      <button type="button" class="btn btn-primary" @click="updateBoard" v-if="$route.params.id >= 0">수정</button>
      <button type="button" class="btn btn-warning" @click="deleteBoard" v-if="$route.params.id >= 0">삭제</button>
      <button type="button" class="btn btn-primary" @click="insertBoard" v-else>확인</button>
      <button type="button" class="btn btn-success" @click="$router.push('/')">목록</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name :"Board",
  data(){
    return {
      id : this.$route.params.id,
      writer : '',
      title : '',
      content : '',
    }
  },
  mounted(){
    if(this.id > 0){
      this.getBoard();
    }
  },
  methods: {
    getBoard(){
      const boardId= this.$route.params.id;
      axios.get(`/api/board/${boardId}`)
        .then((result)=>{
          this.writer = result.data.writer;
          this.title = result.data.title;
          this.content = result.data.content;
        })
    },
    insertBoard(){
      //유효성
      if(this.writer.trim().length === 0 ){
        alert('작성자를 입력해 주세요');
        this.$refs.writer.focus();
        return false;
      }
      if(this.title.trim().length === 0 ){
        alert('제목을 입력해 주세요');
        this.$refs.title.focus();
        return false;
      }
      if(this.content.trim().length === 0 ){
        alert('내용을 입력해 주세요');
        this.$refs.content.focus();
        return false;
      }

      //insert
      axios.post('/api/board', {writer :this.writer, title : this.title, content : this.content})
        .then(()=>{
          this.$router.push('/');
        })
    },
    updateBoard(){
      axios.patch(`/api/board/${this.id}`, {writer :this.writer, title : this.title, content : this.content})
        .then(()=>{
          alert('수정 완료!');
          this.$router.push('/');
        })
    },
    deleteBoard(){
      axios.delete(`/api/board/${this.id}`)
        .then(()=>{
          alert('삭제 완료!');
          this.$router.push('/');
        })
    }
  }
}
</script>

<style>

</style>