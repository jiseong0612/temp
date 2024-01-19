<template>
  <table class="table table-hover mt-3" style="cursor: pointer;">
    <thead>
      <tr>
        <th scope="col">글 번호</th>
        <th scope="col">제목</th>
        <th scope="col">작성자</th>
        <th scope="col">작성일</th>
      </tr>
    </thead>
    <tbody>
      <tr v-if="boardList.length == 0 ">
        <td colspan="4" class="table-warning"> 작성된 글이 없습니다.</td>
      </tr>
      <tr v-else v-for="board in boardList" :key="board.id" :board="board" @click="goToboard(board)">
        <th scope="row">{{board.id}}</th>
        <td>{{board.title}}</td>
        <td>{{board.writer}}</td>
        <td>{{board.regdate}}</td>
      </tr>
    </tbody>
  </table>

<!-- 페이징 시작 -->
  <nav aria-label="Page navigation example" class="d-flex justify-content-center">
    <ul class="pagination">
      <li class="page-item" v-if="pageMap.prev"  @click="goToPage(this.pageMap.startPage -1)"><a class="page-link" href="#">이전</a></li>
      <li class="page-item" v-for="page in pageMap.pageNumberArr" :key="page.id" >
        <a class="page-link" @click="goToPage(page)" :class="page == pageMap.currentPage? 'active': ''" href="#">{{page}}</a>
      </li>
      <li class="page-item" v-if="pageMap.next" @click="goToPage(this.pageMap.endPage +1)"><a class="page-link" href="#">다음</a></li>
    </ul>
  </nav>
<!-- 페이징 끝 -->

<!--검색 -->
<div class="input-group mb-3">
  <input v-model="keyword" ref="keyword" type="text" class="form-control" @keyup.enter="getBoardList" placeholder="검색어를 입력하세요" aria-label="Username" aria-describedby="basic-addon1">
  <select v-model="searchType" class="form-select form-select-sm" aria-label="Small select example">
    <option value="T">제목</option>
    <option value="W">작성자</option>
    <option value="C">본문</option>
    <option value="A">전체</option>
</select>
  <button type="button" class="btn btn-primary" @click="getBoardList('query')">검색</button>
</div>

<div>

</div>
<!--검색 끝-->



  <!--  버튼 -->
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
      totalCount : 0,
      page : 1, 
      pageMap : {},
      keyword : '',
      searchType :'T',
    }
  },
  created(){
    this.getBoardList();
  },
  methods: {
    getBoardList(type){
      console.log('saasdfdsa',this.$route.params)
      if(this.$route.params.id != null){
        this.page = this.$route.params.id
      } 
      if(type === 'query' && this.keyword.trim() === ''){
        alert('검색어를 입력해 주세요');
        this.$refs.keyword.focus();
        return false;
      }
      axios.get(`/api/board/page/${this.page}?keyword=${this.keyword}&searchType=${this.searchType}`)
        .then(result=>{
          this.boardList = [... result.data.boardList];
          this.totalCount = result.data.totalCount;
          this.pageMap = result.data.pageMap;
        }).catch((err)=>{
          console.log(err)
        });
    },
    // getSearchBoardList(){
    //   axios.get(`/board/page/1?keyword=${this.keyword}&searchType=${this.searchType}`)
    //     .then(result=>{
    //       console.log(result.data);
    //       this.boardList = [... result.data.boardList];
    //       this.totalCount = result.data.totalCount;
    //       this.pageMap = result.data.pageMap;
    //     }).catch((err)=>{
    //       console.log(err)
    //     });
    // },
    goToPage(page){
      console.log(page)
      this.page = page;
      if(this.keyword.trim().length > 0){
        this.$router.push(`/board/page/${page}`);
      }else{
        this.$router.push(`/board/page/${page}`);
      }
      this.getBoardList();
    },
    goToboard(board){
      this.$router.push(`/board/page/${this.page}/id/${board.id}`)
    },
  },
};
</script>

<style>
</style>